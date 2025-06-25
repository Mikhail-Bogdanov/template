import java.io.File
import java.util.Properties

println("STARTING GIT HOOK")

try {
    val firstArg = args.firstOrNull()

    if (firstArg != null) {
        CommitMessage(firstArg)
    } else {
        PreCommit()
    }

} catch (e: Exception) {
    println(e.localizedMessage)
    kotlin.system.exitProcess(1)
}

internal class PreCommit {

    private val propertiesFile = File(VERSION_FILE)
    private val properties = Properties().apply { load(propertiesFile.inputStream()) }

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
        val newCode = code.getCurrentCode(properties) + 1

        if (code.isMinor) {
            properties.setProperty(Code.PATCH.codeName, "0")
        } else if (code.isMajor) {
            properties.setProperty(Code.MINOR.codeName, "0")
            properties.setProperty(Code.PATCH.codeName, "0")
        }

        properties.setProperty(code.codeName, "$newCode")

        properties.store(propertiesFile.outputStream(), null)

        println("Property $code updated to $newCode")

        val exitCode = GitHelper.add(VERSION_FILE).waitFor()
        val status = if (exitCode != 0) "FAILED" else "SUCCEEDED"

        println("Git adding $status!")
    }

    companion object {

        private const val CHANGED_FILES_MAJOR_QUANTITY = 40
        private const val CHANGED_FILES_MINOR_QUANTITY = 25

        private const val LINES_MAJOR_QUANTITY = 2000
        private const val LINES_MINOR_QUANTITY = 750

        enum class Code(val codeName: String) {
            MAJOR(codeName = "MAJOR_CODE"),
            MINOR(codeName = "MINOR_CODE"),
            PATCH(codeName = "PATCH_CODE"),
            ;

            val isMajor get() = this == MAJOR
            val isMinor get() = this == MINOR
            val isPatch get() = this == PATCH

            fun getCurrentCode(properties: Properties) = properties.getProperty(codeName).toInt()
        }

        private const val VERSION_FILE = "gradle.properties"
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
