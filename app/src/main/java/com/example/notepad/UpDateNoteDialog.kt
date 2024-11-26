import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.notepad.Note
import com.example.notepad.R


class UpDateNoteDialog(private val listener: ContactDialogListener, var note: Note) :
    DialogFragment() {
    // Interface pour renvoyer les données à l’activité
    interface ContactDialogListener {
        fun onNoteUpDated(note: Note)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_note, null)
        builder.setView(dialogView)
        builder.setTitle("Modifier Note")

        val editTextTitre = dialogView.findViewById<EditText>(R.id.editTextTitre)
        editTextTitre.setText(note.titre) // Remplir l'EditText avec le titre de la note
        val editTextContenu = dialogView.findViewById<EditText>(R.id.editTextContenu)
        editTextContenu.setText(note.contenu) // Remplir l'EditText avec le contenu de la note

        val boutonOk = dialogView.findViewById<Button>(R.id.bouttonOk)

        // Implémenter l’action du bouton OK
        boutonOk.setOnClickListener {
            val titre = editTextTitre.text.toString() // Récupérer le titre
            val contenu = editTextContenu.text.toString() // Récupérer le contenu
            if (titre.isNotEmpty() && contenu.isNotEmpty()) {
                listener.onNoteUpDated(Note(note.id, titre, contenu)) // Appeler la méthode pour mettre à jour la note
            }
            this.dismiss() // Fermer le dialogue
        }

        dialogView.findViewById<Button>(R.id.bouttonAnnuler).setOnClickListener {
            this.dismiss() // Fermer le dialogue si Annuler est cliqué
        }

        return builder.create()
    }
}
