package com.hitherejoe.animate.ui.activity

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity

import com.hitherejoe.animate.R
import kotlinx.android.synthetic.main.activity_shared_transitions_in_actionbar.*

class SharedTransitionsInActionbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_transitions_in_actionbar)

        val slide = Slide(Gravity.BOTTOM)
        slide.addTarget(R.id.text_detail)
        slide.addTarget(R.id.text_close)
        slide.addTarget(R.id.view_separator)
        window.enterTransition = slide

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        text_close.setOnClickListener {    finishAfterTransition() }
    }



}
