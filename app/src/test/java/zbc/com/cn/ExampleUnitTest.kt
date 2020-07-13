package zbc.com.cn

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import zbc.com.cn.utils.no
import zbc.com.cn.utils.otherWise

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val otherWise = false.no {
            print("false")
            1
        }.otherWise {
            print("true")
            2
        }
        Assert.assertEquals(otherWise,1)
    }
}
