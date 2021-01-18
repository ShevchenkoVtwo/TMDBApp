package com.shevchenkovtwo.homework.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.homework.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var fragmentProfileBinding: FragmentProfileBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        fragmentProfileBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Snackbar.make(view, "Will be updated soon", Snackbar.LENGTH_SHORT).show()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        fragmentProfileBinding = null
        super.onDestroy()
    }
}