package com.example.notepad

import UpDateNoteDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class NoteAdapter(
    private val listNote: MutableList<Note>,
    val db: NotesDB,
    val listner: AdapterListner
) :
    RecyclerView.Adapter<NoteAdapter.ContactViewHolder>() {
    interface AdapterListner {
        fun upDateNote(note: Note)
        fun deleteNote(id: Int)
    }

    // ViewHolder : Lie les vues d'un élément (nom et numéro)
    inner class ContactViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textviewTitre: TextView = itemView.findViewById(R.id.titre_note_tv)
        val textviewContenu: TextView = itemView.findViewById(R.id.contenu_note_tv)
        // val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        init {
            // Gérer l'action de l'ImageButton en appelant la méthode listener.deleteNote
            deleteButton.setOnClickListener {
                val noteToDelete = listNote[adapterPosition]
                AlertDialog.Builder(itemView.context)
                    .setTitle("Supprimer la note")
                    .setMessage("Êtes-vous sûr de vouloir supprimer cette note ?")
                    .setPositiveButton("Oui") { _, _ ->
                        listner.deleteNote(noteToDelete.id) // Appel à la méthode deleteNote
                    }
                    .setNegativeButton("Non", null)
                    .show()
            }

            itemView.setOnClickListener {
                listener.upDateNote(listNote[adapterPosition])
            }
        }

    }


    // Crée une nouvelle vue (appelé quand RecyclerView a besoin d'une nouvelle vue d'élément)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ContactViewHolder(view)
    }

    // Lie les données d'un contact à la vue
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val note = listNote[position]
        holder.textviewTitre.text = note.titre
        holder.textviewContenu.text = note.contenu
    }

    // Retourne le nombre d'éléments de la liste
    override fun getItemCount(): Int = listNote.size


}
