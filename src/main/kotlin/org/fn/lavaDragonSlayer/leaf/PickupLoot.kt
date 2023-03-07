package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Area
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf

class PickupLoot (script: Script) : Leaf<Script>(script, "Pickup loot") {
    override fun execute() {

        val playerArea = Area(
            Tile(Players.local().x() - 6, Players.local().y()+ 6),
            Tile(Players.local().x() + 6, Players.local().y()- 6)
        )

        GroundItems.stream().within(playerArea).list().forEach{
            if (it.name() !in Constants.IGNORE_LOOT_TABLE) {
                Movement.walkTo(it.tile())
                it.click("Take")
                Condition.wait({ !it.valid() }, 200, 15)
            }
        }
    }
}