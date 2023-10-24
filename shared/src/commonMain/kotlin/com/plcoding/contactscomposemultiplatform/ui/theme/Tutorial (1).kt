package com.plcoding.contactscomposemultiplatform.ui.theme

/**
 * Er zijn 3 dingen erg belangrijk bij het programmeren. Zorg dat je het verschil weet!
 */

/*
 Standaard type objecten
 val = value (niet aanpasbaar)
 var = variable (aanpasbaar)
 */
val string: String = "Dit is een string"
val int: Int = 100
var boolean: Boolean = true
var double = 100.0
val list: List<Int> = listOf(0, 1, 2, 3)
val map: Map<String, Int> = mapOf("test1" to 1, "test2" to 2, "test3" to 3)

/**
 * Een list is immutable. Dit betekent dat je geen values kunt wijzigen.
 * Een arraylist is mutable. Dit betekent dat je wel values kunt wijzigen.
 *
 * Een map is immutable. Dit betekent dat je geen values kunt wijzigen.
 * Een hashmap is mutable. Dit betekent dat je wel values kunt wijzigen.
 *
 * Door een list of map kun je loopen. Dit betekent dat je door alle waardes heen gaat.
 * Dit doe je door .forEach() { } te typen.
 */

// Een functie
fun runFunction1() {

}

// Een functie met een parameter
fun runFunction2(word: String, number: Int) {

}

// Een functie die een variable teruggeeft
fun runFunction3(): Boolean {
    return true
}

/**
 * Je heb allemaal verschillende type classes. Het is handig om te weten wat je met welk type class kunt.
 */

// Een normale class kun je zo vaak als je wil aanmaken (een nieuwe instance)
class NormalClass() {
    val word: String = "word"

    fun testFunction() {

    }
}

// Een data class is een class die alleen uit var en val bestaat. Bij het aanmaken van deze class geef je de waardes mee.
data class DataClass(
    val value1: Int,
    val value2: Int,
    val value3: Int
)

// Met een enum class kun je values makkelijk hardcoden.
enum class EnumClass(amount: Int) {
    value1(1),
    value2(2),
    value3(3)
}

// Een object is een class die je door de hele code heen kan blijven aanroepen zonder een nieuwe instance te maken.
object ObjectClass {
    val word: String = "word"

    fun testFunction() {

    }
}

// Een interface is ervoor bedoeld om aan te geven welke values of functions er minimaal moeten zijn.
interface InterfaceClass {
    val value1: String
    val value2: Int
    fun required()
    fun required2(): Boolean
}

/**
 * Overal waar je een nieuwe class kunt aanmaken, kun je ook een constructor maken.
 * Een constructor kan zijn een val of var die je meegeeft in de ()
 * of een stukje code binnen de init {}
 */

class ConstructorClass(val fruit: String, base: Int) {
    val baseInt: Int

    init {
        baseInt = base * 2
    }
}

/**
 * Nu even alles door elkaar
 */

// Ik heb een aantal kleuren met een bijbehorende kleurcode. Dit staat vast.
enum class Kleuren(val kleurcode: Int) {
    ROOD(1),
    BLAUW(2),
    GEEL(3),
    GROEN(4)
}

// Ik ga een aantal kleur classes hebben. Ik wil dat elke kleur class minimaal een code en print functie heeft.
interface Kleur {
    val code: String
    fun print()
}

// De RGB class kun je aanmaken door een Kleuren mee te geven bij het aanmaken. Verder is RGB een Kleur, dus hij moet een code en een print functie hebben.
class RGB(kleur: Kleuren): Kleur {
    override val code: String
    // Als ik de functie print() aanroep, print hij de code in de console.
    override fun print() {
        println(code)
    }

    init {
        // Bij het initializen van de class verander ik de code naar een string. De when statement betekend eigenlijk:  Als kleur == <een kleur>, doe dan het volgende.
        code = when(kleur) {
            Kleuren.BLAUW -> "RGB (${kleur.kleurcode}): 0, 0, 255"
            Kleuren.GROEN -> "RGB (${kleur.kleurcode}): 51, 204, 51"
            Kleuren.ROOD -> "RGB (${kleur.kleurcode}): 255, 0, 0"
            Kleuren.GEEL -> "RGB (${kleur.kleurcode}): 255, 255, 0"
        }
    }
}

// De HEX class kun je ook aanmaken door een Kleuren mee te geven bij het aanmaken. Verder is HEX een Kleur, dus hij moet een code en een print functie hebben.
class HEX(kleur: Kleuren): Kleur {
    override val code: String
    // Als ik de functie print() aanroep, print hij de code in de console.
    override fun print() {
        println(code)
    }

    init {
        code = when(kleur) {
            Kleuren.BLAUW -> "HEX (${kleur.kleurcode}): #0000ff"
            Kleuren.GROEN -> "HEX (${kleur.kleurcode}): #33cc33"
            Kleuren.ROOD -> "HEX (${kleur.kleurcode}): #ff0000"
            Kleuren.GEEL -> "HEX (${kleur.kleurcode}): #ffff00"
        }
    }
}

// De KleurManager hoef je niet aan te maken. Deze bestaat altijd al.
object KleurManager {

    // Ik heb een lijstje met allemaal classes die de interface Kleur extenden. Dit betekent dat hier RGB en HEX in kan zitten.
    val kleuren: ArrayList<Kleur> = ArrayList()

    // Als ik de load functie aanroep, vul ik het lijstje met kleuren.
    fun load() {
        // IK maak ter plekke een HEX class aan, en geef Kleuren mee.
        kleuren.add(HEX(Kleuren.GEEL))
        // IK maak ter plekke een RGB class aan, en geef Kleuren mee.
        kleuren.add(RGB(Kleuren.ROOD))
        kleuren.add(RGB(Kleuren.GEEL))
        kleuren.add(HEX(Kleuren.BLAUW))
        kleuren.add(HEX(Kleuren.BLAUW))
    }

    // Als ik de printToConsole functie aanroep, loop ik door alle kleuren (dat lijstje) heen, en voor elke Kleur voer ik de functie print() uit.
    fun printToConsole() {
        kleuren.forEach {
            it.print()
        }
    }
}

fun main(args: Array<String>) {
    // Eerst laad ik alle kleuren in
    KleurManager.load()
    // Dan print voer ik de printToConsole functie uit.
    KleurManager.printToConsole()
}