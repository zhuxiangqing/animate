package com.hitherejoe.animate.ui.activity

import android.animation.Animator
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ArrayAdapter

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_interpolator.*


class InterpolatorActivity : BaseActivity() {


    private var mIsButtonAtTop: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interpolator)
        mIsButtonAtTop = true
        setupSpinnerAdapter()
        text_animate.setOnClickListener { animate() }
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupSpinnerAdapter() {
        val adapter = ArrayAdapter(
                this, R.layout.item_spinner, resources.getStringArray(R.array.interpolators))
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner_interpolators!!.adapter = adapter
    }

    fun animate() {
        val padding = fab_interpolator!!.paddingBottom + fab_interpolator!!.paddingTop
        var height = layout_root!!.height - padding

        val actionBar = supportActionBar
        if (actionBar != null) height -= actionBar.height

        if (!mIsButtonAtTop) height = -height
        mIsButtonAtTop = !mIsButtonAtTop
        fab_interpolator!!.animate().setInterpolator(selectedInterpolator)
                .setDuration(500)
                .setStartDelay(200)
                .translationYBy(height.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        text_animate!!.isEnabled = false
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        text_animate!!.isEnabled = true
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
    }

    private val selectedInterpolator: Interpolator?
        get() {
            when (spinner_interpolators!!.selectedItemPosition) {
                1 -> return FastOutLinearInInterpolator()
                2 -> return FastOutSlowInInterpolator()
                3 -> return LinearOutSlowInInterpolator()
                4 -> return AccelerateDecelerateInterpolator()
                5 -> return AccelerateInterpolator()
                6 -> return DecelerateInterpolator()
                7 -> return AnticipateInterpolator()
                8 -> return AnticipateOvershootInterpolator()
                9 -> return BounceInterpolator()
                10 -> return LinearInterpolator()
                11 -> return OvershootInterpolator()
                else -> return null
            }
        }
}
