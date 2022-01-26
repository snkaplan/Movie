package com.movie.app.ui.main

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.movie.app.ui.base.BaseActivity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.movie.app.R


class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment!!.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onBackPressed() {
        val id = navController.currentDestination?.id
        if(id != R.id.main_fragment){
            NavigationUI.navigateUp(navController, null)
        } else {
            finish()
        }
        super.onBackPressed()
    }
}