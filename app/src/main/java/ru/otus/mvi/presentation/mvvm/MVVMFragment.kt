package ru.otus.mvi.presentation.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.otus.mvi.InjectorProvider
import ru.otus.mvi.databinding.FragmentMvvmBinding
import ru.otus.mvi.domain.RaMCharacter
import ru.otus.mvi.presentation.CharactersAdapter

class MVVMFragment : Fragment() {

    private var _binding: FragmentMvvmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MVVMViewModel by viewModels(
        factoryProducer = {
            (requireContext().applicationContext as InjectorProvider)
                .injector
                .provideViewModelFactory()
        }
    )

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMvvmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uiRecyclerView.adapter = adapter
        binding.uiRecyclerView.layoutManager = LinearLayoutManager(context)

        subscribeUI()

        binding.uiSwipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(State.STARTED) {
                launch {
                    viewModel.items.collect { items: List<RaMCharacter> ->
                        adapter.submitList(items)
                        showList()
                    }
                }

                launch {
                    viewModel.isError
                        .filter { isError -> isError }
                        .onEach {
                            Toast.makeText(
                                requireContext(),
                                "Error wile loading data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .collect()
                }

                launch {
                    viewModel.isLoading
                        .collect { isLoading ->
                            if (isLoading) {
                                showLoading()
                            } else {
                                showList()
                            }
                        }
                }
            }
        }
    }

    private fun showLoading() {
        hideAll()
        binding.uiProgressBar.visibility = View.VISIBLE
    }

    private fun showList() {
        hideAll()
        binding.uiRecyclerView.visibility = View.VISIBLE
    }

    private fun hideAll() {
        binding.uiRecyclerView.visibility = View.GONE
        binding.uiProgressBar.visibility = View.GONE
        binding.uiMessage.visibility = View.GONE
        binding.uiSwipeRefreshLayout.isRefreshing = false
    }
}