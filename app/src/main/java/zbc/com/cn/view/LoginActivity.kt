package zbc.com.cn.view

import android.os.Bundle
import android.view.View
import com.zbc.mvp.impl.BaseMvpActivity
import zbc.com.cn.R
import zbc.com.cn.presenter.LoginPresenter

class LoginActivity : BaseMvpActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun onViewStateRestored(saveInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }


    fun showTips(view: View, msg: String) {

    }

}