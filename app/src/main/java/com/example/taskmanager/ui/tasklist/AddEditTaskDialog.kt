package com.example.taskmanager.ui.tasklist

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.DialogAddEditTaskBinding
import com.example.taskmanager.model.TaskStatus
import com.example.taskmanager.model.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddEditTaskDialog(
    private val task: Task? = null,
    private val onTaskConfirmed: (Task) -> Unit
) : DialogFragment() {

    private var _binding: DialogAddEditTaskBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("he"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStatusSpinner()
        setupDatePicker()
        setupButtons()

        task?.let { existingTask ->
            binding.titleEditText.setText(existingTask.title)
            binding.descriptionEditText.setText(existingTask.description)
            binding.statusSpinner.setSelection(existingTask.status.ordinal)
        }
    }

    private fun setupStatusSpinner() {
        val statusAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            TaskStatus.values().map { it.name }
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.statusSpinner.adapter = statusAdapter
    }

    private fun setupDatePicker() {
        binding.dateButton.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    binding.dateButton.text = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupButtons() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val status = TaskStatus.values()[binding.statusSpinner.selectedItemPosition]

            if (title.isNotBlank()) {
                val newTask = Task(
                    id = task?.id ?: 0,
                    title = title,
                    description = description,
                    status = status
                )
                onTaskConfirmed(newTask)
                dismiss()
            } else {
                binding.titleEditText.error = "נא להזין כותרת"
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}