package de.tomjuri.movrec

import de.tomjuri.movrec.Util.attack
import de.tomjuri.movrec.Util.drop
import de.tomjuri.movrec.Util.getAttack
import de.tomjuri.movrec.Util.getBackward
import de.tomjuri.movrec.Util.getDrop
import de.tomjuri.movrec.Util.getForward
import de.tomjuri.movrec.Util.getJump
import de.tomjuri.movrec.Util.getLeft
import de.tomjuri.movrec.Util.getPitch
import de.tomjuri.movrec.Util.getRight
import de.tomjuri.movrec.Util.getSneak
import de.tomjuri.movrec.Util.getSprint
import de.tomjuri.movrec.Util.getUse
import de.tomjuri.movrec.Util.getYaw
import de.tomjuri.movrec.Util.setBackward
import de.tomjuri.movrec.Util.setForward
import de.tomjuri.movrec.Util.setJump
import de.tomjuri.movrec.Util.setLeft
import de.tomjuri.movrec.Util.setPitch
import de.tomjuri.movrec.Util.setRight
import de.tomjuri.movrec.Util.setSneak
import de.tomjuri.movrec.Util.setSprint
import de.tomjuri.movrec.Util.setYaw
import de.tomjuri.movrec.Util.use
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent
import java.io.File

object MovementRecorder {

    private var init = false

    private var recording = false
    private var playing = false

    private var file: File? = null
    private val movements = mutableListOf<TickMovement>()

    private var startingYaw = 0f
    private var startingPitch = 0f

    fun record(destFile: File) {
        if(!init) init()
        movements.clear()
        file = destFile
        startingYaw = getYaw()
        startingPitch = getPitch()
        recording = true
    }

    fun stopRecording() {
        recording = false
        if(file == null) return
        file!!.parentFile.mkdirs()
        file!!.createNewFile()
        file!!.writeText(movements.joinToString("\n"))
    }

    fun play(file: File) = play(file.readLines())

    fun play(mov: List<String>) {
        movements.clear()
        mov.forEach { movements.add(TickMovement.fromString(it)) }
        playing = true
    }

    private fun init() {
        MinecraftForge.EVENT_BUS.register(this)
        init = true
    }

    @SubscribeEvent
    fun onTickRecord(event: ClientTickEvent) {
        if(!recording) return
        movements.add(TickMovement(startingYaw - getYaw(), startingPitch - getPitch(), getForward(), getLeft(), getBackward(), getRight(), getJump(), getSneak(), getSprint(), false, false, false, ""))
    }

    @SubscribeEvent
    fun onTickPlay(event: ClientTickEvent) {
        if(!playing) return
        if(movements.isEmpty()) {
            playing = false
            return
        }
        val movement = movements.removeAt(0)
        setYaw(startingYaw - movement.yaw)
        setPitch(startingPitch - movement.pitch)
        setForward(movement.forward)
        setLeft(movement.left)
        setBackward(movement.back)
        setRight(movement.right)
        setJump(movement.jump)
        setSneak(movement.sneak)
        setSprint(movement.sprint)
        if(movement.attack) attack()
        if(movement.use) use()
        if(movement.drop) drop()
    }

    @SubscribeEvent
    fun onKey(event: KeyInputEvent) {
        if(!recording || movements.lastOrNull() == null) return
        if(getAttack()) movements.last().attack = true
        if(getUse()) movements.last().use = true
        if(getDrop()) movements.last().drop = true
    }
}