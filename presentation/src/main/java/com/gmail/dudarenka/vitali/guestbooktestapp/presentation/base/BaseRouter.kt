package com.gmail.dudarenka.vitali.guestbooktestapp.presentation.base

import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast

abstract class BaseRouter<A : BaseActivity>(val activity: A) {
    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(e: Throwable) {
        Toast.makeText(activity, "Error " + e.toString(), Toast.LENGTH_SHORT).show()
        Log.e("AAA",e.toString())
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: BaseFragment, containerResId: Int, addToBackStack: Boolean = false) {
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, fragment::class.java.canonicalName)
        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }
        fragmentTransition.commit()
    }
}