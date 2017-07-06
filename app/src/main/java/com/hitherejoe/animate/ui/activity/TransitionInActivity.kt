package com.hitherejoe.animate.ui.activity

import android.os.Bundle
import android.transition.TransitionInflater

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_transition_in.*
class TransitionInActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_in)

        val transition = intent.getStringExtra(EXTRA_TRANSITION)
        when (transition) {
            TRANSITION_SLIDE_RIGHT -> {
                val transitionSlideRight = TransitionInflater.from(this).inflateTransition(R.transition.slide_right)
                window.enterTransition = transitionSlideRight
            }
            TRANSITION_SLIDE_BOTTOM -> {
                val transitionSlideBottom = TransitionInflater.from(this).inflateTransition(R.transition.slide_bottom)
                window.enterTransition = transitionSlideBottom
            }
            TRANSITION_FADE_FAST -> {
                val transitionFadeFast = TransitionInflater.from(this).inflateTransition(R.transition.fade_fast)
                window.enterTransition = transitionFadeFast
            }
            TRANSITION_FADE_SLOW -> {
                val transitionFadeSlow = TransitionInflater.from(this).inflateTransition(R.transition.fade_slow)
                window.enterTransition = transitionFadeSlow
            }
            TRANSITION_EXPLODE -> {
                val transitionExplode = TransitionInflater.from(this).inflateTransition(R.transition.explode)
                window.enterTransition = transitionExplode
            }
            TRANSITION_EXPLODE_BOUNCE -> {
                val transitionExplodeBounce = TransitionInflater.from(this).inflateTransition(R.transition.explode_bounce)
                window.enterTransition = transitionExplodeBounce
            }
        }

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        text_close.setOnClickListener { finishAfterTransition() }
    }



    companion object {

        val EXTRA_TRANSITION = "EXTRA_TRANSITION"
        val TRANSITION_FADE_FAST = "FADE_FAST"
        val TRANSITION_FADE_SLOW = "FADE_SLOW"
        val TRANSITION_SLIDE_RIGHT = "SLIDE_RIGHT"
        val TRANSITION_SLIDE_BOTTOM = "SLIDE_BOTTOM"
        val TRANSITION_EXPLODE = "EXPLODE"
        val TRANSITION_EXPLODE_BOUNCE = "EXPLODE_BOUNCE"
    }

}
