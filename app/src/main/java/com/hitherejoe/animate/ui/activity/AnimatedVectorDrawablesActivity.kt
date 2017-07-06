package com.hitherejoe.animate.ui.activity

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle


import com.hitherejoe.animate.R

import kotlinx.android.synthetic.main.activity_animated_vector_drawables.*

class AnimatedVectorDrawablesActivity : BaseActivity() {





    private var mAddToRemoveDrawable: AnimatedVectorDrawable? = null
    private var mRemoveToAddDrawable: AnimatedVectorDrawable? = null
    private var mHeartToTwitterDrawable: AnimatedVectorDrawable? = null
    private var mTwitterToHeartDrawable: AnimatedVectorDrawable? = null

    private var mIsAddState: Boolean = false
    private var mIsTwitterState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_vector_drawables)
        setupToolbar()

        mAddToRemoveDrawable = getDrawable(R.drawable.avd_add_to_remove) as AnimatedVectorDrawable
        mRemoveToAddDrawable = getDrawable(R.drawable.avd_remove_to_add) as AnimatedVectorDrawable
        mHeartToTwitterDrawable = getDrawable(R.drawable.avd_heart_to_twitter) as AnimatedVectorDrawable
        mTwitterToHeartDrawable = getDrawable(R.drawable.avd_twitter_to_heart) as AnimatedVectorDrawable

        mIsAddState = true
        mIsTwitterState = true
        image_add_remove.setOnClickListener{onAddRemoveImageClick()}
        image_twitter_heart.setOnClickListener { onTwitterHeartImageClick() }
    }

    private fun setupToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onAddRemoveImageClick() {
        val drawable = if (mIsAddState) mRemoveToAddDrawable else mAddToRemoveDrawable
        image_add_remove.setImageDrawable(drawable)
        if (drawable != null) {
            drawable.start()
        }
        mIsAddState = !mIsAddState
    }

    fun onTwitterHeartImageClick() {
        val drawable = if (mIsTwitterState) mHeartToTwitterDrawable else mTwitterToHeartDrawable
        image_twitter_heart!!.setImageDrawable(drawable)
        if (drawable != null) {
            drawable.start()
        }
        mIsTwitterState = !mIsTwitterState
    }

}
