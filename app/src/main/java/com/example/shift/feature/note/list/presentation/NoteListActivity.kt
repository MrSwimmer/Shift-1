package com.example.shift.feature.note.list.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.Note
import com.example.shift.R
import com.example.shift.feature.note.detail.presentaion.NoteDetailActivity
import com.example.shift.feature.note.list.di.NoteListViewModelFactory
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val viewModel: NoteListViewModel by viewModels {
        NoteListViewModelFactory()
    }
    private val adapter =
        NoteListAdapter(NoteDiffUtilCallback()) { note ->
            viewModel.noteClicked(note)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        viewModel.noteClickedEvent.observe(this, Observer(::showNoteDetails))

        val layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        viewModel.notes.observe(this, Observer(::setNoteList))
    }

    private fun setNoteList(notesList: PagedList<Note>) {
        adapter.submitList(notesList)
        recyclerView.adapter = adapter
    }

    private fun showNoteDetails(note: Note) {
        val intent = Intent(this@NoteListActivity, NoteDetailActivity::class.java)
        intent.putExtra("Note", note)
        startActivity(intent)
    }
}