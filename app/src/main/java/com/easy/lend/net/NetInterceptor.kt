
package  com.easy.lend.net

import com.easy.lend.ext.isNetAvailable
import com.easy.lend.ext.toast
import okhttp3.Interceptor
import okhttp3.Response

class NetInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response.code
        val available = isNetAvailable()
        if (!available) {
            //无网络
            toast("There is currently no network, please refresh after connecting to the network")
        } else {
            when (code) {
                404 -> toast("Request does not exist...")
                408 -> toast("Network connection timed out, please try again later")
                500, 501, 502, 503, 504, 505 -> toast("The network has gone away, please wait a moment...")
            }
        }
        return response
    }

}