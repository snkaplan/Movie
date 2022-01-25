package com.movie.app

import android.app.Application
import com.movie.app.di.presentationModule
import com.movie.data.di.databaseModule
import com.movie.data.di.networkModule
import com.movie.data.di.repositoryModule
import com.movie.domain.di.interactionModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

@Suppress("unused")
class MovieApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieApp)) // produces us a context
        import(repositoryModule)
        import(databaseModule)
        import(networkModule)
        import(presentationModule)
        import(interactionModule)
    }
}