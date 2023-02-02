
package  com.loanspeed.speed.net

import com.loanspeed.speed.ext.isNetAvailable
import com.loanspeed.speed.ext.show
import okhttp3.Interceptor
import okhttp3.Response

class NetInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response.code
        val available = isNetAvailable()
        if (!available) {
            //无网络
           ("There is currently no network, please refresh after connecting to the network").show()
        } else {
            when (code) {
                404 -> ("Request does not exist...").show()
                408 -> ("Network connection timed out, please try again later").show()
                500, 501, 502, 503, 504, 505 -> ("The network has gone away, please wait a moment...").show()
            }
        }
        return response
    }

}