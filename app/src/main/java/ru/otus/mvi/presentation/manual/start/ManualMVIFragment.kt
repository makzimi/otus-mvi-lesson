package ru.otus.mvi.presentation.manual.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.otus.mvi.databinding.FragmentManualMviBinding
import ru.otus.mvi.domain.RaMCharacter
import ru.otus.mvi.getInjector
import ru.otus.mvi.presentation.CharactersAdapter

class ManualMVIFragment : Fragment() {
    private var _binding: FragmentManualMviBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ManualMVIViewModel by viewModels(
        factoryProducer = { getInjector().provideViewModelFactory() }
    )

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManualMviBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uiRecyclerView.adapter = adapter
        binding.uiRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.uiSwipeRefreshLayout.setOnRefreshListener {
            // TODO refresh
        }

        // TODO collect states

        // TODO first action
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderLoadingState() {
        hideAll()
        binding.uiProgressBar.visibility = View.VISIBLE
    }

    private fun renderErrorState() {
        binding.uiProgressBar.visibility = View.GONE
        Toast.makeText(
            requireContext(),
            "Error wile loading data",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun renderCharactersState(items: List<RaMCharacter>) {
        hideAll()
        adapter.submitList(items)
        binding.uiRecyclerView.visibility = View.VISIBLE
    }

    private fun hideAll() {
        binding.uiRecyclerView.visibility = View.GONE
        binding.uiProgressBar.visibility = View.GONE
        binding.uiMessage.visibility = View.GONE
        binding.uiSwipeRefreshLayout.isRefreshing = false
    }
}