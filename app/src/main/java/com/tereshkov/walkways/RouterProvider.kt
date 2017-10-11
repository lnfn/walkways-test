package com.tereshkov.walkways

import ru.terrakok.cicerone.Router

/**
 * Created by Tereshkov on 09.10.2017.
 */
interface RouterProvider {
    fun getRouter(): Router
}