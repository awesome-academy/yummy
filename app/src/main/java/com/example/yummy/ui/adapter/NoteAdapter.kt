package com.example.yummy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yummy.R
import com.example.yummy.data.model.Note

class NoteAdapter(
    private val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<NoteViewHolder>() {

    private val notes = mutableListOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, onClickListener)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindData(notes[position])
    }

    fun updateData(newList: List<Note>) {
        notes.run {
            clear()
            addAll(newList)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun openContentNoteFragment(note: Note)
        fun openDeleteNoteDialog(note: Note)
        fun openEditNoteFragment(note: Note)
    }
}
