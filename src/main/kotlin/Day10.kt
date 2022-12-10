import java.lang.StringBuilder

object Day10 {
  fun p1(input: String): Int {
    val instructions = input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(" ")
        when (parts[0]) {
          "addx" -> Instruction.Add(parts[1].toInt())
          "noop" -> Instruction.Noop
          else -> error("Unexpected instruction: $line")
        }
      }
      .toMutableList()

    val memory = Memory(x = 1)
    var sum = 0

    for (pc in 1..220) {
      val done = instructions[0].compute()

      if ((pc - 20) % 40 == 0) {
        sum += (memory.x * pc)
      }

      if (done) {
        instructions[0].apply(memory)
        instructions.removeAt(0)
      }
    }

    return sum
  }

  fun p2(input: String): String {
    val instructions = input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(" ")
        when (parts[0]) {
          "addx" -> Instruction.Add(parts[1].toInt())
          "noop" -> Instruction.Noop
          else -> error("Unexpected instruction: $line")
        }
      }
      .toMutableList()

    val memory = Memory(x = 1)
    var pc = 1
    val display = StringBuilder()

    while (instructions.isNotEmpty()) {
      val done = instructions[0].compute()
      val crtX = (pc - 1) % 40

      if (memory.x == crtX || memory.x == crtX - 1 || memory.x == crtX + 1) {
        display.append('#')
      } else {
        display.append('.')
      }

      if (pc % 40 == 0) {
        display.append('\n')
      }

      if (done) {
        instructions[0].apply(memory)
        instructions.removeAt(0)
      }

      pc++
    }

    return display.toString().trim()
  }

  sealed class Instruction(private var cycles: Int) {

    fun compute(): Boolean {
      cycles--
      return cycles <= 0
    }

    open fun apply(memory: Memory) {}

    data class Add(val value: Int) : Instruction(2) {
      override fun apply(memory: Memory) {
        memory.x += value
      }
    }

    object Noop : Instruction(1)
  }

  data class Memory(var x: Int)
}
