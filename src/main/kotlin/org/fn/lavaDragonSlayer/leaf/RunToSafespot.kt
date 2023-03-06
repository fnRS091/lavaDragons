package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.Constants.SAFE_TILE1
import org.powbot.api.Condition
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Camera
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class RunToSafespot(script: Script) : Leaf<Script>(script, "Running to safespot") {
    override fun execute() {
        Movement.walkTo(SAFE_TILE1)
    }
}