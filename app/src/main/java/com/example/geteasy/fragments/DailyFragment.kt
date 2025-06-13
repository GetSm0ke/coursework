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
import com.example.geteasy.databinding.FragmentDailyBinding
import java.text.SimpleDateFormat
import java.util.*

class DailyFragment : Fragment() {
    private var _binding: FragmentDailyBinding? = null
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
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.optionButton.setOnClickListener {
            Log.d("DailyFragment", "Menu button clicked")
            drawerController?.openDrawer()
        }

        setupDateTimePicker()
        setupFrequencyPicker()
        setupSaveButton()
    }

    private fun setupDateTimePicker() {
        binding.dateEditText.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                showTimePicker()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                updateDateTimeDisplay()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun updateDateTimeDisplay() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        binding.dateEditText.setText(dateFormat.format(calendar.time))
    }

    private fun setupFrequencyPicker() {
        binding.dateEditText2.setOnClickListener {
            showFrequencyPickerDialog()
        }
    }

    private fun showFrequencyPickerDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_frequency_picker, null)
        val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        val periodSpinner = dialogView.findViewById<Spinner>(R.id.periodSpinner)

        // Настройка NumberPicker
        numberPicker.minValue = 1
        numberPicker.maxValue = 30
        numberPicker.value = 1

        // Настройка Spinner
        val periods = resources.getStringArray(R.array.notification_periods)
        val periodAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            periods
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        periodSpinner.adapter = periodAdapter

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.select_frequency)
            .setView(dialogView)
            .setPositiveButton(R.string.done) { _, _ ->
                val number = numberPicker.value
                val period = when (periodSpinner.selectedItemPosition) {
                    0 -> getString(R.string.day)
                    1 -> getString(R.string.week)
                    2 -> getString(R.string.month)
                    3 -> getString(R.string.year)
                    else -> getString(R.string.day)
                }
                val frequencyText = getString(R.string.frequency_format, number, period)
                binding.dateEditText2.setText(frequencyText)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun setupSaveButton() {
        binding.saveReminder.setOnClickListener {
            saveReminder()
        }
    }

    private fun saveReminder() {
        val dateTime = calendar.timeInMillis
        val frequency = binding.dateEditText2.text.toString()
        val reminderText = binding.dateEditTextReminder.text.toString()

        if (reminderText.isBlank()) {
            binding.dateEditTextReminder.error = getString(R.string.daily_text_error)
            return
        }

        // Здесь сохраняем данные (в БД, ViewModel и т.д.)
        showToast(getString(R.string.daily_saved))
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}