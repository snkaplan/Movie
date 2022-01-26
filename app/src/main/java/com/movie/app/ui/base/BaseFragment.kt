package com.movie.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.movie.app.common.showFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

abstract class BaseFragment : Fragment(), KodeinAware {
  override val kodein by closestKodein()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(getLayout(), container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewReady()
  }

    abstract fun viewReady()
  
  abstract fun getLayout(): Int

    fun addFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
    activity?.showFragment(fragment, containerId, addToBackStack)
  }
}