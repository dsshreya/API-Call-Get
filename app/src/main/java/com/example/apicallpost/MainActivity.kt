package com.example.apicallpost

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.apicallpost.fragments.MainFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InternetConnectionCheck.ConnectivityReceiverListener {

    var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetwork
        if (networkInfo != null) {
            inflateFragment()
        } else {
            val view = LayoutInflater.from(this)
                .inflate(R.layout.layout_no_internet, mainActivityFrame, false)
            mainActivityFrame.addView(view)
        }

        registerReceiver(
            InternetConnectionCheck(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

    }

    override fun onResume() {
        super.onResume()
        InternetConnectionCheck.connectivityReceiverListener = this
        /*val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(InternetConnectionCheck(), intentFilter)*/
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(InternetConnectionCheck())
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        val layout = layoutInflater
        if (!isConnected) {
            snackBar= Snackbar.make(mainActivityFrame, "You are offline", Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
        } else {
            snackBar?.dismiss()
            mainActivityFrame.removeAllViews()
            inflateFragment()
        }
    }

    private fun inflateFragment()
    {
        supportFragmentManager.beginTransaction().replace(
            R.id.mainActivityFrame,
            MainFragment()
        ).addToBackStack(null).commit()
    }
}
