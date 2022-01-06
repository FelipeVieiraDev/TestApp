package com.example.testapp.ui.numbers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_prime_numbers.*

class PrimeNumbersFragment : Fragment() {

    lateinit var mHandler: Handler

    private val updateTextTask = object : Runnable {
        override fun run() {
            setHandle()
            mHandler.postDelayed(this, 3000)
        }
    }

    private val viewModel: PrimeNumbersViewModel by viewModels {
        PrimeNumbersViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prime_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getPrimeNumbers()
        mHandler = Handler(Looper.getMainLooper())
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setHandle() {
        var number = viewModel.getNextNumber()
        if (number > 0) {
            numberTv.text = number.toString()
        } else {
            mHandler.removeCallbacks(updateTextTask)
        }
    }

    override fun onResume() {
        mHandler.post(updateTextTask)
        super.onResume()
    }

    override fun onPause() {
        mHandler.removeCallbacks(updateTextTask)
        super.onPause()
    }

}

