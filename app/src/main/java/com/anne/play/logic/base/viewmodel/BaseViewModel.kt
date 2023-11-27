package com.anne.play.logic.base.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.model.PlayState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
abstract class BaseViewModel(application: Application) : BaseArticleViewModel(application) {
    protected val mutableTreeLiveData = MutableLiveData<PlayState<List<ClassifyModel>>>()
    val treeLiveData: LiveData<PlayState<List<ClassifyModel>>>
        get() = mutableTreeLiveData

    abstract suspend fun getData()
    fun getDataList() {
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private val _position = MutableLiveData(0)
    val position: LiveData<Int> = _position

    fun onPositionChanged(position: Int) {
        _position.value = position
    }

//    override val repositoryArticle: BaseArticlePagingRepository
//        get() = OfficialRepository(getApplication<>())
}
