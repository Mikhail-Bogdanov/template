import java.io.File

if (args.isEmpty()) {
    println("❌ Commit message file path not provided.")
    kotlin.system.exitProcess(1)
}

val commitMsgFile = File(args[0])
if (!commitMsgFile.exists()) {
    println("❌ Commit message file not found: ${args[0]}")
    kotlin.system.exitProcess(1)
}

val original = commitMsgFile.readText().trim()
val updated = "[task] $original"

commitMsgFile.writeText(updated)

println("✅ Commit message updated to: $updated")
