package com.mikhailgrigorev.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.IOException

class BeatBox(context: Context) {
    private val mAssets: AssetManager
    private val mSounds: MutableList<Sound> = ArrayList()
    private fun loadSounds() {
        val soundNames: Array<String>?
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER)
            Log.i(TAG, "Found " + soundNames!!.size + " sounds")
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
            return
        }
        for (filename in soundNames) {
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            mSounds.add(sound)
        }
    }

    val sounds: List<Sound>
        get() = mSounds

    companion object {
        private const val TAG = "BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    init {
        mAssets = context.assets
        loadSounds()
    }
}
