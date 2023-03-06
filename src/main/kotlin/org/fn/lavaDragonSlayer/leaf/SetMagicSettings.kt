package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.powbot.api.Condition
import org.powbot.api.Notifications
import org.powbot.api.rt4.Combat
import org.powbot.api.rt4.Components
import org.powbot.api.rt4.Game
import org.powbot.api.script.tree.Leaf
import java.util.concurrent.Callable

class SetMagicSettings(script: Script) : Leaf<Script>(script, "Setting magic settings") {
    override fun execute() {
        if (Game.tab(Game.Tab.ATTACK)) {
            Condition.wait(Callable { Components.stream().widget(593).first().visible() }, 200, 15)

            if(Components.stream().widget(593).first().visible()){

                Components.stream().widget(593).action("Choose spell").first().click()

                Condition.wait(Callable { Components.stream().widget(201).first().visible() }, 300, 15)

                Components.stream().widget(201).action("Fire Strike").first().click()

                Condition.wait(Callable { Components.stream().widget(593).first().visible() }, 300, 15)

                if (Combat.autoRetaliate()) {
                    Components.stream().widget(593).action("Auto retaliate").first().click()
                }
            }
        }
    }
}