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
        editTextTitre.setText(note.titre)
        val editTextContenu = dialogView.findViewById<EditText>(R.id.editTextContenu)
        editTextContenu.setText(note.contenu)
        val bouttonOk = dialogView.findViewById<Button>(R.id.bouttonOk)
        /**
         * @TODO a.	Implémenter l’action du bouton ok permettant de récupérer le titre et le contenu
         * de la note et d’appeler la méthode listener.onNoteUpDated pour mettre à jour la note. (2 points)
         */

        dialogView.findViewById<Button>(R.id.bouttonAnnuler).setOnClickListener {
            this.dismiss()

        }
        return builder.create()
    }
}
