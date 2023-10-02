import kotlin.random.Random

fun main() {

}
open class Creature(
    var attack: Int,
    var defense: Int,
    var maxHealth: Int,
    var health: Int = maxHealth,
    var damageRange: IntRange
) {

    fun isAlive() = health > 0

    fun takeDamage(damage: Int) {
        if (damage < 0) {
            throw IllegalArgumentException("Урон не может быть отрицательным")
        }

        health -= damage
        if (health < 0) {
            health = 0
        }
    }

    fun attackCreature(creature: Creature) {
        val attackModifier = attack - creature.defense + 1
        val diceRolls = if (attackModifier < 1) 1 else attackModifier

        var success = false
        for (i in 1..diceRolls) {
            if (Random.nextInt(1, 6) >= 5) {
                success = true
                break
            }
        }

        if (success) {
            val damage = Random.nextInt(damageRange.first, damageRange.last)
            creature.takeDamage(damage)
        }
    }

}

class Player(
    attack: Int,
    defense: Int,
    maxHealth: Int,
    damageRange: IntRange
) : Creature(attack, defense, maxHealth, maxHealth, damageRange) {

    var healsLeft = 4

    fun heal() {
        if (healsLeft > 0) {
            val healAmount = (maxHealth * 0.3).toInt()
            health += healAmount
            healsLeft--
        }
    }

}

class Monster(
    attack: Int,
    defense: Int,
    maxHealth: Int,
    damageRange: IntRange
) : Creature(attack, defense, maxHealth, maxHealth, damageRange)