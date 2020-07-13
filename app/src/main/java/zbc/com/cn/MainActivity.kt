package zbc.com.cn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.et_1)
        findViewById<EditText>(R.id.et_2)
        findViewById<EditText>(R.id.et_3)
        findViewById<TextView>(R.id.tv_ok).setOnClickListener { view ->
            when (view.id) {
                R.id.tv_ok -> {

                }

                else -> {

                }
            }
        }


    }
}
