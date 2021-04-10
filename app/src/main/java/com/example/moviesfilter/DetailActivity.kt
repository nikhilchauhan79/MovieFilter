package com.example.moviesfilter

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.movie_card.*

class DetailActivity : AppCompatActivity() {
//    lateinit var textViewTitle: TextView
//    lateinit var textViewYear: TextView
//    lateinit var textViewRank: TextView
//    lateinit var textViewRating: TextView
//    lateinit var textViewGenres: TextView
//    lateinit var textViewActors: TextView
//    lateinit var textViewPlot: TextView
//    lateinit var textViewRunningTime: TextView
//    lateinit var textViewReleaseDate: TextView
//    lateinit var textViewDirectors: TextView
//    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        imageView=findViewById(R.id.image_view_detail)
//        textView=findViewById(R.id.text_view_detail)
        val bundle: Bundle? = intent.extras
        val id = bundle?.get("id_value")
        val language = bundle?.get("language_value")

        val imageUrl=bundle?.get("imageUrl")
        val title=bundle?.get("title")
        val year=bundle?.get("year")
        val rank=bundle?.get("rank")
        val rating=bundle?.get("rating")
        val genres=bundle?.get("genres")
        val actors=bundle?.get("actors")
        val plot=bundle?.get("plot")

        val running_time_secs=bundle?.get("running_time_secs")
        val release_date=bundle?.get("release_date")
        val directors=bundle?.get("directors")

        Log.d(ContentValues.TAG, "onClick: "+rating)
        Log.d(ContentValues.TAG, "onClick: "+genres)
        Log.d(ContentValues.TAG, "onClick: "+directors)

        rating_detail.text = rating.toString()
        genre_detail.text = genres.toString()
        director_detail.text = directors.toString()
        release_date_detail.text = release_date.toString()
        runtime_detail.text = running_time_secs.toString()
        if(rank!=null) {
            text_view_rank.append(rank.toString())
        }
        if(rating!=null) {
            text_view_rating.append(rating.toString())
        }


        actors_detail.text = actors.toString()
        tv_plot.text = plot.toString()
        text_view_title.text = title.toString()
        text_view_year.text = year.toString()

        Glide.with(image_view_detail.context)
            .load(imageUrl)
            .centerCrop()
            .into(image_view_detail)




    }



}