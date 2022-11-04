
package com.easy.lend.model


data class BaseModel<T>(var ret: T,
                        var success: Boolean = false,
                        var msg: String? = null,
                        var code: Int = 0)
