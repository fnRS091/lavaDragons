package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.Notifications
import org.powbot.api.script.tree.Leaf

class XXX(script: Script) : Leaf<Script>(script, "Placeholder") {
    override fun execute() {
        Condition.sleep(1000)
        Notifications.showNotification("XXX PATH")
    }
}