package com.easy.lend.model



class NoteModel  {
    var title: String? = ""
        get() = if (field == null) "" else field
    var content: String? = ""
        get() = if (field == null) "" else field
    var time = 0L
}