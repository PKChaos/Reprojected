import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * this is a top-level gradle buildscript.
 * Project-wide dependencies, such as mindustry, arc and kotlin, should be declared here.
 * Read the comments for more info.
 */

plugins {
	kotlin("jvm") version "1.8.0"
}

allprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")

	repositories {
		mavenCentral()
		mavenLocal()
		maven("https://jitpack.io")
	}

	dependencies {
		compileOnly("com.github.Anuken.Arc", "arc-core", "v143.1")
		compileOnly("com.github.Anuken", "MindustryJitpack", "v143.1")
		
		//might use, we'll have to see.
		//implementation("com.github.mnemotechnician", "mkui", "v1.2.1")
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = "1.8"
		targetCompatibility = "1.8"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			// target java version - 8. do not increase unless you really need to
			// this will not change a lot but will break mobile compatibility.
			jvmTarget = "1.8"
			// kotlin compiler argument
			freeCompilerArgs += arrayOf(
				// use the experimental kotlin compiler - x2 speed
				"-Xuse-k2",
				// enable context receivers
				// note: context receivers seem to be kinda broken in the k2 compiler.
				"-Xcontext-receivers"
			)
		}
	}
}
