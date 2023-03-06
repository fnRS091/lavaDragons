package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.Constants.ENCLAVE
import org.fn.lavaDragonSlayer.Constants.GRAND_EXCHANGE
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class RunToNearestBank(script: Script) : Leaf<Script>(script, "Running to bank") {
    override fun execute() {
        // Will auto wait, no Condition.wait needed
        if (Players.local().tile().distanceTo(ENCLAVE) < Players.local().tile().distanceTo(GRAND_EXCHANGE)) {
            Movement.moveTo(ENCLAVE)
        } else {
            Movement.moveTo(GRAND_EXCHANGE)
        }
    }
}