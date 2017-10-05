package com.tereshkov.walkways

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


/**
 * Created by Tereshkov on 01.10.2017.
 */
class LocalCiceroneHolder {
    private val containers: HashMap<String, Cicerone<Router>> = hashMapOf()

    fun getCicerone(containerTag: String): Cicerone<Router> {
        if (!containers.containsKey(containerTag)) {
            containers.put(containerTag, Cicerone.create())
        }
        return containers[containerTag] as Cicerone<Router>
    }
}