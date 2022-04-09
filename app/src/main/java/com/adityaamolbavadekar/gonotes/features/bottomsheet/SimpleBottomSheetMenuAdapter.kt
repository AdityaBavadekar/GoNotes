package com.adityaamolbavadekar.gonotes.features.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.RecyclerViewItemBinding

class SimpleBottomSheetMenuAdapter(private val type: String) :
    RecyclerView.Adapter<SimpleBottomSheetMenuAdapter.MenuItemHolder>() {

    class MenuItemHolder(val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        return MenuItemHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        if (BottomSheetMenu.COLORS.name == type) {
            holder.binding.textView1.text = colorNames[position]
            holder.binding.imageView1.setBackgroundColor(colorIds[position])
        }else {

        }
    }

    override fun getItemCount(): Int {
        return when (BottomSheetMenu.valueOf(type)) {
            BottomSheetMenu.COLORS -> 6
            BottomSheetMenu.ATTACH -> 5
            BottomSheetMenu.DUPLICATE -> 2
        }
    }

    enum class BottomSheetMenu { COLORS, ATTACH, DUPLICATE }

    private val colorNames = listOf("Green", "Blue", "Red", "Yellow", "Purple", "Orange")
    private val colorIds = listOf(
        R.color.go_notes_green_200,
        R.color.go_notes_blue_200,
        R.color.go_notes_red_200,
        R.color.go_notes_yellow_200,
        R.color.go_notes_purple_200,
        R.color.go_notes_orange_200
    )

}
