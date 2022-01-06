package com.example.testapp.ui.numbers

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrimeNumbersViewModel : ViewModel() {

    val viewState = MutableLiveData<ViewState>()

    var primeNumbersList = mutableListOf<Int>()
    var numbersIndex = 0


    sealed class ViewState {
        class ShowNames: ViewState()
        class EmptyNames : ViewState()
    }

    fun getPrimeNumbers(){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val max = 100
                for (i in 1..max){
                    var count  = 0
                    for (j in i downTo 1){
                        if (i%j == 0) count++
                    }
                    if (count == 2) primeNumbersList.add(i)
                }
            }
        }
    }

    fun getNextNumber(): Int{
        var number = 0
        if (primeNumbersList.size > numbersIndex + 1){
            number = primeNumbersList[numbersIndex]
            numbersIndex++
        }
        return number
    }
}
class PrimeNumbersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PrimeNumbersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrimeNumbersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}