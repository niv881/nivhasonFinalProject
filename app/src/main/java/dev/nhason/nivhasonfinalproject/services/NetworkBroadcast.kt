package dev.nhason.nivhasonfinalproject.services

import android.app.AlertDialog
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.LayoutInflater
import dev.nhason.nivhasonfinalproject.databinding.NoInternetConnectionLayoutBinding
import java.nio.file.attribute.AclEntry.Builder

class NetworkBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!isNetworkConnected(context)){
            val binding : NoInternetConnectionLayoutBinding = NoInternetConnectionLayoutBinding
                .inflate(LayoutInflater.from(context))
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setView(binding.root)
            builder.setCancelable(false)
            val dialog : Dialog = builder.create()
            dialog.show()

            binding.btnNoNetwork.setOnClickListener{
                if (isNetworkConnected(context))dialog.dismiss()
            }
        }
    }

     fun isNetworkConnected(context: Context) : Boolean {
        return try {
            val connectivityManager : ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo : NetworkInfo = connectivityManager.activeNetworkInfo!!
            return networkInfo != null && networkInfo.isConnected

        }catch (e : Exception){
            e.printStackTrace()
            false
        }
    }

}