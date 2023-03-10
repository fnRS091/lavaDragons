package org.fn.lavaDragonSlayer.modules

import org.fn.lavaDragonSlayer.Constants.FOOD
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import org.powbot.mobile.script.ScriptManager

class HealthChecker : Runnable {

    override fun run() {
        while (!ScriptManager.isStopping()) {
            if (!Bank.inViewport() && Game.clientState() == 30) {
                if (Players.local().healthPercent() < 95) {
                    if (Game.tab(Game.Tab.INVENTORY)) {
                        var food: Item = Inventory.stream().name(FOOD).first()
                        food.click()
                        Condition.sleep(800)
                        Game.closeOpenTab()
                    } else {
                        Game.tab(Game.Tab.INVENTORY)
                    }
                }
            }

            try {
                Thread.sleep(700)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}