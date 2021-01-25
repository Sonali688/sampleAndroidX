package com.sample.networkcallngsample

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.networkcallngsample.adapter.RepoListAdapter
import com.sample.networkcallngsample.adapter.RepoListImageAdapter
import com.sample.networkcallngsample.network.RepositoryRetriever
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewRepo.layoutManager = LinearLayoutManager(this)


        val dividerItemDecoration : DividerItemDecoration = DividerItemDecoration(
            this,
            (recyclerViewRepo.layoutManager as LinearLayoutManager).orientation
        )
        recyclerViewRepo.addItemDecoration(dividerItemDecoration)



        if (isNetworkConnected()) {
            retrieveRepositories()
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    fun retrieveRepositories() {
        //1 Create a Coroutine scope using a job to be able to cancel when needed
        val mainActivityJob = Job()

        //2 Handle exceptions if any
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        //3 the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch {
            val resultList = RepositoryRetriever().getRepositories()
            //recyclerViewRepo.adapter = RepoListAdapter(resultList)
            recyclerViewRepo.adapter = RepoListImageAdapter(resultList)

        }
        /*coroutineScope.launch(errorHandler) {
            //4
            val resultList = RepositoryRetriever().getRepositories()
            repoList.adapter = RepoListAdapter(resultList)
        }*/
    }

    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}