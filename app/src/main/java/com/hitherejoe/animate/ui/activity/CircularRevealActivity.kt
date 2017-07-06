package com.hitherejoe.animate.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Interpolator

import com.hitherejoe.animate.ui.fragment.CircularRevealedFragment
import com.hitherejoe.animate.R
import com.hitherejoe.animate.util.ApiLevelHelper

import kotlinx.android.synthetic.main.activity_circular_reveal.*

class CircularRevealActivity : BaseActivity() {


    private var mCircularReveal: Animator? = null
    private var mFragment: Fragment? = null
    private var mInterpolator: Interpolator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal)
        mInterpolator = FastOutSlowInInterpolator()

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        fab_reveal.setOnClickListener { onFabClick(it) }
    }

    override fun onBackPressed() {
        if (mFragment != null) {
            val manager = fragmentManager
            val trans = manager.beginTransaction()
            trans.remove(mFragment)
            trans.commit()
            manager.popBackStack()
            mFragment = null

            fab_reveal!!.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setListener(null)
                    .start()
        } else {
            super.onBackPressed()
        }
    }


    fun onFabClick(view: View) {
        mFragment = CircularRevealedFragment()
        fragmentManager.beginTransaction()
                .replace(frame_container!!.id, mFragment).commit()
        revealFragmentContainer(view, frame_container)
    }

    private fun revealFragmentContainer(clickedView: View, fragmentContainer: View) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            revealFragmentContainerLollipop(clickedView, fragmentContainer)
        } else {
            fragmentContainer.visibility = View.VISIBLE
            clickedView.visibility = View.GONE
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun revealFragmentContainerLollipop(clickedView: View,
                                                fragmentContainer: View) {
        prepareCircularReveal(clickedView, fragmentContainer)
        clickedView.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setInterpolator(mInterpolator)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}

                    override fun onAnimationEnd(animation: Animator) {
                        fragmentContainer.visibility = View.VISIBLE
                        mCircularReveal!!.start()
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun prepareCircularReveal(startView: View, targetView: View) {
        val centerX = (startView.left + startView.right) / 2
        val centerY = (startView.top + startView.bottom) / 2
        val finalRadius = Math.hypot(centerX.toDouble(), centerY.toDouble()).toFloat()
        mCircularReveal = ViewAnimationUtils.createCircularReveal(
                targetView, centerX, centerY, 0f, finalRadius)
        mCircularReveal!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mCircularReveal!!.removeListener(this)
            }
        })
    }
}
