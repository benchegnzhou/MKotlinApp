package zbc.com.cn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.zbc.mvp.impl.MainFragment
import zbc.com.cn.utils.UserMsg

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = findViewById<EditText>(R.id.et_1)
        val password = findViewById<EditText>(R.id.et_2)
        val isVisable = findViewById<EditText>(R.id.et_3)
        findViewById<TextView>(R.id.tv_ok).setOnClickListener { view ->
            when (view.id) {
                R.id.tv_ok -> {
                    UserMsg.userName = userName.text.toString().trim()
                    UserMsg.password = password.text.toString().trim().toLong()
                    UserMsg.visiable = "true" == (isVisable.text.toString().trim().toLowerCase())
                }

                else -> {

                }
            }
        }
        userName.setText(UserMsg.userName)
        password.setText(UserMsg.password.toString())
        isVisable.setText(UserMsg.visiable.toString())

        val mainFragment = MainFragment()

        Log.d("MainActivity", mainFragment.presenter.toString())
        Log.d("MainActivity", mainFragment.view.toString())
        Log.d("MainActivity", mainFragment.toString())
    }
}
