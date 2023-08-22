plugins {
    kotlin("jvm") version "1.9.0"
    id("cc.polyfrost.loom") version "0.10.0.5"
    id("dev.architectury.architectury-pack200") version "0.1.3"
}

group = "de.tomjuri"
version = "1.0.0"

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
}

loom.forge.pack200Provider.set(dev.architectury.pack200.java.Pack200Adapter())
kotlin.jvmToolchain(8)