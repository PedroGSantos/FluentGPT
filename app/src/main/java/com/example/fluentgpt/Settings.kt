package com.example.fluentgpt

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val themeSwitch: Switch = view.findViewById(R.id.themeSwitch)

        // Carrega o estado do tema das preferências compartilhadas
        val sharedPreferences = requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("isLightMode", true)
        themeSwitch.isChecked = isDarkMode

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Salva o novo estado do tema nas preferências compartilhadas
            sharedPreferences.edit().putBoolean("isDarkMode", isChecked).apply()

            // Define o tema com base no estado do Switch
            if (isChecked) {
                requireActivity().setTheme(R.style.Theme_FluentGPT)
            } else {
                requireActivity().setTheme(R.style.Base_Theme_FluentGPT_Light)
            }

            // Reinicia a atividade para aplicar o novo tema
            requireActivity().recreate()
        }

        val languageSpinner: Spinner = view.findViewById(R.id.languageSpinner)
        val languages = listOf(getString(R.string.english), getString(R.string.portuguese))

        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = adapter

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLanguage = languages[position]
                if (selectedLanguage == getString(R.string.portuguese)) {
                    // Definir novo contexto com configuração do idioma português
                    val config = Configuration(resources.configuration)
                    config.setLocale(Locale("pt"))
                    requireActivity().applicationContext.createConfigurationContext(config)
                } else {
                    // Definir novo contexto com configuração do idioma inglês padrão
                    val config = Configuration(resources.configuration)
                    config.setLocale(Locale.getDefault())
                    requireActivity().applicationContext.createConfigurationContext(config)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nada selecionado
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}