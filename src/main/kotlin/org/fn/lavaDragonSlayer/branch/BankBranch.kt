package org.fn.lavaDragonSlayer.branch

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.leaf.OpenBank
import org.fn.lavaDragonSlayer.leaf.RunToNearestBank
import org.fn.lavaDragonSlayer.leaf.WithDrawItems
import org.fn.lavaDragonSlayer.leaf.XXX
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class AtBank(script: Script) : Branch<Script>(script, "At bank") {
    override val successComponent: TreeComponent<Script> = BankOpen(script)
    override val failedComponent: TreeComponent<Script> = RunToNearestBank(script)

    override fun validate(): Boolean {
        return (Bank.inViewport())
    }
}

class BankOpen(script: Script) : Branch<Script>(script, "Bank open") {
    override val successComponent: TreeComponent<Script> = HasRequiredItemsInBank(script)
    override val failedComponent: TreeComponent<Script> = OpenBank(script)

    override fun validate(): Boolean {
        return (Bank.opened())
    }
}

class HasRequiredItemsInBank(script: Script) : Branch<Script>(script, "Required Items in bank") {
    override val successComponent: TreeComponent<Script> = WithDrawItems(script)
    override val failedComponent: TreeComponent<Script> = XXX(script)

    override fun validate(): Boolean {
        val food = script.config.food
        return (Bank.stream().name(Constants.RUNES[0]).first().valid()
                && Bank.stream().name(Constants.RUNES[1]).first().valid()
                && Bank.stream().name(food).first().valid())
    }
}
