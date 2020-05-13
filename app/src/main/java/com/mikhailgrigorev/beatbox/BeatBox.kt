package com.mikhailgrigorev.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException


class BeatBox(context: Context) {
    private val mAssets: AssetManager
    private val mSounds: MutableList<Sound> = ArrayList()
    private val mSoundPool: SoundPool
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
            try {
                val assetPath = "$SOUNDS_FOLDER/$filename"
                val sound = Sound(assetPath)
                load(sound)
                mSounds.add(sound)
            } catch (ioe: IOException){
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
    }

    @Throws(IOException::class)
    private fun load(sound: Sound) {
        val assetFd = mAssets.openFd(sound.assetPath)
        val soundId = mSoundPool.load(assetFd, 1)
        sound.soundId = soundId
    }

    fun release() {
        mSoundPool.release()
    }

    fun play(sound: Sound) {
        val soundId = sound.soundId ?: return
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    val sounds: List<Sound>
        get() = mSounds

    companion object {
        private const val TAG = "BeatBox"
        private const val MAX_SOUNDS = 5
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    init {
        mAssets = context.assets
        mSoundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)
        loadSounds()
    }
}
