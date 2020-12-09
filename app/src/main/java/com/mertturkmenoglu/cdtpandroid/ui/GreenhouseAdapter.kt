package com.mertturkmenoglu.cdtpandroid.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.mertturkmenoglu.cdtpandroid.data.model.Greenhouse
import com.mertturkmenoglu.cdtpandroid.util.GreenhouseDiffCallback
import androidx.recyclerview.widget.ListAdapter
import com.mertturkmenoglu.cdtpandroid.R

class GreenhouseAdapter(private val context: Context, private val viewModel: HomeViewModel)
    : ListAdapter<Greenhouse, GreenhouseAdapter.GreenhouseViewHolder>(GreenhouseDiffCallback.callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreenhouseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_greenhouse, parent, false)
        return GreenhouseViewHolder(view)
    }

    override fun onBindViewHolder(holder: GreenhouseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GreenhouseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val greenhouseName: TextView = view.findViewById(R.id.greenhouseItemGreenhouseName)
        private val greenhouseTemperature: TextView = view.findViewById(R.id.greenhouseItemTemperature)
        private val greenhouseChangeTemperatureTextInput: TextInputLayout = view.findViewById(R.id.greenhouseTemperatureTextInput)
        private val greenhouseChangeTemperatureButton: Button = view.findViewById(R.id.greenhouseTemperatureChangeButton)

        init {
            setClickListeners()
        }

        fun bind(greenhouse: Greenhouse) {
            greenhouseName.text = greenhouse.name
            greenhouseTemperature.text = "${greenhouse.temperature} \u2103"
        }

        private fun setClickListeners() {
            greenhouseChangeTemperatureButton.setOnClickListener {
                val editText = greenhouseChangeTemperatureTextInput.editText ?: throw IllegalStateException()
                val name = greenhouseName.text.toString()
                val newTemperature = editText.text?.toString()?.trim()?.toInt() ?: greenhouseTemperature.text.toString().toInt()

                viewModel.updateGreenhouse(name, newTemperature)
            }
        }
    }
}
