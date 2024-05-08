package com.example.bitfitpt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val dashboardFragment: DashboardFragment = DashboardFragment.newInstance(application)
        val foodListFragment: FoodListFragment = FoodListFragment.newInstance(application)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_dashboard -> fragment = dashboardFragment
                R.id.nav_logs -> fragment = foodListFragment
            }
            fragmentManager.beginTransaction().replace(R.id.clContainer, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_logs

        val addNewFoodBtn = findViewById<Button>(R.id.addFoodBtn)
        addNewFoodBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            this.startActivity(intent)
            dashboardFragment.update(foodListFragment.foods)
        }
    }
}