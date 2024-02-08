package com.example.islamic.quran.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.islamic.R
import com.example.islamic.databinding.FragmentQuranScreenBinding
import com.example.islamic.quran.data.SuraData
import com.example.islamic.quran.data.SuraName
import com.example.islamic.quran.recycler.SuraAdapter


class QuranFragment : Fragment() {
    private lateinit var binding: FragmentQuranScreenBinding
    private lateinit var suraAdapter: SuraAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuranScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSuraName()
        initViews()
        navigateToSuraDetail()
    }

    private fun initViews() {
        initRecycleView()
    }

    private fun showSuraName() {
        setFragmentResultListener("suraDetailsResult") { _, result ->
            val suraName = result.getString("suraName")
            Log.d("QuranFragment", "Received suraName: $suraName")
            binding.suraName.text = "سُورَة $suraName"
        }
    }

    private fun initRecycleView() {
        suraAdapter = SuraAdapter(SuraName.sura.mapIndexed { index, suraName ->
            SuraData(index + 1, suraName)
        })
        binding.quranRecyclerView.adapter = suraAdapter

    }

    private fun navigateToSuraDetail() {
        suraAdapter.setOnItemClickListener { suraData ->
            val bundle = Bundle().apply {
                putInt("suraNumber", suraData.suraNumber)
                putString("suraName", suraData.suraName)
            }
            findNavController().navigate(R.id.action_quranFragment_to_suraDetailsFragment, bundle)
        }
    }
}