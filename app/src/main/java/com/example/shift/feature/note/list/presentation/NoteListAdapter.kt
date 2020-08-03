package com.example.shift.feature.note.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.Note
import com.example.shift.R

class NoteListAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<Note>,
    private val clickListener: (Note) -> Unit
) : PagedListAdapter<Note, NoteListAdapter.ViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_note, parent, false)
        return ViewHolder(
            view,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View, private val noteListener: (Note) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.titleNote)

        fun bind(model: Note) {
            title.text = model.id.toString()
            itemView.setOnClickListener {
                noteListener(model)
            }
        }
    }
}