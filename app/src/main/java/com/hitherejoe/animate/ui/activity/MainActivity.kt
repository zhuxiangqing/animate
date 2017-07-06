package com.hitherejoe.animate.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager


import com.hitherejoe.animate.R
import com.hitherejoe.animate.ui.adapter.AnimationApisAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recycler_animation_apis!!.layoutManager = LinearLayoutManager(this)
        val apiArray = resources.getStringArray(R.array.animation_apis)
        val animationApisAdapter = AnimationApisAdapter(apiArray, onRecyclerItemClick)
        recycler_animation_apis!!.adapter = animationApisAdapter
    }

    private val onRecyclerItemClick = AnimationApisAdapter.OnRecyclerItemClick { position ->
        var intent: Intent? = null
        when (position) {
            0 -> intent = Intent(
                    this@MainActivity, ViewPropertyAnimatorActivity::class.java)
            1 -> intent = Intent(this@MainActivity, ObjectAnimatorActivity::class.java)
            2 -> intent = Intent(this@MainActivity, InterpolatorActivity::class.java)
            3 -> intent = Intent(this@MainActivity, CircularRevealActivity::class.java)
            4 -> intent = Intent(this@MainActivity, MorphTransitionsActivity::class.java)
            5 -> intent = Intent(this@MainActivity, SharedTransitionsActivity::class.java)
            6 -> intent = Intent(this@MainActivity, WindowTransitionsActivity::class.java)
            7 -> intent = Intent(
                    this@MainActivity, WindowTransitionsActivityExplode::class.java)
            8 -> intent = Intent(
                    this@MainActivity, AnimatedVectorDrawablesActivity::class.java)
        }
        if (intent != null) startActivity(intent)
    }
}
