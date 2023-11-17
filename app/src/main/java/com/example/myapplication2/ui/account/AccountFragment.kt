package com.example.myapplication2.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAccount
        accountViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val darkmodeView: TextView = binding.textDarkmode
        accountViewModel.darkmode.observe(viewLifecycleOwner) {
            darkmodeView.text = it.toString()
        }

        val usernameView: TextView = binding.textUsername
        accountViewModel.username.observe(viewLifecycleOwner) {
            usernameView.text = it
        }

        val darkModeSwitch: Switch = binding.darkModeSwitch
        darkModeSwitch.isChecked = accountViewModel.darkmode.value ?: false
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            accountViewModel.setDarkmode(isChecked)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}