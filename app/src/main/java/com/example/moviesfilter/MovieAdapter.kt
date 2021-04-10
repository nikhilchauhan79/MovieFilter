package com.example.moviesfilter

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesfilter.model.Movie
import java.util.*
import kotlin.collections.ArrayList


class MovieAdapter(val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(), Filterable {

    var movieFilterList: ArrayList<Movie>
    init {
        movieFilterList =  movieList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        init {
//            if (clickListener != null) {
//                itemView.setOnClickListener(this)
//            }
//        }

        fun bindItems(movie: Movie) {
            val textViewTitle = itemView.findViewById(R.id.title) as TextView
            val textViewYear = itemView.findViewById(R.id.year) as TextView
            val imageView = itemView.findViewById(R.id.image_view) as ImageView
            val textViewRank = itemView.findViewById(R.id.rank) as TextView
            val textViewRating = itemView.findViewById(R.id.rating) as TextView
            val textViewGenre = itemView.findViewById(R.id.genre) as TextView
            val textViewActors = itemView.findViewById(R.id.actors) as TextView
            val textViewPlot = itemView.findViewById(R.id.plot) as TextView
            val textViewDirectors = itemView.findViewById(R.id.director) as TextView

            textViewTitle.text = movie.title
            textViewYear.text = movie.year.toString()
            textViewRank.text = movie.info.rank.toString()
            textViewRating.text = movie.info.rating.toString()
            textViewGenre.text = movie.info.genres.toString()
            textViewActors.text = movie.info.actors.toString()
            textViewPlot.text = movie.info.plot
            textViewDirectors.text = movie.info.directors.toString()

            Glide.with(imageView.context)
                .load(movie.info.image_url)
                .into(imageView)

            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {

                    val pos = adapterPosition

                    // check if item still exists

                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        val clickedDataItem: Movie = movieList.get(pos)

                        val title = clickedDataItem.title
                        val year = clickedDataItem.year
                        val imageUrl = clickedDataItem.info.image_url
                        val rank = clickedDataItem.info.rank
                        val rating = clickedDataItem.info.rating
                        val genres = clickedDataItem.info.genres
                        val actors = clickedDataItem.info.actors
                        val release_date = clickedDataItem.info.release_date
                        val plot = clickedDataItem.info.plot
                        val directors = clickedDataItem.info.directors
                        val running_time_secs = clickedDataItem.info.running_time_secs


                        val intent = Intent(v?.context, DetailActivity::class.java)
                        intent.putExtra("imageUrl", imageUrl)
                        intent.putExtra("title", title)
                        intent.putExtra("year", year)
                        intent.putExtra("rank", rank)
                        intent.putExtra("rating", rating)
                        intent.putExtra("genres", genres)
                        intent.putExtra("actors", actors)
                        intent.putExtra("plot", plot)
                        intent.putExtra("running_time_secs", running_time_secs)
                        intent.putExtra("release_date", release_date)
                        intent.putExtra("directors", directors)

                        itemView.context.startActivity(intent)
                    }


                }

            })
        }


//        override fun onClick(v: View?) {
//            if (v != null) {
//                clickListener?.onItemClick(v,adapterPosition)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieFilterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movieFilterList[position])
    }

    fun addMovies(movies: List<Movie>) {

        this.movieList.apply {
            clear()
            addAll(movies)
        }

//        this.movieFilterList.apply {
//            clear()
//            addAll(movies)
//        }



    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    movieFilterList = movieList
                } else {
                    val resultList = ArrayList<Movie>()
                    for (row: Movie in movieList) {
                        if(row.info.genres!=null) {
                            if (row.info.genres.toString().toLowerCase(Locale.ROOT)
                                    .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(row)
                            }
                        }

                        if(row.info.directors!=null) {
                            if (row.info.directors.toString().toLowerCase(Locale.ROOT)
                                    .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(row)
                            }
                        }


                    }
                    movieFilterList=resultList
                }
                val filterResults = FilterResults()
                filterResults.values = movieFilterList
//                Log.d(TAG, "performFiltering: "+movieFilterList)
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.d("res", "publishResults: "+results)
                Log.d(TAG, "publishResults: "+movieFilterList)

//                movieFilterList = results?.values as ArrayList<Movie>

                results?.values?.let{
                    movieFilterList = results?.values as ArrayList<Movie>
                    notifyDataSetChanged()

                }

            }
        }
    }

//    interface ClickListener {
//        fun onItemClick(v: View,position: Int)
//    }
//
//    fun setOnItemClickListener(clickListener: ClickListener) {
//        this.clickListener = clickListener
//    }


}