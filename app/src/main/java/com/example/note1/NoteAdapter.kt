package com.example.note1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note, position + 1)
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNoteNumber: TextView = itemView.findViewById(R.id.tvNoteNumber)
        private val tvNoteText: TextView = itemView.findViewById(R.id.tvNoteText)
        private val cbCompleted: CheckBox = itemView.findViewById(R.id.cbCompleted)
        private val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)

        fun bind(note: Note, position: Int) {
            tvNoteNumber.text = position.toString()
            tvNoteText.text = note.text
            cbCompleted.isChecked = note.isCompleted
            tvDateTime.text = note.dateTime

            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                note.isCompleted = isChecked
            }
        }
    }
}