package com.anne.play.ui.page.project

import android.app.Application
import com.anne.play.logic.base.repository.BaseArticlePagingRepository
import com.anne.play.logic.base.viewmodel.BaseViewModel

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
class ProjectViewModel(application: Application) : BaseViewModel(application) {
    override val repositoryArticle: BaseArticlePagingRepository
        get() = ProjectRepository(getApplication())

    override suspend fun getData() {
        (repositoryArticle as ProjectRepository).getTree(mutableTreeLiveData)
    }
}
