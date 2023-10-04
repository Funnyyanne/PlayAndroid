package com.anne.play.ui.page.offical

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anne.play.logic.base.paging.OfficialPagingSource
import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.ClassifyModel
import com.anne.play.logic.network.PlayAndroidNetwork
import com.anne.play.logic.repository.BaseArticleRepository
import com.anne.play.logic.viewmodel.Query

/**
 *
 * Author:AnneLo
 * Time:2023/9/30
 */
class OfficialRepository(application: Application) : BaseArticleRepository(application) {

    override suspend fun getArticleTree(): BaseModel<List<ClassifyModel>> {
        return PlayAndroidNetwork.getWxArticleTree()
    }

    override fun getPagingData(query: Query) = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
        ),
    ) {
        OfficialPagingSource(query.cid)
    }.flow
}
