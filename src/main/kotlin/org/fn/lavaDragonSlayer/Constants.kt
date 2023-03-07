package org.fn.lavaDragonSlayer

import org.powbot.api.Area
import org.powbot.api.Tile

object Constants {
    // Runes
    val FIRE_STRIKE_RUNES = listOf<String>("Air rune", "Mind rune")
    val FIRE_BOLT_RUNES = listOf<String>("Air rune", "Chaos rune")

    // Required Items
    var FOOD: String = "Bass"
    var RUNES: List<String> = FIRE_STRIKE_RUNES

    // Wilderness Safespot + Bank
    val ENCLAVE: Tile = Tile(3134, 3629)
    val GRAND_EXCHANGE: Tile = Tile(3164, 3485)

    val GATE = Tile(3200, 3853)
    val LAVA_DRAGON_ISLAND: Area = Area(Tile(3174, 3860), Tile(3229, 3800))

    // Safe spot behind tree
    val SAFE_TILE1: Tile = Tile(3183, 3838)
    val SAFE_AREA1: Area = Area(Tile(3179, 3840), Tile(3184, 3833))

    const val MOB_NAME: String = "Lava dragon"
    const val MOB_ID: Int = 6593

    val IGNORE_LOOT_TABLE = listOf("Burnt bones", "Vial", "White berries", "Black dagger", "Jug", "Jug of wine")
}
