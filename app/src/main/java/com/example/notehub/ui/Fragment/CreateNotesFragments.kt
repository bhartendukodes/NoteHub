package com.example.notehub.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notehub.R

import com.example.notehub.databinding.FragmentCreateNotesFragmentsBinding
import com.example.notehub.model.Note
import com.example.notehub.viewmodel.HomeViewModel

import java.util.*

class CreateNotesFragments : Fragment() {

    var priority: String ="1"

    private val binding by lazy {
        FragmentCreateNotesFragmentsBinding.inflate(layoutInflater)
    }

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel.initDb(applicatonContext = requireContext())
        onClick()
        priority()
        return binding.root
    }

    private fun onClick() {
        binding.apply {
            btnEditSaveNotes.setOnClickListener {
                    val title = edtTitle.text.toString()
                    val subTitle = edtSubtitle.text.toString()
                    val content = edtContent.text.toString()
                    val d = Date()
                    val notesDate: CharSequence = DateFormat.format("MMM d, yyyy", d.time)
                    val value= Note(
                        null,
                        title = title,
                        subTitle = subTitle,
                        content = content,
                        date = notesDate.toString() ,
                        priority
                    )
                    homeViewModel.saveNote(value)

                Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragments_to_homeFragment)

            }

        }
    }

    private fun priority() {
        binding.apply {

            ivGreen.setImageResource(R.drawable.ic_baseline_done_24)

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
}
