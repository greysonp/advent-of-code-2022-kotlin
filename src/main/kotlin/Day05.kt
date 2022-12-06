import java.util.Stack

object Day05 {
  fun p1(input: String): String {
    val inputParts = input.split("\n\n")
    val cargo = Cargo.parse(inputParts[0])
    val instructions = inputParts[1]
      .lines()
      .filter { it.isNotEmpty() }
      .map { Instruction.parse(it) }

    instructions.forEach { cargo.processIndividualMove(it) }

    return cargo.topOfStacks()
  }

  fun p2(input: String): String {
    val inputParts = input.split("\n\n")
    val cargo = Cargo.parse(inputParts[0])
    val instructions = inputParts[1]
      .lines()
      .filter { it.isNotEmpty() }
      .map { Instruction.parse(it) }

    instructions.forEach { cargo.processGroupMove(it) }

    return cargo.topOfStacks()
  }

  data class Instruction(val count: Int, val from: Int, val to: Int) {
    companion object {
      private val regex = Regex("^move (\\d+) from (\\d+) to (\\d+).*$")

      fun parse(input: String): Instruction {
        val parts = regex.find(input)!!.groupValues.drop(1)
        return Instruction(
          count = parts[0].toInt(),
          from = parts[1].toInt() - 1,
          to = parts[2].toInt() - 1,
        )
      }
    }
  }

  class Cargo private constructor(private val stacks: MutableList<Stack<Char>>) {
    fun processIndividualMove(instruction: Instruction) {
      for (i in 0 until instruction.count) {
        val popped = stacks[instruction.from].pop()
        stacks[instruction.to].push(popped)
      }
    }

    fun processGroupMove(instruction: Instruction) {
      val fromStack = stacks[instruction.from]
      val popped = fromStack.subList(fromStack.size - instruction.count, fromStack.size)
      stacks[instruction.from] = fromStack.dropLast(instruction.count).toCollection(Stack())
      stacks[instruction.to] += popped
    }

    fun topOfStacks(): String {
      return stacks
        .filter { it.isNotEmpty() }
        .map { it.peek() }
        .joinToString("")
    }

    companion object {
      fun parse(input: String): Cargo {
        val lines = input
          .split("\n")
          .filter { it.isNotEmpty() && !it.startsWith(" 1") }

        val stacks = mutableListOf<Stack<Char>>()
        val stackCount = lines.map { (it.length + 1) / 4 }.max()

        for (i in 1..stackCount) {
          stacks += Stack<Char>()
        }

        for (line in lines) {
          line.chunked(4).forEachIndexed { i, part ->
            if (part.startsWith("[")) {
              stacks[i].add(0, part[1])
            }
          }
        }

        return Cargo(stacks)
      }
    }
  }
}