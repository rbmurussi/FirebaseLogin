package br.iesb.mobile.firebaselogin.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.util.Endpoint
import br.iesb.mobile.firebaselogin.util.NetworkUtils
import br.iesb.mobile.firebaselogin.view.adapter.MyItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
    }

    fun load() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.posts.execute().body()

        recycler_view.adapter = MyItemRecyclerViewAdapter(callback)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }


}