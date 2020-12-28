package com.app.filesender.interfaces

import android.content.DialogInterface

interface DialogListener {
    fun onOk(dialog: DialogInterface)
    fun onCancel(dialog: DialogInterface)
}