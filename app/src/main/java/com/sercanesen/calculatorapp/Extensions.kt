package com.sercanesen.calculatorapp

import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import java.math.BigInteger

const val MAX_VALUE_ERROR = "ERR-VALUE IS OUT OF MAX RANGE"

fun Activity.setScreenFullSize() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.hide(WindowInsets.Type.statusBars())
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

fun Activity.changeStatusBarColor(colorValue: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(colorValue);
    }
}

fun customLogger(data: String) {
    Log.e("GLOBAL_TAG", data)
}

fun String.getDisplayFormattedValue(): String {
    if (this.equals("ERR-VALUE IS OUT OF MAX RANGE")) {
        return MAX_VALUE_ERROR
    }
    // 1.001.975.000
    //.000
    //.975.000
    var currentText = this
    var formattedText = ""
    for (i in 4..currentText.length step 3) {
        var x = currentText.toBigInteger() % BigInteger("1000")
        if (x.toString().length == 3) {
            formattedText = ".$x$formattedText"
        }
        if (x.toString().length == 2) {
            formattedText = ".0$x$formattedText"
        }
        if (x.toString().length == 1) {
            formattedText = ".00$x$formattedText"
        }
        currentText = currentText.dropLast(3)
        customLogger("Current Text: $currentText")
    }
    return currentText + formattedText
}
