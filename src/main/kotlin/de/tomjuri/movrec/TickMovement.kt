package de.tomjuri.movrec

data class TickMovement(
    var yaw: Float,
    var pitch: Float,
    var forward: Boolean,
    var left: Boolean,
    var back: Boolean,
    var right: Boolean,
    var jump: Boolean,
    var sneak: Boolean,
    var sprint: Boolean,
    var attack: Boolean,
    var use: Boolean,
    var drop: Boolean,
    var message: String
) {
    companion object {
        fun fromString(s: String) : TickMovement {
            val split = s.split(";")
            return TickMovement(
                split[0].toFloat(),
                split[1].toFloat(),
                split[2].toBoolean(),
                split[3].toBoolean(),
                split[4].toBoolean(),
                split[5].toBoolean(),
                split[6].toBoolean(),
                split[7].toBoolean(),
                split[8].toBoolean(),
                split[9].toBoolean(),
                split[10].toBoolean(),
                split[11].toBoolean(),
                split[12]
            )
        }
    }
    override fun toString() = "$yaw;$pitch;$forward;$left;$back;$right;$jump;$sneak;$sprint;$attack;$use;$drop;$message"
}