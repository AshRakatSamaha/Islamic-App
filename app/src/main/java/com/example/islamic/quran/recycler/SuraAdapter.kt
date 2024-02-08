package com.example.islamic.quran.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.islamic.R
import com.example.islamic.databinding.ItemSuraBinding
import com.example.islamic.quran.data.SuraData

class SuraAdapter(val suraList: List<SuraData> = listOf()) :
    RecyclerView.Adapter<SuraAdapter.SuraViewHolder>() {
    inner class SuraViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemSuraBinding.bind(viewItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sura, parent, false)
        return SuraViewHolder(view)
    }

    override fun getItemCount(): Int = suraList.size

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        val sura = suraList[position]
        holder.binding.apply {
            tvSurahName.text = sura.suraName
            tvSurahNum.text = sura.suraNumber.toString()
            suraCard.setOnClickListener {
                onItemClickListener?.let { it(sura) }
            }

        }

    }

    private var onItemClickListener: ((SuraData) -> Unit)? = null

    fun setOnItemClickListener(listener: (SuraData) -> Unit) {
        onItemClickListener = listener
    }
}