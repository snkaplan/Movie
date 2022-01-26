package com.movie.app.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.app.common.TMDB_IMAGE_BASE_URL
import com.movie.app.databinding.UpcomingRecyclerViewItemBinding
import com.movie.domain.model.Movie
import kotlin.collections.ArrayList

class UpcomingRecyclerViewAdapter(
    private val upcomingList: ArrayList<Movie>,
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

    fun reloadList(newItems: ArrayList<Movie>) {
        upcomingList.addAll(newItems)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(upcomingMovie: Movie, clickListener: (Movie, Int) -> Unit) {
            itemBinding.upcomingMovieOverviewTv.text = upcomingMovie.overview
            itemBinding.upcomingMovieTitleTv.text = upcomingMovie.title
            itemBinding.upcomingReleaseDate.text = upcomingMovie.releaseDate
            itemBinding.upcomingMovieRvItem.setOnClickListener {
                clickListener(upcomingMovie, adapterPosition)
            }
            upcomingMovie.posterPath?.let {
                Glide.with(view)
                    .load("${TMDB_IMAGE_BASE_URL}${it}")
                    .into(itemBinding.upcomingMovieImage)
            }

        }
    }
}