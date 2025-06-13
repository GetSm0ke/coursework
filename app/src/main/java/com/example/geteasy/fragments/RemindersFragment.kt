package com.example.geteasy.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.geteasy.R
import com.example.geteasy.activities.DrawerController
import com.example.geteasy.databinding.FragmentRemindersBinding
import java.text.SimpleDateFormat
import java.util.*

class RemindersFragment : Fragment(R.layout.fragment_reminders) {
    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()
    private var drawerController: DrawerController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            drawerController = context as DrawerController
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DrawerController")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRemindersBinding.bind(view)
        binding.optionButton.setOnClickListener {
            Log.d("DailyFragment", "Menu button clicked")
            drawerController?.openDrawer()
        }

        setupDateInput()
        setupReminderInput()
        setupSaveButton()
    }

    private fun setupDateInput() {
        binding.dateEditText.setOnClickListener {
            showDateTimePicker()
        }
    }

    private fun showDateTimePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            TimePickerDialog(requireContext(), { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)

                val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                binding.dateEditText.setText(format.format(calendar.time))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun setupReminderInput() {
        binding.reminderEditText.setOnEditorActionListener { _, actionId, _ ->
            // Обработка завершения ввода текста
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                // Сохранить текст напоминания
                val reminderText = binding.reminderEditText.text.toString()
                // Дополнительная обработка...
                true
            } else {
                false
            }
        }
    }

    private fun setupSaveButton() {
        binding.saveReminder.setOnClickListener {
            saveReminder()
        }
    }

    private fun saveReminder() {
        val dateTime = calendar.timeInMillis
        val reminderText = binding.reminderEditText.text.toString()

        if (reminderText.isBlank()) {
            binding.reminderEditText.error = getString(R.string.reminder_text_error)
            return
        }

        // Здесь сохраняем данные (в БД, ViewModel и т.д.)
        showToast(getString(R.string.reminder_saved))
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}