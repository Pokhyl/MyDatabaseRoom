package com.example.mydatabaseroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {
 

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()
 


 
    fun saveOrUpdate(name: String, email: String) {

                insertSubscriber(Subscriber(0, name, email))

            }
 
    private fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        repository.insert(subscriber)
    }
    fun getSavedSubscribers() = liveData {
        repository.subscribers.collect {
            emit(it)
        }
//        repository.subscribers.collect {
//            emit(it)
       // }
    }
 
}