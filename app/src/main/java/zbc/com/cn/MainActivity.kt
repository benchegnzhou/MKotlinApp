package zbc.com.cn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bennyhuo.github.view.config.Themer
import com.bennyhuo.tieguanyin.annotations.ActivityBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.navigation.NavigationView

import com.zbc.mvp.impl.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.menu_item_daynight.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.toast
import zbc.com.cn.modle.AccountManager
import zbc.com.cn.modle.onAccountStatesChangeListener
import zbc.com.cn.network.entities.User
import zbc.com.cn.utils.doOnLayoutAvailable
import zbc.com.cn.utils.no
import zbc.com.cn.utils.yes


@ActivityBuilder(flags = [Intent.FLAG_ACTIVITY_CLEAR_TOP])
class MainActivity : AppCompatActivity(), onAccountStatesChangeListener {


    val actionBarController by lazy {
        ActionBarController(this)
    }

    private val navigationController by lazy {
        NavigationController(navigationView, ::onNavItemChanged, ::handleNavigationHeaderClickEvent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Themer.applyProperTheme(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        initNavigationView()

        AccountManager.onAccountStatesChangeListenerList.add(this)
        val mainFragment = MainFragment()

        Log.d("MainActivity", mainFragment.presenter.toString())
        Log.d("MainActivity", mainFragment.view.toString())
        Log.d("MainActivity", mainFragment.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStatesChangeListenerList.remove(this)
    }

    private fun initNavigationView() {
        AccountManager.currentUser?.let { } ?: kotlin.run {

        }
        AccountManager.isLogedIn()
            .yes {
                navigationController.useLoginLayout()
            }
            .otherwise {
                navigationController.useNoLoginLayout()
            }
        navigationController.selectProperItem()
    }

    private fun upDateNavigationView(user: User) {
        navigationView.doOnLayoutAvailable {

            usernameView.text = user.name
            emailView.text = user.email ?: ""

            avatarView.setTextAndColorSeed(
                user.name?.toUpperCase(),
                user.name?.hashCode().toString()
            )
            Glide
                .with(this)
                .load(user.avatar_url)
                .apply(
                    RequestOptions()
                        .dontAnimate()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(avatarView)
        }
    }

    override fun onLogin(user: User) {
        navigationController.useLoginLayout()
    }

    override fun onLogout() {
        navigationController.useNoLoginLayout()
    }

    private fun onNavItemChanged(navViewItem: NavViewItem) {
        drawer_layout.afterClosed {
            showFragment(R.id.fragmentContainer, navViewItem.fragmentClass, navViewItem.arguements)
            title = navViewItem.title
        }
    }

    private fun handleNavigationHeaderClickEvent() {
        AccountManager.isLogedIn().no {
            startLoginActivity()
        }.otherwise {
            launchUI {
                if (confirm("提示", "确认注销吗?")) {
                    AccountManager
                        .logout()
                        .subscribe({
                            toast("注销成功")
                        }, {
                            it.printStackTrace()
                        })
                } else {
                    toast("取消了")
                }
            }
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_actionbar, menu)
        menu.findItem(R.id.dayNight).actionView.dayNightSwitch.apply {
            isChecked = Themer.currentTheme() == Themer.ThemeMode.DAY

            onCheckedChange { buttonView, isChecked ->
                Themer.toggle(this@MainActivity)
            }
        }
        return true
    }
}
