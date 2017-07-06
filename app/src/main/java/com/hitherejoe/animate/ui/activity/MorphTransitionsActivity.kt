package com.hitherejoe.animate.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_morph_transitions.*

class MorphTransitionsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_morph_transitions)
        setupToolbar()
        button_morph.setOnClickListener { onButtonClick(it) }
        fab_morph.setOnClickListener { onFabClick(it) }
    }

    private fun setupToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onButtonClick(view: View) {
        val login = PopupActivity.getStartIntent(this, PopupActivity.MORPH_TYPE_BUTTON)
        val options = ActivityOptions.makeSceneTransitionAnimation(this@MorphTransitionsActivity, view, getString(R.string.transition_morph_view))
        startActivity(login, options.toBundle())
    }

    fun onFabClick(view: View) {
        val login = PopupActivity.getStartIntent(this, PopupActivity.MORPH_TYPE_FAB)
        val options = ActivityOptions.makeSceneTransitionAnimation(this@MorphTransitionsActivity, view, getString(R.string.transition_morph_view))
        startActivity(login, options.toBundle())
    }
}
