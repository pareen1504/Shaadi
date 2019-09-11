package com.pd.wifilogging.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.pd.shaadi.view.CreateDialog
import com.pd.shaadi.view.MainActivity


fun MainActivity.LoadMoreData() {
    viewModel.getData(10)
    isLoading = false
}

fun AppCompatActivity.showAlert() {
    val createDialog = CreateDialog()
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    val prev: Fragment? = supportFragmentManager.findFragmentByTag("dialog")
    if (prev != null) {
        ft.remove(prev)
    }
    ft.addToBackStack(null)
    createDialog.show(ft, "dialog")
}