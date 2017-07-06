package com.hitherejoe.animate.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_window_transitions.*


class WindowTransitionsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_transitions)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        text_fade_fast.setOnClickListener { startFadeFastTransition() }
        text_fade_slow.setOnClickListener { startFadeSlowTransition() }
        text_slide_right.setOnClickListener { startSlideRightTransition() }
        text_slide_bottom.setOnClickListener { startSlideBottomTransition() }
        text_explode.setOnClickListener { startExplodeTransition() }
        text_explode_bounce.setOnClickListener { startExplodeBounceTransition() }
    }

    fun startFadeFastTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_FAST)
        startActivityWithOptions(intent)
    }

    fun startFadeSlowTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_SLOW)
        startActivityWithOptions(intent)
    }

    fun startSlideRightTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_SLIDE_RIGHT)
        startActivityWithOptions(intent)
    }

    fun startSlideBottomTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_SLIDE_BOTTOM)
        startActivityWithOptions(intent)
    }

    fun startExplodeTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_EXPLODE)
        startActivityWithOptions(intent)
    }

    fun startExplodeBounceTransition() {
        val intent = Intent(this@WindowTransitionsActivity, TransitionInActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_EXPLODE_BOUNCE)
        startActivityWithOptions(intent)
    }

    private fun startActivityWithOptions(intent: Intent) {
        val transitionActivity = ActivityOptions.makeSceneTransitionAnimation(this@WindowTransitionsActivity)
        startActivity(intent, transitionActivity.toBundle())
    }

}
