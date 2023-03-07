package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Components
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.LoginScreenWorldSwitcher
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.input.Touchscreen

class LogIntoRandomWorld(script: Script) : Leaf<Script>(script, "Logging into world") {
    override fun execute() {
        if (Game.clientState() == 30) return

        Condition.sleep(2000)

        Touchscreen.tap(130, 460, 50)

        Condition.sleep(15000)

        if (LoginScreenWorldSwitcher.isOpen()) {
            LoginScreenWorldSwitcher.switchToP2P()
        }

        Touchscreen.tap(485, 250, 50)

        if (Condition.wait({ Components.stream().widget(378).action("Play").first().visible() }, 300, 25)) {
            Components.stream().widget(378).action("Play").first().click()
        }
    }
}