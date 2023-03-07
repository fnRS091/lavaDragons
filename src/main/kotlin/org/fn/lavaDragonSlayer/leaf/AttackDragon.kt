package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Constants.SAFE_TILE1
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import java.util.concurrent.Callable

class AttackDragon (script: Script) : Leaf<Script>(script, "Attacking dragon") {
    override fun execute() {
        var mob = Npcs.stream().id(Constants.MOB_ID).nearest().first()

        // if(!Players.local().inCombat()) {
        //     mob = Npcs.stream().nearest().id(Constants.MOB_ID).first()
        // }

        if (mob.healthPercent() >= 1 && !mob.healthBarVisible() && Players.local().interacting() != mob) {
            if (mob.inViewport() && !Players.local().inCombat()) {
                mob.interact("Attack")
            } else if (!mob.inViewport()) {
                Camera.turnTo(mob)
            }
        }

        if (Players.local().tile() != SAFE_TILE1) {
            walkToSafeTile()
        }

        Condition.wait({ Players.local().interacting() != mob}, 500, 25)
    }

    private fun walkToSafeTile() {
        Movement.step(SAFE_TILE1, 0)
        Condition.wait(Callable { Players.local().tile() == SAFE_TILE1 }, 200, 25)
    }
}