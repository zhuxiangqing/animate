package com.hitherejoe.animate.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import com.hitherejoe.animate.R
import com.hitherejoe.animate.ui.adapter.GridAdapter

import kotlinx.android.synthetic.main.activity_window_transitions_explode.*
class WindowTransitionsActivityExplode : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_transitions_explode)
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        recycler_view_team!!.setHasFixedSize(true)
        val items = resources.getStringArray(R.array.items)
        recycler_view_team!!.adapter = GridAdapter(items, onRecyclerItemClick)
    }

    private val onRecyclerItemClick = GridAdapter.OnRecyclerItemClick { view ->
        val squareParticipant = Pair(view, ViewCompat.getTransitionName(view))
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@WindowTransitionsActivityExplode, squareParticipant)
        val intent = Intent(
                this@WindowTransitionsActivityExplode, SharedTransitionsInActionbarActivity::class.java)
        startActivity(intent, transitionActivityOptions.toBundle())
    }

}
