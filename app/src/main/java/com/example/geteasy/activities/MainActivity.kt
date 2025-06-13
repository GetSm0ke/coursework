package com.example.geteasy.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.geteasy.R
import androidx.fragment.app.Fragment
import com.example.geteasy.fragments.RemindersFragment
import com.example.geteasy.fragments.NotesFragment
import com.example.geteasy.fragments.DailyFragment
import com.example.geteasy.fragments.SettingsFragment
import com.example.geteasy.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), DrawerController {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private var currentMenuItem: Int = R.id.nav_reminder

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Восстановление состояния
        savedInstanceState?.getInt("CURRENT_MENU_ITEM")?.let {
            currentMenuItem = it
            navView.setCheckedItem(it)
        }

        // Стартовый фрагмент
        if (savedInstanceState == null) {
            loadFragment(RemindersFragment(), addToBackStack = false)
            navView.setCheckedItem(R.id.nav_reminder)
        }

        // Настройка NavigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId != currentMenuItem) {
                currentMenuItem = menuItem.itemId
                when (menuItem.itemId) {
                    R.id.nav_reminder -> loadFragment(RemindersFragment())
                    R.id.nav_daily -> loadFragment(DailyFragment())
                    R.id.nav_notes -> loadFragment(NotesFragment())
                    R.id.nav_settings -> loadFragment(SettingsFragment())
                }
            }
            closeDrawer()
            true
        }
    }

    // Реализация интерфейса DrawerController
    override fun openDrawer() {
        if (!isDrawerOpen()) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun closeDrawer() {
        if (isDrawerOpen()) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun isDrawerOpen(): Boolean {
        return drawerLayout.isDrawerOpen(GravityCompat.START)
    }

    override fun setCurrentMenuItem(menuItemId: Int) {
        currentMenuItem = menuItemId
        binding.navView.setCheckedItem(menuItemId)
    }

    override fun getCurrentMenuItem(): Int {
        return currentMenuItem
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .apply { if (addToBackStack) addToBackStack(null) }
            .commit()
    }

    override fun onBackPressed() {
        when {
            isDrawerOpen() -> closeDrawer()
            supportFragmentManager.backStackEntryCount > 1 -> super.onBackPressed()
            else -> finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CURRENT_MENU_ITEM", currentMenuItem)
    }
}