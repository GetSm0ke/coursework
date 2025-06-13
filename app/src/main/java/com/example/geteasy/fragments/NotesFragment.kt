package com.example.geteasy.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.NumberPicker
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.geteasy.R
import com.example.geteasy.activities.DrawerController
import com.example.geteasy.databinding.FragmentNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()
    private var drawerController: DrawerController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Проверяем, что активность реализует DrawerController
        try {
            drawerController = context as DrawerController
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DrawerController")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.optionButton.setOnClickListener {
            Log.d("NotesFragment", "Menu button clicked")
            drawerController?.openDrawer()
        }

//        setupSaveButton()
    }

    private fun setupSaveButton() {
        binding.saveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
//        val frequency = binding.dateEditText2.text.toString()
        val reminderText = binding.dateEditTextReminder.text.toString()

        if (reminderText.isBlank()) {
            binding.dateEditTextReminder.error = getString(R.string.note_text_error)
            return
        }

        // Здесь сохраняем данные (в БД, ViewModel и т.д.)
        showToast(getString(R.string.note_saved))
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}