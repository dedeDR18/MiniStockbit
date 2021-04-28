package com.example.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created on : 28/04/21 | 06.33
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor()

    )

    fun diskIO(): Executor = diskIO

}