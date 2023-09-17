package com.anne.play.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel(){
    private val _position = MutableLiveData(CourseTabs.HOME_PAGE)
    val position : LiveData<CourseTabs> = _position
    fun onPositionChanged(position:CourseTabs){
         _position.value = position
    }

}
