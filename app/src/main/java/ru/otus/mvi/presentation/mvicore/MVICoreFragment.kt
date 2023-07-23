package ru.otus.mvi.presentation.mvicore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.otus.mvi.InjectorProvider
import ru.otus.mvi.databinding.FragmentMvicoreBinding
import ru.otus.mvi.presentation.mvvm.MVVMViewModel

class MVICoreFragment : Fragment() {
    private var _binding: FragmentMvicoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MVVMViewModel by viewModels(
        factoryProducer = {
            (requireContext().applicationContext as InjectorProvider)
                .injector
                .provideViewModelFactory()
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMvicoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}