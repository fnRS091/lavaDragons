package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Camera
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class DoNothing(script: Script) : Leaf<Script>(script, "letting time pass") {
    override fun execute() {
        Condition.sleep(700)
    }
}