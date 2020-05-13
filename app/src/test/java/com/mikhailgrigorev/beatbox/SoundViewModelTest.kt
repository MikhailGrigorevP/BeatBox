package com.mikhailgrigorev.beatbox

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {
    private var mBeatBox: BeatBox? = null
    private var mSound: Sound? = null
    private var mSubject: SoundViewModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mBeatBox = mock(BeatBox::class.java)
        mSound = Sound("assetPath")
        mSubject = SoundViewModel(mBeatBox!!)
        mSubject!!.sound = mSound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(mSubject!!.title, `is`(mSound!!.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        mSound?.let { verify(mBeatBox)?.play(it) }
    }
}