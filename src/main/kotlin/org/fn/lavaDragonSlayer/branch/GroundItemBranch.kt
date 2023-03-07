package org.fn.lavaDragonSlayer.branch

import org.fn.lavaDragonSlayer.Constants.IGNORE_LOOT_TABLE
import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.leaf.DoNothing
import org.fn.lavaDragonSlayer.leaf.PickupLoot
import org.powbot.api.rt4.GroundItems
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class LootOnFloor(script: Script) : Branch<Script>(script, "Dragon loot on floor") {
    private var lootableItem = false

    override val successComponent: TreeComponent<Script> = PickupLoot(script)
    override val failedComponent: TreeComponent<Script> = DoNothing(script)

    override fun validate(): Boolean {
        GroundItems.stream().list().forEach{
            if (it.name() !in IGNORE_LOOT_TABLE) {
                lootableItem = true
                return true
            }
        }
        return lootableItem
    }
}