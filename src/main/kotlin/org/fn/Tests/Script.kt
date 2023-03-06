package org.fn.Tests

import org.fn.lavaDragonSlayer.Constants
import org.fn.lavaDragonSlayer.modules.PlayerDetect
import org.powbot.api.*
import org.powbot.api.event.LoginEvent
import org.powbot.api.rt4.*
import org.powbot.api.script.*
import org.powbot.api.script.paint.Paint
import org.powbot.api.script.paint.PaintBuilder
import org.powbot.manager.grpc.ClientState
import org.powbot.mobile.input.TouchAction
import org.powbot.mobile.input.Touchscreen
import org.powbot.mobile.script.Logger.LogMessage
import org.powbot.mobile.service.MyPowBotService
import org.powbot.mobile.service.ScriptUploader
import org.powbot.proto.rt4.Interaction
import java.sql.ClientInfoStatus
import java.util.concurrent.Callable
import java.util.logging.Logger


@ScriptManifest(
    name="FnTests",
    description="Tests",
    version="0.0.1",
    category=ScriptCategory.MoneyMaking,
    author="Fn"
)

@ScriptConfiguration.List(
    [
        ScriptConfiguration(
            "Spell",
            "Spell you wish to cast. Fire staff required!",
            OptionType.STRING,
            allowedValues = arrayOf(
                "FIRE_STRIKE",
                "FIRE_BOLT",
            ),
        ),
    ]
)

class Script: AbstractScript() {
    private val logger = Logger.getLogger(this.javaClass.name)
    lateinit var mob: Npc

    var isItAlive: Boolean = false
    private val playerDetector: PlayerDetect = PlayerDetect()

    override fun onStart() {
        super.onStart()
        addPaint()

        // Thread(playerDetector).name = "PlayerDetect"
        // Thread(playerDetector).start()
        /*
        var world1 = 303
        var world2 = 351
        var goTo = 0

        if (Worlds.current().number == world1) {
            goTo = world2
        } else {
            goTo = world1
        }

        val world = World(goTo, 1, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE)

        Condition.wait(Callable { world.hop() }, 200, 20)
        */


    }

    override fun poll() {
        // attackMob()


        Condition.sleep(5000)
    }

    private fun addPaint() {
        val p: Paint = PaintBuilder.newBuilder()
            .backgroundColor(Color.argb(255, 117, 124, 168))
            // .addString("Mob Alive:") { isItAlive.toString() }
            .build()
        addPaint(p)
    }

    private fun attackMob() {
        if(!isItAlive) {
            mob = Npcs.stream().nearest().name("Barbarian").first()
            isItAlive = true
        }

        if (mob.healthPercent() >= 1 && !mob.inCombat() && !mob.healthBarVisible()) {
            if (mob.inViewport()) {
                mob.interact("Attack")
            } else {
                Camera.angleToLocatable(mob)
            }

            if (Players.local().tile() != Tile(3081, 3421)) {
                Movement.moveTo(Tile(3081, 3421))
                Condition.wait(Callable { Players.local().tile() == Tile(3081, 3421) }, 220, 25)
            }

        } else if (mob.healthPercent() < 1){
            isItAlive = false

            var deathTile = mob.tile()

            Movement.walkTo(deathTile)
            Condition.wait(Callable { Players.local().tile() == deathTile }, 200, 25)

            GroundItems.stream().at(deathTile).forEach { it ->
                it.interact("Take")
                Condition.wait(Callable { !it.valid() }, 200, 25)
            }

            Movement.walkTo(Tile(3081, 3421))
            Condition.wait(Callable { Players.local().tile() == Tile(3081, 3421) }, 200, 25)
        }
    }
}

fun main(args: Array<String>) {
    ScriptUploader().uploadAndStart("FnTests", "", "127.0.0.1:5037", true, false)
}
