package com.anne.play.ui.page.offical

import android.app.Application
import com.anne.play.logic.viewmodel.BaseArticlePagingRepository
import com.anne.play.logic.viewmodel.BaseViewModel

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
class OfficalViewModel(application: Application) : BaseViewModel(application) {
    override val repositoryArticle: BaseArticlePagingRepository
        get() = OfficialRepository(getApplication())

    override suspend fun getData() {
        (repositoryArticle as OfficialRepository).getTree(mutableTreeLiveData)
    }
}
