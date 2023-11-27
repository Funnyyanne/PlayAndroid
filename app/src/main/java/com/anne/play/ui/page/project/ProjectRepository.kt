package com.anne.play.ui.page.project

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anne.play.logic.base.paging.ProjectPagingSource
import com.anne.play.logic.base.repository.BaseArticleRepository
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.model.Query
import com.anne.play.logic.network.PlayAndroidNetwork

/**
 *
 * Author:AnneLo
 * Time:2023/11/27
 */
class ProjectRepository constructor(val application: Application) :
    BaseArticleRepository(application) {
    override suspend fun getArticleTree(): BaseModel<List<ClassifyModel>> {
        return PlayAndroidNetwork.getProjectTree()
    }

    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
        ),
    ) {
        ProjectPagingSource(query.cid)
    }.flow
}
