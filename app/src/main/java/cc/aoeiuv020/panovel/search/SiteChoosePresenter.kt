package cc.aoeiuv020.panovel.search

import cc.aoeiuv020.panovel.Presenter
import cc.aoeiuv020.panovel.data.DataManager
import cc.aoeiuv020.panovel.data.entity.Site
import cc.aoeiuv020.panovel.report.Reporter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.error
import org.jetbrains.anko.uiThread

/**
 * Created by AoEiuV020 on 2018.05.13-18:29:22.
 */
class SiteChoosePresenter : Presenter<SiteChooseActivity>() {
    fun start() {
        view?.doAsync({ e ->
            val message = "加载网站列表失败，"
            Reporter.post(message, e)
            error(message, e)
            view?.runOnUiThread {
                view?.showError(message, e)
            }
        }) {
            val list = DataManager.listSites()
            uiThread {
                view?.showSiteList(list)
            }
        }
    }

    fun enabledChange(site: Site) {
        view?.doAsync({ e ->
            val message = "${if (site.enabled) "启用" else "禁用"}网站失败，"
            Reporter.post(message, e)
            error(message, e)
            view?.runOnUiThread {
                view?.showError(message, e)
            }
        }) {
            DataManager.siteEnabledChange(site)
        }
    }

    fun pinned(site: Site) {
        view?.doAsync({ e ->
            val message = "置顶网站失败，"
            Reporter.post(message, e)
            error(message, e)
            view?.runOnUiThread {
                view?.showError(message, e)
            }
        }) {
            DataManager.pinned(site)
        }
    }

    fun cancelPinned(site: Site) {
        view?.doAsync({ e ->
            val message = "取消置顶网站失败，"
            Reporter.post(message, e)
            error(message, e)
            view?.runOnUiThread {
                view?.showError(message, e)
            }
        }) {
            DataManager.cancelPinned(site)
        }
    }
}