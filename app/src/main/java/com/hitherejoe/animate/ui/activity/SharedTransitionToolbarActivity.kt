package com.hitherejoe.animate.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.Gravity

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_shared_transition_in.*

class SharedTransitionToolbarActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val slide = Slide(Gravity.BOTTOM)
        slide.addTarget(R.id.text_detail)
        slide.addTarget(R.id.text_close)
        slide.addTarget(R.id.view_separator)
        window.enterTransition = slide
        setContentView(R.layout.activity_shared_transition_in)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        text_close.setOnClickListener {     finishAfterTransition() }
    }



}
