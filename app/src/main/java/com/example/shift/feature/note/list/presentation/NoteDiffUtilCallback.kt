package com.example.shift.feature.note.list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.common.Note

class NoteDiffUtilCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note) =
        oldItem.description == newItem.description && oldItem.title == newItem.title
}