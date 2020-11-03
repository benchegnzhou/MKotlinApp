package zbc.com.cn.view.common

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bennyhuo.github.view.common.CommonListAdapter
import com.bennyhuo.github.view.common.CommonListPresenter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.zbc.mvp.impl.BaseMvpFrament
import kotlinx.android.synthetic.main.list.*
import org.jetbrains.anko.support.v4.toast
import zbc.com.cn.R

abstract class CommonListFragment<DataType, out Presenter : CommonListPresenter<DataType, CommonListFragment<DataType, Presenter>>> :
    BaseMvpFrament<Presenter>() {
    protected abstract val adapter: CommonListAdapter<DataType>

    protected val errorInfoView by lazy {
        ErrorInfoView(rootView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    handler: Handler
    ): View? {
        return inflater.inflate(R.layout.list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        m_refresh_layout.setRefreshHeader(ClassicsHeader(context))
        m_refresh_layout.setRefreshFooter(ClassicsFooter(context))

        recyclerView.adapter = LuRecyclerViewAdapter(adapter)

        recyclerView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()



        m_refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                m_refresh_layout.finishLoadMore()
                m_refresh_layout.finishRefresh()
                presenter::refreshData
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                m_refresh_layout.finishLoadMore()
                m_refresh_layout.finishRefresh()
                presenter::loadMore
            }
        })


        presenter.initData()
    }


    fun onDataInit(data: GitHubPaging<DataType>) {
        adapter.data.clear()
        adapter.data.addAll(data)
        recyclerView.setNoMore(data.isLast)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        refreshView.isRefreshing = false
        dismissError()
    }

    fun onDataRefresh(data: GitHubPaging<DataType>) {
        onDataInit(data)
    }

    fun onDataInitWithNothing() {
        showError("No Data.")
        recyclerView.setNoMore(true)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        refreshView.isRefreshing = false
        errorInfoView.isClickable = false
    }

    fun onDataInitWithError(error: String) {
        showError(error)
        errorInfoView.onClick {
            presenter.initData()
        }
    }

    fun onDataRefreshWithError(error: String) {
        if (adapter.data.isEmpty()) {
            showError(error)
            errorInfoView.onClick {
                presenter.initData()
            }
        } else {
            toast(error)
        }
    }

    fun onMoreDataLoaded(data: GitHubPaging<DataType>) {
        adapter.data.update(data)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        recyclerView.setNoMore(data.isLast)
        dismissError()
    }

    fun onMoreDataLoadedWithError(error: String) {
        showError(error)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        errorInfoView.onClick {
            presenter.initData()
        }
    }

    protected open fun showError(error: String) {
        errorInfoView.show(error)
    }

    protected open fun dismissError() {
        errorInfoView.dismiss()
    }
}