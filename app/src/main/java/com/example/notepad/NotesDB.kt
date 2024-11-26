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
        val values = ContentValues() // conteneur clé:valeur
        // Modifier les valeurs à insérer : titre et contenu
        values.put("titre", titre)  // Utiliser le paramètre titre
        values.put("contenu", contenu)  // Utiliser le paramètre contenu
        writableDatabase.insert("notes", null, values)
    }

    fun loadNotes(): MutableList<Note> {
        val notes = mutableListOf<Note>()
        readableDatabase.rawQuery("SELECT * FROM notes", null).use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val titre = cursor.getString(cursor.getColumnIndexOrThrow("titre"))
                val contenu = cursor.getString(cursor.getColumnIndexOrThrow("contenu"))
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
        writableDatabase.delete("notes", "id = ?", arrayOf(id.toString()))
        writableDatabase.close()
    }


}