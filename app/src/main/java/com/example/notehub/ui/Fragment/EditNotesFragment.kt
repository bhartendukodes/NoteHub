package com.example.notehub.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notehub.R
import com.example.notehub.databinding.FragmentEditNotesBinding
import com.example.notehub.model.Note
import com.example.notehub.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesFragment : Fragment() {

    var priority: String ="1"

    private val binding by lazy {
        FragmentEditNotesBinding.inflate(layoutInflater)
    }


    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    val notes by navArgs<EditNotesFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel.initDb(applicatonContext = requireContext())
        setHasOptionsMenu(true)
        setContent()
        updateNotes()
        return binding.root
    }

    private fun updateNotes() {
        binding.apply {
            saveNote.setOnClickListener {
                val title = edtTitle.text.toString()
                val subTitle = edtSubtitle.text.toString()
                val content = edtNotes.text.toString()
                val d = Date()
                val notesDate: CharSequence = DateFormat.format("MMM d, yyyy", d.time)
                val value= Note(
                    notes.data.id,
                    title = title,
                    subTitle = subTitle,
                    content = content,
                    date = notesDate.toString() ,
                    priority
                )
                homeViewModel.updateNote(value)

                Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
            }
            }
        }

    private fun setContent() {
        binding.apply {
            edtTitle.setText(notes.data.title)
            edtSubtitle.setText(notes.data.subTitle)
            edtNotes.setText(notes.data.content)

            when(notes.data.priority)
            {
                "1"->{
                    priority="1"
                    ivGreen.setImageResource(R.drawable.ic_baseline_done_24)
                    ivYellow.setImageResource(0)
                    ivRed.setImageResource(0)
                }
                "2"->{
                    priority="2"
                    ivGreen.setImageResource(0)
                    ivYellow.setImageResource(R.drawable.ic_baseline_done_24)
                    ivRed.setImageResource(0)
                }
                "3"->{
                    priority="3"
                    ivGreen.setImageResource(0)
                    ivYellow.setImageResource(0)
                    ivRed.setImageResource(R.drawable.ic_baseline_done_24)
                }
            }
            ivGreen.setOnClickListener {
                priority="1"
                ivGreen.setImageResource(R.drawable.ic_baseline_done_24)
                ivYellow.setImageResource(0)
                ivRed.setImageResource(0)
            }

            ivYellow.setOnClickListener {
                priority="2"
                ivGreen.setImageResource(0)
                ivYellow.setImageResource(R.drawable.ic_baseline_done_24)
                ivRed.setImageResource(0)
            }

            ivRed.setOnClickListener {
                priority="3"
                ivGreen.setImageResource(0)
                ivYellow.setImageResource(0)
                ivRed.setImageResource(R.drawable.ic_baseline_done_24)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_show, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delet)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.yesClicked)
            val textViewNo= bottomSheet.findViewById<TextView>(R.id.noClicked)

            textViewYes!!.setOnClickListener {
                homeViewModel.deleteNote(notes.data.id!!)
                bottomSheet.dismiss()
                findNavController().navigateUp()
            }
            textViewNo!!.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}