package com.example.notepad

import AddNoteDialog
import UpDateNoteDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * l'activité principale l'application NotePad
 * @author GHILAS Hamza pour évaluation des étudiant dans la
 * matière Programmation Mobile
 *
 */

class MainActivity : AppCompatActivity(), NoteAdapter.AdapterListner {
    private lateinit var adapter: NoteAdapter
    private val db: NotesDB = NotesDB(this)
    private var listNote: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listNote = db.loadNotes()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = NoteAdapter(listNote, db, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ajout de quelques Notes
        if (db.getNotesCount() == 0) {
            for (i in 1..10)
                db.addNote("Note $i", "Contenu $i")
            reloadNotes()
        }


    }

    /**
     * Action du Boutton flottant + de l'activité pricipale
     * création d'un objet listner implémantant la méthode
     * onNoteAdded(titre, contenu)
     * @param titre: titre de la note
     * @param contenu: le contenu de la note
     */

    fun addNote(view: View) {
        val listner = object : AddNoteDialog.ContactDialogListener {
            override fun onNoteAdded(titre: String, contenu: String) {
                db.addNote(titre, contenu)
                reloadNotes()
            }
        }
        val dialog = AddNoteDialog(listner)
        dialog.show(supportFragmentManager, "noteAdd")
    }

    /**
     * 1 points
     * @TODO implémenter la méthode upDateNote
     * @param note : la note à mise à jour
     * la méthode créer un objet listner qui implément l’interface UpDateNoteDialog.ContactDialogListener
     * mise à jour de la note dans la BDD
     * recharger la liste de Note et mettre à jour l'affichage
     * puis instancie la boite de dialogue et l’affiche
     */
    override fun upDateNote(note: Note) {
        val listner = object : UpDateNoteDialog.ContactDialogListener {
            override fun onNoteUpDated(note: Note) {
                TODO("Not yet implemented")
            }
        }
        val dialog = UpDateNoteDialog(listner, note)
        dialog.show(supportFragmentManager, "noteUpDate")
    }

    override fun deleteNote(id: Int) {
        db.deleteNote(id)
        reloadNotes()
    }

    /**
     * recharger la liste de notes et notifier le changement à l'adapter
     *
     */
    fun reloadNotes() {
        listNote.clear()
        listNote.addAll(db.loadNotes())
        adapter.notifyDataSetChanged()
    }
}

