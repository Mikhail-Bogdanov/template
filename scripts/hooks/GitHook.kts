import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.pathString

println("STARTING GIT HOOK")

try {
    val firstArg = args.firstOrNull()

    if (firstArg != null) {
        CommitMessage(firstArg)
    } else {
        PreCommit()
    }

//    throw Exception("STUB! TEST HOOK!")
} catch (e: Exception) {
    println(e.localizedMessage)
    kotlin.system.exitProcess(1)
}

internal class PreCommit {

    private val changes = GitHelper.getCommitChanges()

    init {
        val changedFiles = changes.size.also {
            println("Changed files: $it")
        }
        val added = changes.sumOf { it.added }
        val deleted = changes.sumOf { it.deleted }
        val changes = (added + deleted).also {
            println("Changes quantity: $it")
        }

        val codeToIncrease = when {
            changedFiles >= CHANGED_FILES_MAJOR_QUANTITY && changes >= LINES_MAJOR_QUANTITY -> Code.MAJOR
            changedFiles <= CHANGED_FILES_MINOR_QUANTITY && changes <= LINES_MINOR_QUANTITY -> Code.PATCH
            else -> Code.MINOR
        }

        increaseCode(codeToIncrease)
    }

    private fun increaseCode(code: Code) {
        val newCode = code.current + 1
        code.set(newCode)

        val message = when (code) {
            Code.MAJOR -> {
                Code.MINOR.clear()
                Code.PATCH.clear()
                "New ${code.name} release!"
            }
            Code.MINOR -> {
                Code.PATCH.clear()
                "${code.name} version release!"
            }
            Code.PATCH -> {
                "${code.name}!"
            }
        } + "\n" + "Bumped to $newCode"

        println(message)

        val exitCode = GitHelper.add(code.filePath).waitFor()
        val status = if (exitCode != 0) "FAILED" else "SUCCEEDED"

        println("Git adding $status!")
    }

    companion object {

        private const val CHANGED_FILES_MAJOR_QUANTITY = 40
        private const val CHANGED_FILES_MINOR_QUANTITY = 25

        private const val LINES_MAJOR_QUANTITY = 2000
        private const val LINES_MINOR_QUANTITY = 750

        enum class Code(val filePath: String) {
            MAJOR(Path("version", "major.txt").pathString) {
                override val file = File(filePath)
            },
            MINOR(Path("version", "minor.txt").pathString) {
                override val file = File(filePath)
            },
            PATCH(Path("version", "patch.txt").pathString) {
                override val file = File(filePath)
            },
            ;

            abstract val file: File
            val current get() = file.readText().trim().toInt()

            fun set(newCode: Int) {
                file.writeText(newCode.toString().trim())
            }

            fun clear() = set(0)

            val isMajor get() = this == MAJOR
            val isMinor get() = this == MINOR
            val isPatch get() = this == PATCH
        }
    }
}

internal class CommitMessage(messageFilePath: String) {

    private val messageFile = File(messageFilePath)
    private val originalMessage = messageFile.readText().trim()

    init {
        println(originalMessage) // todo come up with smth
    }

    fun setMessage(newMessage: String) {
        messageFile.writeText(newMessage)
    }
}

internal object GitHelper {

    /**
     * @return list of changes quantity; list.size == changed files quantity
     */
    fun getCommitChanges(): List<Changes> {
        val proc = execute("git", "diff", "--cached", "--numstat")

        val output = proc.inputStream.bufferedReader().readText().trim()

        proc.waitFor()

        return output.lines().mapNotNull { line ->
            val clearedLine = line.filter {
                it.isDigit() || it.isWhitespace()
            }.trim().ifBlank { "" }

            val isLineValid = clearedLine.matches(Regex("\\d+\\W+\\d+"))

            if (isLineValid) {
                val added = clearedLine.takeWhile { it.isWhitespace().not() }.toInt()
                val deleted = clearedLine.takeLastWhile { it.isWhitespace().not() }.toInt()
                Changes(added = added, deleted = deleted)
            } else {
                null
            }
        }
    }

    fun add(fileName: String) = execute("git", "-C", File("").canonicalPath, "add", fileName)

    fun execute(
        vararg command: String,
    ): Process = ProcessBuilder(*command).redirectErrorStream(true).start()

    data class Changes(val added: Int, val deleted: Int)

}
