package com.movie.app.ui.nowplaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.app.databinding.NowPlayingRecyclerViewItemBinding
import com.movie.app.databinding.UpcomingRecyclerViewItemBinding
import com.movie.domain.model.Movie
import kotlin.collections.ArrayList

class NowPlayingRecyclerViewAdapter(
    private val nowPlayingList: ArrayList<Movie>,
    private val clickListener: (Movie, Int) -> Unit,
) : RecyclerView.Adapter<NowPlayingRecyclerViewAdapter.MViewHolder>() {
    private lateinit var itemBinding: NowPlayingRecyclerViewItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        itemBinding =
            NowPlayingRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return MViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(nowPlayingList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return nowPlayingList.size
    }

    fun reloadList(newItems: ArrayList<Movie>) {
        nowPlayingList.addAll(newItems)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(nowPlayingMovie: Movie, clickListener: (Movie, Int) -> Unit) {
            itemBinding.titleTv.text = nowPlayingMovie.title
            itemBinding.overviewTv.text = nowPlayingMovie.overview
            itemBinding.nowPlayingMovieRvItem.setOnClickListener {
                clickListener(nowPlayingMovie, adapterPosition)
            }
            nowPlayingMovie.posterPath?.let {
                Glide.with(view)
                    .load("https://image.tmdb.org/t/p/w185/${it}")
                    .into(itemBinding.posterIv)
            }
        }
    }
}