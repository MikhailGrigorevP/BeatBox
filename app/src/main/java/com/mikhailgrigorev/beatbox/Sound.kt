package com.mikhailgrigorev.beatbox

class Sound(val assetPath: String) {
    val name: String
    var soundId: Int? = null

    init {
        val components = assetPath.split("/".toRegex()).toTypedArray()
        val filename = components[components.size - 1]
        name = filename.replace(".wav", "")
    }
}
