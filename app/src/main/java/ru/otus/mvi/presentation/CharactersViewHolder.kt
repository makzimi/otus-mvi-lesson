package ru.otus.mvi.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.otus.mvi.databinding.ItemGalleryBinding
import ru.otus.mvi.domain.RaMCharacter

class CharactersViewHolder(private val binding: ItemGalleryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: RaMCharacter) {
        Glide.with(this.itemView).load(character.image).into(binding.uiImage)
        binding.uiName.text = character.name
    }
}
