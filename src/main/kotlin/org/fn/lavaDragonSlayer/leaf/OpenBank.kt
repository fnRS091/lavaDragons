package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Camera
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf

class OpenBank(script: Script) : Leaf<Script>(script, "Opening bank") {
    override fun execute() {
        if (Bank.inViewport()) {
            Bank.open()
        } else {
            Movement.moveToBank()
            Camera.turnTo(Bank.nearest().tile())
        }
    }
}


