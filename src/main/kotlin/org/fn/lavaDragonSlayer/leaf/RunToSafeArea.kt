package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf

class RunToSafeArea(script: Script) : Leaf<Script>(script, "Running to safe area") {
    override fun execute() {
        Movement.walkTo(Constants.SAFE_AREA1.centralTile)
    }
}