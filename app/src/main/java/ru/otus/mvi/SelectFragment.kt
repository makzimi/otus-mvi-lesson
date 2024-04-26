package ru.otus.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.otus.mvi.databinding.FragmentSelectBinding

class SelectFragment: Fragment() {

    private var _binding: FragmentSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMVVM.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_MVVMFragment)
        }

        binding.buttonManual.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_ManualMVIFragment)
        }

        binding.buttonRoxie.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_roxieFragment)
        }

        binding.buttonMVICore.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_MVICoreFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
