package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseObserver
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: liu
 * date: 2019/1/16 17:38
 * 文章列表
 */
class ArticleFragPresenter(val context: Context) : BasePresenter<ContractProjectAndArticle.ArticleView>(), ContractProjectAndArticle.ArticleModel {

    override fun searchArticle(chapterId: Int, page: Int,keyWords: String) {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .searchArticle(chapterId,page,keyWords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<Article>(){
                    override fun onSuccess(bean: Article) {
                        mRootView.searchArticleSuccess(bean.datas!!)
                    }

                    override fun onFailed(msg: String) {
                        mRootView.getOrSearchFailed(msg)
                    }

                })
    }

    override fun getArticleList(chapterId: Int, page: Int) {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .getArticles(chapterId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: BaseObserver<Article>(){
                    override fun onSuccess(bean: Article) {
                        mRootView.getArticleListSuccess(bean.datas!!)
                    }

                    override fun onFailed(msg: String) {
                        mRootView.getOrSearchFailed(msg)
                    }
                })


    }


}