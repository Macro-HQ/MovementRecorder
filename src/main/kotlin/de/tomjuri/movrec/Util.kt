package de.tomjuri.movrec

import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding

object Util {
    fun getForward() = Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown
    fun getLeft() = Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown
    fun getBackward() = Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown
    fun getRight() = Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown
    fun getSneak() = Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown
    fun getJump() = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown
    fun getSprint() = Minecraft.getMinecraft().gameSettings.keyBindSprint.isKeyDown
    fun getAttack() = Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown
    fun getUse() = Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown
    fun getDrop() = Minecraft.getMinecraft().gameSettings.keyBindDrop.isKeyDown
    fun getYaw() = Minecraft.getMinecraft().thePlayer.rotationYaw
    fun getPitch() = Minecraft.getMinecraft().thePlayer.rotationPitch

    fun setForward(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode, b)
    fun setLeft(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode, b)
    fun setBackward(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindBack.keyCode, b)
    fun setRight(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode, b)
    fun setSneak(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode, b)
    fun setJump(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode, b)
    fun setSprint(b: Boolean) = KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.keyCode, b)
    fun setYaw(f: Float) { Minecraft.getMinecraft().thePlayer.rotationYaw = f }
    fun setPitch(f: Float) { Minecraft.getMinecraft().thePlayer.rotationPitch = f }

    fun attack() {
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.keyCode, true)
        Thread {
            Thread.sleep(100)
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.keyCode, false)
        }.start()
    }

    fun drop() {
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindDrop.keyCode, true)
        Thread {
            Thread.sleep(100)
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindDrop.keyCode, false)
        }.start()
    }

    fun use() {
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.keyCode, true)
        Thread {
            Thread.sleep(100)
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindUseItem.keyCode, false)
        }.start()
    }
}