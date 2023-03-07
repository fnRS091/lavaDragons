package org.fn.lavaDragonSlayer.leaf

import org.fn.lavaDragonSlayer.Script
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.ScriptManager


class StopScript(script: Script) : Leaf<Script>(script, "Stopping script") {
    override fun execute() {
        ScriptManager.stop()
    }
}