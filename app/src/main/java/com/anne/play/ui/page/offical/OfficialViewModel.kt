package com.anne.play.ui.page.offical

import android.app.Application
import com.anne.play.logic.base.repository.BaseArticlePagingRepository
import com.anne.play.logic.base.viewmodel.BaseViewModel

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
class OfficialViewModel(application: Application) : BaseViewModel(application) {
    override val repositoryArticle: BaseArticlePagingRepository
        get() = OfficialRepository(getApplication())

    override suspend fun getData() {
        (repositoryArticle as OfficialRepository).getTree(mutableTreeLiveData)
    }
}
