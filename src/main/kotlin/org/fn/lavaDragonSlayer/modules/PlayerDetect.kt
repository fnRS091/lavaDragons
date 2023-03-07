package org.fn.lavaDragonSlayer.modules

import org.fn.lavaDragonSlayer.Constants.ENCLAVE_AREA
import org.powbot.api.Area
import org.powbot.api.Condition
import org.powbot.api.Notifications
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.mobile.script.ScriptManager

class PlayerDetect : Runnable {
    var playerInArea: Boolean = false

    override fun run() {
        while (!ScriptManager.isStopping()) {
            if (Components.stream().widget(90).id(45).first().visible() && !ENCLAVE_AREA.contains(Players.local())) {
                val playerTile: Tile = Players.local().tile()

                val tile1 = Tile(playerTile.x - 12, playerTile.y + 12)
                val tile2 = Tile(playerTile.x + 12 , playerTile.y - 12)

                val curPlayerArea = Area(tile1, tile2)

                playerInArea = if (curPlayerArea.contains(Players.stream().notLocalPlayer().any())) true else false

                if (playerInArea) {
                    val enemyPlayer = Players.stream().notLocalPlayer().nearest().first()

                    val wildernessLevels: List<String> = getLevel(Components.stream().widget(90).id(49).first().text())

                    val wildernessLOW = wildernessLevels[0].toInt()
                    val wildernessHIGH = wildernessLevels[1].toInt()

                    if ((wildernessLOW..wildernessHIGH).contains(enemyPlayer.combatLevel)) {
                        // LOGOUT :((
                        if (Game.tab(Game.Tab.LOGOUT)) {
                            Condition.wait({ Components.stream().action("Logout").first().click() }, 200, 20)
                        }
                    } else {
                        Notifications.showNotification("$wildernessLOW $wildernessHIGH safe")
                    }
                }
            } else playerInArea = false

            try {
                Thread.sleep(250)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun getLevel(text: String): List<String> {
        return text.split("-")
    }
}
