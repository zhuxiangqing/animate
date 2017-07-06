package com.hitherejoe.animate.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.transition.ArcMotion
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator

import com.hitherejoe.animate.R
import com.hitherejoe.animate.util.MorphButtonToDialog
import com.hitherejoe.animate.util.MorphDialogToButton
import com.hitherejoe.animate.util.MorphDialogToFab
import com.hitherejoe.animate.util.MorphFabToDialog

class PopupActivity : Activity() {

    internal var isDismissing = false
    private var container: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        val type = intent.getStringExtra(EXTRA_MORPH_TYPE)
        if (type == MORPH_TYPE_BUTTON) {
            setupSharedElementTransitionsButton(this, container)
        } else if (type == MORPH_TYPE_FAB) {
            setupSharedElementTransitionsFab(this, container,
                    resources.getDimensionPixelSize(R.dimen.dialog_corners))
        }
        container = findViewById<View>(R.id.container) as ViewGroup
    }

    fun setupSharedElementTransitionsFab(activity: Activity,
                                         target: View?,
                                         dialogCornerRadius: Int) {
        val arcMotion = ArcMotion()
        arcMotion.minimumHorizontalAngle = 50f
        arcMotion.minimumVerticalAngle = 50f
        val color = ContextCompat.getColor(activity, R.color.accent)
        val easeInOut = AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in)
        val sharedEnter = MorphFabToDialog(color, dialogCornerRadius)
        sharedEnter.pathMotion = arcMotion
        sharedEnter.interpolator = easeInOut
        val sharedReturn = MorphDialogToFab(color)
        sharedReturn.pathMotion = arcMotion
        sharedReturn.interpolator = easeInOut
        if (target != null) {
            sharedEnter.addTarget(target)
            sharedReturn.addTarget(target)
        }
        activity.window.sharedElementEnterTransition = sharedEnter
        activity.window.sharedElementReturnTransition = sharedReturn
    }

    fun setupSharedElementTransitionsButton(activity: Activity,
                                            target: View?) {
        val arcMotion = ArcMotion()
        arcMotion.minimumHorizontalAngle = 50f
        arcMotion.minimumVerticalAngle = 50f
        val color = ContextCompat.getColor(activity, R.color.accent)
        val easeInOut = AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in)
        val sharedEnter = MorphButtonToDialog(color)
        sharedEnter.pathMotion = arcMotion
        sharedEnter.interpolator = easeInOut
        val sharedReturn = MorphDialogToButton(color)
        sharedReturn.pathMotion = arcMotion
        sharedReturn.interpolator = easeInOut
        if (target != null) {
            sharedEnter.addTarget(target)
            sharedReturn.addTarget(target)
        }
        activity.window.sharedElementEnterTransition = sharedEnter
        activity.window.sharedElementReturnTransition = sharedReturn
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    fun dismiss(view: View?) {
        isDismissing = true
        setResult(Activity.RESULT_CANCELED)
        finishAfterTransition()
    }

    override fun onBackPressed() {
        dismiss(null)
    }

    companion object {

        val EXTRA_MORPH_TYPE = "morph_type"
        val MORPH_TYPE_BUTTON = "morph_type_button"
        val MORPH_TYPE_FAB = "morph_type_fab"

        fun getStartIntent(context: Context, type: String): Intent {
            val intent = Intent(context, PopupActivity::class.java)
            intent.putExtra(EXTRA_MORPH_TYPE, type)
            return intent
        }
    }

}