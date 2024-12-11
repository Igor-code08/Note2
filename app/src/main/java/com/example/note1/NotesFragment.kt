package com.example.note1

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class NotesFragment : Fragment() {

    private val notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // Для меню "Exit"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Note1", "onViewCreated")

        // Установка адаптера для RecyclerView
        adapter = NoteAdapter(notes)
        val rvNotes =view.findViewById(R.id.rvNotes) as RecyclerView
        rvNotes.layoutManager = LinearLayoutManager(requireContext())
        rvNotes.adapter = adapter

        // Добавление новой заметки при нажатии на кнопку "Добавить"
        val btnAddNote = view.findViewById(R.id.btnAddNote) as Button
        btnAddNote.setOnClickListener {
            val etNoteInput = view.findViewById(R.id.etNoteInput) as EditText
            val text = etNoteInput.text.toString()
            if (text.isNotEmpty()) {
                val dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                val note = Note(
                    id = notes.size + 1,
                    text = text,
                    dateTime = dateTime
                )
                notes.add(note)
                adapter.notifyDataSetChanged()
                etNoteInput.text.clear()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_exit) {
            requireActivity().finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Note1", "onViewCreated")
    }
}