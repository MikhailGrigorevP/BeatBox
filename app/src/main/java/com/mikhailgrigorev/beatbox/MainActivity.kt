package com.mikhailgrigorev.beatbox

import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment? {
        return BeatBoxFragment.newInstance()
    }
}
