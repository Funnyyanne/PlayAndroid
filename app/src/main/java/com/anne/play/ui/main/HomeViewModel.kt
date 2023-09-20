package com.anne.play.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anne.play.ui.main.CourseTabs

class HomeViewModel:ViewModel(){
    private val _position = MutableLiveData(CourseTabs.HOME_PAGE)
    val position : LiveData<CourseTabs> = _position
    fun onPositionChanged(position: CourseTabs){
         _position.value = position
    }

}
