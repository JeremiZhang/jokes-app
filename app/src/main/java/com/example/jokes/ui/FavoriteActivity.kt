package com.example.jokes.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.R
import com.example.jokes.model.Favorite
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private val favorites = arrayListOf<Favorite>()
    private val favoriteAdapter = FavoriteAdapter(favorites)
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        //Fills recycleviewer with data using the adapter
        rvFavorite.layoutManager =
            LinearLayoutManager(this@FavoriteActivity, RecyclerView.VERTICAL, false)
        rvFavorite.adapter = favoriteAdapter

        rvFavorite.addItemDecoration(
            DividerItemDecoration(
                this@FavoriteActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, ADD_FAVORITE_REQUEST_CODE)
        }

        createItemTouchHelper().attachToRecyclerView(rvFavorite)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe favorites from the view model, update the list when the data is changed
        viewModel.favorite.observe(this, Observer { favorites ->
            this@FavoriteActivity.favorites.clear()
            this@FavoriteActivity.favorites.addAll(favorites)
            favoriteAdapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_FAVORITE_REQUEST_CODE -> {
                    val favorite = data!!.getParcelableExtra<Favorite>(EXTRA_FAVORITE)
                    viewModel.insertFavorite(favorite)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedFavorite = favorites.removeAt(position)
                viewModel.deleteFavorite(deletedFavorite)
            }
        }
        return ItemTouchHelper(callback)
    }

    companion object {
        const val ADD_FAVORITE_REQUEST_CODE = 100
    }

}
