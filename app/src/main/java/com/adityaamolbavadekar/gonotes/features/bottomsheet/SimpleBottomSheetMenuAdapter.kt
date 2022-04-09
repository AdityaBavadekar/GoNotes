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
        holder.apply {

            when (type) {
                BottomSheetMenu.COLORS.name -> {
                    binding.textView1.text = colorNames[position]
                    binding.imageView1.setBackgroundColor(colorIds[position])
                }
                BottomSheetMenu.ATTACH.name -> {
                    binding.textView1.text = attachTitles[position]
                    binding.imageView1.setImageResource(attachImageIds[position])
                }
                BottomSheetMenu.DUPLICATE.name -> {
                    binding.textView1.text = duplicateTitles[position]
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return when (BottomSheetMenu.valueOf(type)) {
            BottomSheetMenu.COLORS -> 6 //6 GoNoteColors
            BottomSheetMenu.ATTACH -> 5
            BottomSheetMenu.DUPLICATE -> 3
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
    private val attachTitles =
        listOf("Add image", "Take photo", "Add document", "Add Checkboxes", "Add Collaborators")
    private val attachImageIds: List<Int> =
        listOf(
            R.drawable.ic_image,
            R.drawable.ic_photo_camera,
            R.drawable.ic_file,
            R.drawable.ic_check_box,
            R.drawable.ic_outline_person_add_24
        )
    private val duplicateTitles = listOf("Duplicate note","Duplicate title","Duplicate description")
}
