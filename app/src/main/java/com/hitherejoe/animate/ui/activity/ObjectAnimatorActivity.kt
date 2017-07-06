package com.hitherejoe.animate.ui.activity

import android.os.Bundle
import android.widget.AdapterViewAnimator

import com.hitherejoe.animate.R
import com.hitherejoe.animate.ui.adapter.FrameAdapter
import kotlinx.android.synthetic.main.activity_object_animator.*

class ObjectAnimatorActivity : BaseActivity() {



    private var isAnimatingUp: Boolean = false
    private var mContentCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        isAnimatingUp = true
        mContentCount = 20
        flipper_content.adapter = FrameAdapter(this, mContentCount)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showNext() {
        if (mContentCount > 1) {
            setAnimations()
            flipper_content!!.showNext()
            isAnimatingUp = !isAnimatingUp
            mContentCount--
        } else {
            finish()
        }
    }

    private fun setAnimations() {
        flipper_content!!.setInAnimation(this, if (isAnimatingUp)
            R.animator.slide_in_bottom
        else
            R.animator.slide_in_left)
        flipper_content!!.setOutAnimation(this, if (isAnimatingUp)
            R.animator.slide_out_top
        else
            R.animator.slide_out_right)
    }

}
