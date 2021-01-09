package br.iesb.mobile.firebaselogin.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.domain.ItemResult

/**
 * [RecyclerView.Adapter] that can display a [ItemResult].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(private val values: List<ItemResult>?) :
    RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        if(item != null) {
            holder.textView1.text = item.id
            holder.textView2.text = item.userId
            holder.textView3.text = item.title
            holder.textView4.text = item.body
        }
    }

    override fun getItemCount(): Int = values?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.textView1)
        val textView2: TextView = view.findViewById(R.id.textView2)
        val textView3: TextView = view.findViewById(R.id.textView3)
        val textView4: TextView = view.findViewById(R.id.textView4)

    }
}