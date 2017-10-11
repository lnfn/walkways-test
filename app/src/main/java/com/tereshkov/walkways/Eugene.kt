package com.tereshkov.walkways

import javax.inject.Inject

/**
 * Created by Tereshkov on 06.10.2017.
 */
class Eugene @Inject constructor() {
    val name = "Eugene Tereshkov"
    val old = 25

    fun goOn() = "$name go on"
}