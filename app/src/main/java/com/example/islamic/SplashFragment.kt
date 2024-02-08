package com.example.islamic

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.islamic.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                findNavController().navigate(R.id.action_splashFragment_to_quranFragment)

            }
            ,1000 )
        return binding.root
    }


}