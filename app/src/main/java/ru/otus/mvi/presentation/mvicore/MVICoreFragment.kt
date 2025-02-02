package ru.otus.mvi.presentation.mvicore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.functions.Consumer
import ru.otus.mvi.databinding.FragmentMvicoreBinding
import ru.otus.mvi.domain.RaMCharacter
import ru.otus.mvi.getInjector
import ru.otus.mvi.presentation.CharactersAdapter
import ru.otus.mvi.presentation.mvicore.entities.State
import ru.otus.mvi.presentation.mvicore.entities.Wish.SwipedToRefresh
import ru.otus.mvi.presentation.mvicore.entities.Wish.FeatureStarted

class MVICoreFragment : Fragment(), Consumer<State> {
    private var _binding: FragmentMvicoreBinding? = null
    private val binding get() = _binding!!

    private val feature by lazy { getInjector().provideFeature() }

    private val mviBindings by lazy {
        MVICoreFragmentBindings(
            view = this,
            feature = feature,
            newsListener = NewsListener(context = requireContext())
        )
    }

    private val adapter = CharactersAdapter()

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

        mviBindings.setup(this)

        binding.uiRecyclerView.adapter = adapter
        binding.uiRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.uiSwipeRefreshLayout.setOnRefreshListener {
            feature.accept(SwipedToRefresh)
        }

        if (savedInstanceState == null) {
            feature.accept(FeatureStarted)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun accept(state: State) {
        with(state) {
            when {
                isLoading -> renderLoadingState()
                else -> renderItemsState(items)
            }
        }
    }

    private fun renderLoadingState() {
        hideAll()
        binding.uiProgressBar.visibility = View.VISIBLE
    }

    private fun renderItemsState(items: List<RaMCharacter>) {
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
