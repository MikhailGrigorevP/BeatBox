package com.mikhailgrigorev.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


class SoundViewModel(private val mBeatBox: BeatBox) : BaseObservable() {
    private var mSound: Sound? = null

    @get:Bindable
    val title: String
        get() = mSound!!.name

    var sound: Sound?
        get() = mSound
        set(sound) {
            mSound = sound
            notifyChange()
        }

}
