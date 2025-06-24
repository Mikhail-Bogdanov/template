import java.io.File
import java.util.Properties

try {
    val commitMsgFile = File(args[0])
    val originalMessage = commitMsgFile.readText().trim()

    val propertiesFile = File("gradle.properties")
    val properties = Properties().apply {
        load(propertiesFile.inputStream())
    }

    val MAJOR_CODE = properties.getProperty("MAJOR_CODE").toInt() + 1
    val MINOR_CODE = properties.getProperty("MINOR_CODE").toInt() + 1
    val PATCH_CODE = properties.getProperty("PATCH_CODE").toInt() + 1

    properties.setProperty("PATCH_CODE", PATCH_CODE.toString())

    propertiesFile.outputStream().use { properties.store(it, null) }

    val process = ProcessBuilder("git", "add", "gradle.properties")
        .inheritIO()
        .start()
    process.waitFor()

//    throw Exception("asd")

    commitMsgFile.writeText(originalMessage)

} catch (e: Exception) {
    println(e.localizedMessage)
    kotlin.system.exitProcess(1)
}
