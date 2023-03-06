package org.fn.lavaDragonSlayer.branch

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.leaf.LogIntoRandomWorld
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.stream.item.InventoryItemStream
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent
import org.powbot.manager.grpc.ClientState

class LoggedIn(script: Script) : Branch<Script>(script, "Is logged in") {
    override val successComponent: TreeComponent<Script> = InventorySpace(script)
    override val failedComponent: TreeComponent<Script> = LogIntoRandomWorld(script)

    override fun validate(): Boolean {
        return (Game.clientState() == 30)
    }
}

class InventorySpace(script: Script) : Branch<Script>(script, "Has inventory space") {
    override val successComponent: TreeComponent<Script> = HasReqItems(script)
    override val failedComponent: TreeComponent<Script> = AtBank(script)

    override fun validate(): Boolean {
        return (Inventory.stream().count() <= 20)
    }
}

class HasReqItems(script: Script) : Branch<Script>(script, "Has required items") {
    override val successComponent: TreeComponent<Script> = AtLavaDragonIsland(script)
    override val failedComponent: TreeComponent<Script> = AtBank(script)

    override fun validate(): Boolean {
        val food = script.config.food

        return (Inventory.stream().name(Constants.RUNES[0]).first().valid()
                && Inventory.stream().name(Constants.RUNES[1]).first().valid()
                && Inventory.stream().name(food).first().valid())
    }
}

