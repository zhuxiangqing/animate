package com.hitherejoe.animate.ui.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.View
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
import android.widget.CheckBox
import android.widget.Spinner

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_view_properties.*

class ViewPropertyAnimatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_properties)
        setupSpinnerAdapter()

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        text_start_animation.setOnClickListener { buildAndStartAnimation(fab_smile) }
        text_reset_animations.setOnClickListener { onResetAnimationsClicked() }
    }

    private fun setupSpinnerAdapter() {
        val adapter = ArrayAdapter(
                this, R.layout.item_spinner, resources.getStringArray(R.array.interpolators))
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner_interpolators!!.adapter = adapter
    }

    fun onAnimateTextClicked() {
        buildAndStartAnimation(fab_smile)
    }

    fun onResetAnimationsClicked() {
        fab_smile!!.alpha = 1f
        fab_smile!!.scaleY = 1f
        fab_smile!!.scaleX = 1f
        fab_smile!!.translationZ = 1f
    }

    private fun buildAndStartAnimation(view: View) {
        val animator = view.animate()

        if (check_animate_alpha!!.isChecked || view.alpha == 0f) {
            val animationValue = if (view.alpha == 0f) 1f else 0f
            animator.alpha(animationValue)
        }
        if (check_animate_scale!!.isChecked) {
            val animationValue = if (view.scaleY == 0f) 1f else 0f
            animator.scaleX(animationValue).scaleY(animationValue)
        }
        if (check_animate_z!!.isChecked) {
            val animationValue = if (view.translationZ != 25f) 25f else 2f
            animator.translationZ(animationValue)
        }
        if (check_animation_duration!!.isChecked) {
            animator.duration = 500L
        }
        if (check_animation_delay!!.isChecked) {
            animator.startDelay = 500L
        }
        animator.interpolator = selectedInterpolator
        animator.start()
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
