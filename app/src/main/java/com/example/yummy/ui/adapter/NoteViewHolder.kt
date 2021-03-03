package com.example.yummy.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.data.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteViewHolder(
    itemView: View,
    private val onClickListener: NoteAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    private var noteItem: Note? = null

    init {
        itemView.apply {
            setOnClickListener {
                noteItem?.let(onClickListener::openContentNoteFragment)
            }
            imageButtonDelete.setOnClickListener {
                noteItem?.let(onClickListener::openDeleteNoteDialog)
            }
            imageButtonEdit.setOnClickListener {
                noteItem?.let(onClickListener::openEditNoteFragment)
            }
        }
    }

    fun bindData(note: Note) {
        noteItem = note
        itemView.textTitle.text = note.title
    }
}
