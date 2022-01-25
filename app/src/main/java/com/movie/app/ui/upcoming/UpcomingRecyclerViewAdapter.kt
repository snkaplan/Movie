package com.movie.app.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.app.databinding.UpcomingRecyclerViewItemBinding
import com.movie.domain.model.Movie
import java.util.*

class UpcomingRecyclerViewAdapter(
private val upcomingList: List<Movie>,
private val clickListener: (Movie, Int) -> Unit,
) : RecyclerView.Adapter<UpcomingRecyclerViewAdapter.MViewHolder>() {
    private lateinit var itemBinding: UpcomingRecyclerViewItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        itemBinding =
            UpcomingRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return MViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(upcomingList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    inner class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(upcomingMovie: Movie, clickListener: (Movie, Int) -> Unit) {
            itemBinding.upcomingMovieOverviewTv.text = upcomingMovie.overview
            itemBinding.upcomingMovieTitleTv.text = upcomingMovie.title
            itemBinding.upcomingReleaseDate.text = upcomingMovie.releaseDate
            itemBinding.upcomingMovieRvItem.setOnClickListener {
                clickListener(upcomingMovie, adapterPosition)
            }
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w185/${upcomingMovie.posterPath}")
                .into(itemBinding.upcomingMovieImage)
        }
    }
}