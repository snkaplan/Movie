package com.movie.app

import android.os.Bundle
import com.movie.app.ui.base.BaseActivity
import com.movie.app.ui.nowplaying.NowPlayingFragment
import com.movie.app.ui.upcoming.UpcomingFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(NowPlayingFragment(), R.id.now_playing_fragment_area, true)
        addFragment(UpcomingFragment(), R.id.upcoming_fragment_area, true)
    }
}