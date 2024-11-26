package com.example.notepad

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDB(context: Context) :
    SQLiteOpenHelper(context, "notes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableStatement = """
CREATE TABLE notes (
id INTEGER PRIMARY KEY AUTOINCREMENT,
titre TEXT,
contenu TEXT
)
"""
        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun getNotesCount(): Int =
        DatabaseUtils.queryNumEntries(readableDatabase, "notes", null).toInt()

    fun addNote(titre: String, contenu: String) {
        val values = ContentValues() // contenneur clé:valeur
        /** 1 point
        @TODO modifier les valeurs à insérer: titre et contenu
         */
        // insérer "Note" pour le titre juste pour faire tourner l'app.
        values.put("titre", "Note")  // à modifier
        // insérer "contenu Note" pour le contenu juste pour faire tourner l'app
        values.put("contenu", "contenu Note")  // à modifier
        writableDatabase.insert("notes", null, values)
    }

    fun loadNotes(): MutableList<Note> {
        val notes = mutableListOf<Note>()
        readableDatabase.rawQuery("SELECT * FROM notes", null).use { cursor ->
            while (cursor.moveToNext()) {
                /** 1 point
                 * @TODO complétez la récupération du titre et du contenu à partir du curseur
                 */

                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val titre = "Note" // à modifier
                val contenu = "contenu Note"  // à modifier
                val note = Note(id, titre, contenu)
                notes.add(note)
            }
        }
        return notes
    }

    fun upDateNote(note: Note) {
        val values = ContentValues() // contenneur clé:valeur
        values.put("titre", note.titre)
        values.put("contenu", note.contenu)
        writableDatabase.update("notes", values, "id = ?", arrayOf(note.id.toString()))
        writableDatabase.close()

    }

    fun deleteNote(id: Int) {
        // @TODO completer avec l'appel de la methode writableDatabase.delete pour supprimer la note ayan l'identifiant id
        // writableDatabase.delete(...
       writableDatabase.close()

    }


}