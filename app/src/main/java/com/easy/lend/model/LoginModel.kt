package com.easy.lend.model



class LoginModel  {
    /**
     * token : kmubaZibZmdlaWKclGtjaJKZaZOZaJ2VYGxtbJpukXWvq9p62c2mgaNgnceSqqyC2Jpu1puhrdWmqW6huYmoh8Shm2nc0WdpoGhx
     * user_id : 45767
     */
    var token: String = ""
    var user_id: String? = null
    override fun toString(): String {
        return "LoginBean{" +
                "token='" + token + '\''.toString() +
                ", user_id='" + user_id + '\''.toString() +
                '}'.toString()
    }
}