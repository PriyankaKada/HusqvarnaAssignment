package com.husqvarna.movies.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object CircularProgress {

    fun  circularProgress(context: Context) : CircularProgressDrawable {
        val circularProgress = CircularProgressDrawable(context)
        circularProgress.strokeWidth = 5f
        circularProgress.centerRadius = 30f
        circularProgress.start()

        return circularProgress
    }
}