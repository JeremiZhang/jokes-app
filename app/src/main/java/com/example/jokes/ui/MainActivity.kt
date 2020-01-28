package com.example.jokes.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jokes.R
import com.example.jokes.model.Favorite
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_FAVORITE = "EXTRA_FAVORITE"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        btnJoke.setOnClickListener { viewModel.getJokes() }

        btnFavorites.setOnClickListener { toFavoriteActivity() }

        ivAddFavorite.setOnClickListener { addFavorite() }
    }

    private fun addFavorite() {
        if (tvJoke.text.toString().isNotBlank()
            && tvAnswer.text.toString().isNotBlank()
        ) {
            val favorite = Favorite(
                tvJoke.text.toString(),
                tvAnswer.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_FAVORITE, favorite)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
            toFavoriteActivity()
        } else {
            Toast.makeText(
                this, "No joke to favorite!"
                , Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun toFavoriteActivity() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivityForResult(intent, ADD_FAVORITE_REQUEST_CODE)
    }

    private fun initViewModel() {
        // Initialize the MainActivityViewModel.
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe the joke object.
        viewModel.joke.observe(this, Observer {
            tvJoke.text = it?.setup
            tvAnswer.text = it?.punchline
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


    companion object {
        const val ADD_FAVORITE_REQUEST_CODE = 100
    }
}
