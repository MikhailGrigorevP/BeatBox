package com.mikhailgrigorev.beatbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


abstract class SingleFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment?
    protected val layoutResId: Int
        protected get() = R.layout.activity_fragment

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        val manager: FragmentManager = supportFragmentManager
        var fragment: Fragment? =
            manager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            if (fragment != null) {
                manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }
}
