package br.iesb.mobile.firebaselogin.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.domain.ItemResult
import br.iesb.mobile.firebaselogin.util.Endpoint
import br.iesb.mobile.firebaselogin.util.NetworkUtils
import br.iesb.mobile.firebaselogin.view.adapter.MyItemRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class MainFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)
        val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.posts.execute().body()
        print(callback?.size)
        val values: List<ItemResult>? = callback
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(values)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}