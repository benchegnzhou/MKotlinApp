package zbc.com.cn.view.fragment

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import org.jetbrains.anko.*

import com.zbc.mvp.impl.BaseMvpFrament
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView
import zbc.com.cn.R
import zbc.com.cn.presenter.AboutPresenter
import zbc.com.cn.view.activity.LoginActivity

/**
 * 关于我们
 *
 * 本模块主要联系使用anko创建ui界面
 */
class AboutFragment : BaseMvpFrament<AboutPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.UI {
            nestedScrollView {
                verticalLayout {
                    imageView {
                        imageResource = R.mipmap.ic_launcher
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    textView("GitHub") {
                        textColor = R.color.colorPrimary
                        textSize = 40f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView("By ZBC") {
                        textColor = R.color.colorPrimary
                        textSize = 40f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    //还可以直接传入string资源文件
                    textView(R.string.open_source_licenses) {
                        textColor = R.color.colorPrimary
                        textSize = 40f
                        //响应点击事件，可以直接写
                        onClick {
                                alert {
                                    customView{
                                        scrollView {
                                            textView {
                                                padding= dip(resources.getDimension(R.dimen.qb_px_10))
                                            }
                                    }
                                }

                            }
                        }
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                }
            }
        }

    }

    /**
     * 启动activity并传递参数
     *
     */
    private fun openDetailActivity() {
        context!!.startActivity<LoginActivity>(
            "TITLE_KEY" to "anime.name",
            "DESCRIPTION_KEY" to "anime.description",
            "IMDB_LINK_KEY" to "anime.imdbLink",
            "IMAGE_KEY" to "anime.imageDrawable"
        )
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}