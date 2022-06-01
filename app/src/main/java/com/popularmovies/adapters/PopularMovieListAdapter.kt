package com.popularmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.popularmovies.R
import com.popularmovies.adapters.PopularMovieListAdapter.ViewHolder
import com.popularmovies.models.Result
import com.popularmovies.utils.AppConstants

class PopularMovieListAdapter(private val context: Context, private val movieList: List<Result>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_popular_movies_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMovieName.text = movieList[position].title
        Glide.with(context)
            .load(AppConstants.BASE_URL + movieList[position].poster_path)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.ivPoster);
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvMovieName: TextView = view.findViewById<TextView>(R.id.tvMovieName)
        var ivPoster: ImageView = view.findViewById<ImageView>(R.id.ivPoster)
    }

}