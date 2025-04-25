package com.example.geteasy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.geteasy.databinding.ActivityRemindersTabBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRemindersTabBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemindersTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        // Настройка кнопки меню
        binding.optionButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Настройка NavigationView
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_reminder -> {
                    Toast.makeText(this, "Reminder", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_daily -> {
                    Toast.makeText(this, "Daily", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_notes -> {
                    Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}