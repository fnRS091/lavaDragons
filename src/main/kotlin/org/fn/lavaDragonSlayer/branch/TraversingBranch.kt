package org.fn.lavaDragonSlayer.branch

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.Constants.LAVA_DRAGON_ISLAND
import org.fn.lavaDragonSlayer.Constants.SAFE_AREA1
import org.fn.lavaDragonSlayer.Constants.SAFE_TILE1
import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.leaf.*
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class AtLavaDragonIsland(script: Script) : Branch<Script>(script, "At Lavadragonisland") {
    override val successComponent: TreeComponent<Script> = MagicSettings(script)
    override val failedComponent: TreeComponent<Script> = RunToLavaDragonIsland(script)

    override fun validate(): Boolean {
        return (LAVA_DRAGON_ISLAND.contains(Players.local().tile()))
    }
}

class MagicSettings(script: Script) : Branch<Script>(script, "Magic settings") {
    override val successComponent: TreeComponent<Script> = EnoughStamina(script)
    override val failedComponent: TreeComponent<Script> = SetMagicSettings(script)

    override fun validate(): Boolean {
        // Game.tab(Game.Tab.ATTACK)
        // Components.stream().widget(593).id(22).first().textureId() != 780
        return (true) // Fix later
    }
}

class EnoughStamina(script: Script) : Branch<Script>(script, "waiting for stam") {
    override val successComponent: TreeComponent<Script> = AtSafearea(script)
    override val failedComponent: TreeComponent<Script> = DoNothing(script)

    override fun validate(): Boolean {
        return (Movement.energyLevel() >= 20 || SAFE_TILE1.distanceTo(Players.local().tile()) < 5)
    }
}

class AtSafearea(script: Script) : Branch<Script>(script, "At Safearea") {
    override val successComponent: TreeComponent<Script> = AtSafespot(script)
    override val failedComponent: TreeComponent<Script> = RunToSafeArea(script)

    override fun validate(): Boolean {
        return (SAFE_AREA1.contains(Players.local().tile()))
    }
}

class AtSafespot(script: Script) : Branch<Script>(script, "At Safespot") {
    override val successComponent: TreeComponent<Script> = DragonAlive(script)
    override val failedComponent: TreeComponent<Script> = RunToSafespot(script)

    override fun validate(): Boolean {
        return (SAFE_TILE1 == Players.local().tile())
    }
}
