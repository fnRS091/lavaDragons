package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import java.util.concurrent.Callable

class WithDrawItems(script: Script) : Leaf<Script>(script, "Withdrawing items") {
    override fun execute() {
        val food = script.config.food

        Bank.depositInventory()
        Condition.wait(Callable { Inventory.stream().isEmpty() }, 300, 15 )
        Bank.withdraw(Constants.RUNES[0], 600)
        Condition.wait(Callable { Inventory.stream().name(Constants.RUNES[0]).first().valid()}, 300, 15 )
        Bank.withdraw(Constants.RUNES[1], 250)
        Condition.wait(Callable { Inventory.stream().name(Constants.RUNES[1]).first().valid()}, 300, 15 )
        Bank.withdraw(food, 12)
        Condition.wait(Callable { Inventory.stream().name(food).first().valid()}, 300, 15 )
    }
}