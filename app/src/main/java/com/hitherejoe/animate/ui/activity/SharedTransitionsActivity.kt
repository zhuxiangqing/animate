package com.hitherejoe.animate.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_shared_transitions.*

class SharedTransitionsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_transitions)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        text_shared_transition.setOnClickListener { startSharedTransition(it) }
        text_shared_toolbar.setOnClickListener { startToolbarTransition() }
    }

    fun startSharedTransition(view: View) {
        val intent = Intent(this@SharedTransitionsActivity, SharedTransitionInToolbarActivity::class.java)
        intent.putExtra(
                TransitionInActivity.EXTRA_TRANSITION, TransitionInActivity.TRANSITION_FADE_FAST)
        val participants = Pair(view, ViewCompat.getTransitionName(view))
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SharedTransitionsActivity, participants)
        ActivityCompat.startActivity(
                this@SharedTransitionsActivity, intent, transitionActivityOptions.toBundle())
    }

    fun startToolbarTransition() {
        val intent = Intent(this@SharedTransitionsActivity, SharedTransitionToolbarActivity::class.java)
        val squareParticipant = Pair<View, String>(text_shared_toolbar, ViewCompat.getTransitionName(text_shared_toolbar))
        val toolbarParticipants = Pair<View, String>(text_toolbar, ViewCompat.getTransitionName(text_toolbar))
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SharedTransitionsActivity, squareParticipant, toolbarParticipants)
        ActivityCompat.startActivity(
                this@SharedTransitionsActivity, intent, transitionActivityOptions.toBundle())
    }

}
