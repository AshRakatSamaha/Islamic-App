package com.example.islamic.quran.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamic.R
import com.example.islamic.databinding.ItemSuraContentBinding

class SuraDetailsAdapter(var suraDetails: List<String>?) :
    RecyclerView.Adapter<SuraDetailsAdapter.SuraDetailsViewHolder>() {

    inner class SuraDetailsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemSuraContentBinding.bind(viewItem)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuraDetailsAdapter.SuraDetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sura_content, parent, false)
        return SuraDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuraDetailsAdapter.SuraDetailsViewHolder, position: Int) {
        val suraDetail = suraDetails!![position]
        holder.binding.apply {
            suraContentTv.text = suraDetail
        }

    }

    override fun getItemCount(): Int = suraDetails!!.size

    fun updateData(data: List<String>){
        this.suraDetails = data
        notifyDataSetChanged()
    }
}