package com.shevchenkovtwo.homework.ui.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.homework.databinding.FragmentCinemasBinding


class CinemasFragment : Fragment() {

    private var fragmentCinemasBinding: FragmentCinemasBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentCinemasBinding.inflate(inflater, container, false)
        fragmentCinemasBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Snackbar.make(view, "Will be updated soon", Snackbar.LENGTH_SHORT).show()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        fragmentCinemasBinding = null
        super.onDestroy()
    }
}