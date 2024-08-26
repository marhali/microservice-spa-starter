import org.jetbrains.kotlin.com.google.gson.Gson
import org.jetbrains.kotlin.com.google.gson.JsonObject
import java.util.*

plugins {
	id("base")
}

val frontendDir = file(".")
val packageJsonFile = file("${frontendDir}/package.json")

val gson = Gson()

if(packageJsonFile.exists()) {
	val packageJson = gson.fromJson(packageJsonFile.reader(), JsonObject::class.java)
	val scripts = packageJson.getAsJsonObject("scripts")
	val packageManagerProperty = packageJson.getAsJsonPrimitive("packageManager").asString
	val packageManager = packageManagerProperty.split("@")[0]

	scripts.entrySet().forEach { (scriptName) ->
		val capitalizedScriptName = scriptName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

		tasks.register<Exec>("${packageManager}Run${capitalizedScriptName}") {
			group = "application"
			description = "Runs the $packageManager script from ${packageJsonFile.name}"
			workingDir = frontendDir
			commandLine(packageManager, scriptName)
		}
	}
} else {
	logger.warn("Could not find package.json in directory '${packageJsonFile.path}'")
}
