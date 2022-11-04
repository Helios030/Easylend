package com.easy.lend.model



class RegionModel  {
    /**
     * id : 1
     * name : ACEH
     */
    var id: Int = 0
    var name: String = ""
    var parent_id: String = ""
    override fun toString(): String {
        return "RegionBean(id=$id, name='$name', parent_id='$parent_id')"
    }


}
