package org.fn.lavaDragonSlayer


import org.fn.lavaDragonSlayer.branch.InventorySpace
import org.fn.lavaDragonSlayer.branch.LoggedIn
import org.fn.lavaDragonSlayer.modules.HealthChecker
import org.fn.lavaDragonSlayer.modules.PlayerDetect
import org.powbot.api.Color
import org.powbot.api.Condition
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.OptionType
import org.powbot.api.script.ScriptCategory
import org.powbot.api.script.ScriptConfiguration
import org.powbot.api.script.ScriptManifest
import org.powbot.api.script.paint.Paint
import org.powbot.api.script.paint.PaintBuilder
import org.powbot.api.script.tree.TreeComponent
import org.powbot.api.script.tree.TreeScript
import org.powbot.mobile.service.ScriptUploader
import java.util.logging.Logger

@ScriptManifest(
    name="FnLavaDragon",
    description="Lava Dragon Farming.\n" +
            "Suggested: 15+ agility\n" +
            "Required: Member rank.\n" +
            "Required: 13+ magic, 18+ health, air+mind or chaos runes in bank 2000+, 200+ food in bank, fire staff.",
    version="0.8.0",
    category=ScriptCategory.MoneyMaking,
    author="Fn"
)

@ScriptConfiguration.List(
    [
        ScriptConfiguration(
            name="Food",
            description="Food to eat",
            OptionType.STRING,
            defaultValue="Bass"
        ),
        ScriptConfiguration(
            name="Spell",
            description="Spell you wish to cast. Fire staff required!",
            OptionType.STRING,
            allowedValues = arrayOf(
                "FIRE_STRIKE",
                "FIRE_BOLT",
            ),
        ),
    ]
)

class Script : TreeScript() {
    private val logger = Logger.getLogger(this.javaClass.name)

    private var playerInArea: String = "false"
    private val playerDetector: PlayerDetect = PlayerDetect()
    private val healthChecker: HealthChecker = HealthChecker()

    private var low: Int = 0
    private var high: Int = 0

    override val rootComponent: TreeComponent<*> by lazy {
        LoggedIn(this)
    }

    lateinit var config: Config

    override fun onStart() {
        super.onStart()
        addPaint()
        getConfig()

        Constants.FOOD = config.food
        if (config.spell == "FIRE_STRIKE") {
            Constants.RUNES = Constants.FIRE_STRIKE_RUNES
        } else if (config.spell == "FIRE_BOLT") {
            Constants.RUNES = Constants.FIRE_BOLT_RUNES
        }

        Thread(playerDetector).name = "PlayerDetect"
        Thread(playerDetector).start()

        Thread(healthChecker).name = "HealthChecker"
        Thread(healthChecker).start()
    }

    override fun poll() {
        super.poll()

        playerInArea = (playerDetector.playerInArea).toString()
        low = playerDetector.wildernessLOW
        high = playerDetector.wildernessHIGH
        Condition.sleep(600)
    }


    fun getConfig(){
        val food = getOption<String>("Food")
        val spell = getOption<String>("Spell")

        config = Config(food, spell)
    }

    private fun addPaint() {
        val p: Paint = PaintBuilder.newBuilder()
            .addString("Step: ") { lastLeaf.name }
            .addString("Possible PK'er nearby:") { playerInArea.toString() }
            .trackSkill(Skill.Magic)
            .trackSkill(Skill.Hitpoints)
            .trackSkill(Skill.Defence)
            .backgroundColor(Color.argb(255, 117, 124, 168))
            .build()
        addPaint(p)
    }
}

fun main(args: Array<String>) {
    ScriptUploader().uploadAndStart("FnLavaDragon", "", "127.0.0.1:58079", true, false)
}