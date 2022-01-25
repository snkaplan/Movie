package com.movie.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.movie.app.common.showFragment
import com.movie.app.routing.AppFragmentNavigator
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

abstract class BaseFragment : Fragment(), KodeinAware {
  override val kodein by closestKodein()
  protected val appFragmentNavigator: AppFragmentNavigator by instance()
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(getLayout(), container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewReady()
  }
  
  protected fun onBackPressed() = (activity as BaseActivity).onBackPressed()
  
  abstract fun viewReady()
  
  abstract fun getLayout(): Int
  
  open fun showError(@StringRes errorMessage: Int, rootView: View) {
    (activity as BaseActivity).showError(errorMessage, rootView)
  }
  
  open fun showError(errorMessage: String?, rootView: View) {
    (activity as BaseActivity).showError(errorMessage, rootView)
  }
  
  open fun showLoading(progressBar: ProgressBar) {
    (activity as BaseActivity).showLoading(progressBar)
  }
  
  open fun hideLoading(progressBar: ProgressBar) {
    (activity as BaseActivity).hideLoading(progressBar)
  }

  fun addFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
    activity?.showFragment(fragment, containerId, addToBackStack)
  }
}