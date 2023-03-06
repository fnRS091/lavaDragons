package org.fn.lavaDragonSlayer.branch

import org.fn.lavaDragonSlayer.Constants.MOB_ID
import org.fn.lavaDragonSlayer.Script
import org.fn.lavaDragonSlayer.leaf.AttackDragon
import org.fn.lavaDragonSlayer.leaf.DoNothing
import org.fn.lavaDragonSlayer.leaf.PickupLoot
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class DragonAlive(script: Script) : Branch<Script>(script, "Dragon alive") {
    var mob: Npc = Npcs.nil()
    override val successComponent: TreeComponent<Script> = PlayerFighting(script)
    override val failedComponent: TreeComponent<Script> = LootOnFloor(script)

    override fun validate(): Boolean {
        mob = Npcs.stream().id(MOB_ID).nearest().first()

        return (mob.valid() && mob.distance() < 20)
    }
}

class PlayerFighting(script: Script) : Branch<Script>(script, "Player/none attck dragon") {
    override val successComponent: TreeComponent<Script> = AttackDragon(script)
    override val failedComponent: TreeComponent<Script> = DoNothing(script)

    override fun validate(): Boolean {
        var mob = Npcs.stream().id(MOB_ID).nearest().first()
        return (mob.interacting() == Actor.Nil || mob.interacting() == Players.local())
    }
}

