package com.movie.app.ui.main

import com.movie.app.R
import com.movie.app.ui.base.BaseFragment
import com.movie.app.ui.nowplaying.NowPlayingFragment
import com.movie.app.ui.upcoming.UpcomingFragment

class MainFragment : BaseFragment() {
    override fun viewReady() {
        addFragment(NowPlayingFragment(), R.id.now_playing_fragment_area, true)
        addFragment(UpcomingFragment(), R.id.upcoming_fragment_area, true)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }
}