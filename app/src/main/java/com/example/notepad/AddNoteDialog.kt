import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.notepad.R


class AddNoteDialog(private val listener: ContactDialogListener) : DialogFragment() {
    // Interface pour renvoyer les données à l’activité
    interface ContactDialogListener {
        fun onNoteAdded(titre: String, contenu: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_note, null)
        builder.setView(dialogView)
        builder.setTitle("Ajouter Note")
        val editTextTitre = dialogView.findViewById<EditText>(R.id.editTextTitre)
        val editTextPhone = dialogView.findViewById<EditText>(R.id.editTextContenu)
        val bouttonOk = dialogView.findViewById<Button>(R.id.bouttonOk)

        bouttonOk.setOnClickListener {
            val titre = editTextTitre.text.toString()
            val contenu = editTextPhone.text.toString()
            if (titre.isNotEmpty() && contenu.isNotEmpty()) {
                listener.onNoteAdded(titre, contenu)  // Envoie les données
            }
            this.dismiss()
        }
        /**
         * @TODO : implémenter l'action du boutton Annuler permettant e cacher la boite de dialogue
         *
         */

        return builder.create()
    }
}
