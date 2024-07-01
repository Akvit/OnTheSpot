package com.azhel.onthespot.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.azhel.onthespot.R
import com.azhel.onthespot.domain.BootEventModel
import com.azhel.onthespot.domain.toDate

class BootInformationAdapter(var list: List<BootEventModel>): RecyclerView.Adapter<BootInformationAdapter.BootInformationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BootInformationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boot_item, parent, false)
        return BootInformationViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BootInformationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class BootInformationViewHolder(view: View): ViewHolder(view) {

        private val textView = view.findViewById<TextView>(R.id.boot_date)

        fun bind(date: BootEventModel) {
            textView.text = date.bootTime.toDate()
        }
    }

}
