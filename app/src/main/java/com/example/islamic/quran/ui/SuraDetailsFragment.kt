package com.example.islamic.quran.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamic.databinding.FragmentSuraDetailsBinding
import com.example.islamic.quran.recycler.SuraDetailsAdapter

class SuraDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSuraDetailsBinding
    private lateinit var suraDetailsAdapter: SuraDetailsAdapter
    private var suraNumber: Int? = null
    private var suraName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuraDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareSuraName()
        initRecyclerView()
        showSuraName()
        readDataFromFile()


        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }


    }

    private fun shareSuraName() {
        suraNumber = requireArguments().getInt("suraNumber")
        suraName = requireArguments().getString("suraName")
        val result = Bundle().apply {
            putString("suraName", suraName)
        }
        parentFragmentManager.setFragmentResult("suraDetailsResult", result)

    }

    private fun initRecyclerView() {
        suraDetailsAdapter = SuraDetailsAdapter(null)
        binding.suraContentRecycler.adapter = suraDetailsAdapter

    }


    private fun readDataFromFile() {
        val fileContent = requireContext().assets.open("$suraNumber.txt").bufferedReader()
            .use { it.readText() }
        val data = fileContent.split("\\d+".toRegex())
        suraDetailsAdapter.updateData(data)

    }

    private fun showSuraName() {
        binding.suraName.text = "سُورَة $suraName"

    }

}