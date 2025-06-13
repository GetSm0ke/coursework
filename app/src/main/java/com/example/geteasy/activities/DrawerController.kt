package com.example.geteasy.activities

interface DrawerController {
    fun openDrawer()
    fun closeDrawer()
    fun isDrawerOpen(): Boolean
    fun setCurrentMenuItem(menuItemId: Int)
    fun getCurrentMenuItem(): Int
}