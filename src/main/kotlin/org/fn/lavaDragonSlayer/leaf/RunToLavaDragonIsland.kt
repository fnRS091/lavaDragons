package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.Constants.GATE
import org.fn.lavaDragonSlayer.Constants.LAVA_DRAGON_ISLAND
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Camera
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf
import java.util.concurrent.Callable

class RunToLavaDragonIsland(script: Script) : Leaf<Script>(script, "Running to island") {
    override fun execute() {
        Movement.moveTo(GATE)
    }
}