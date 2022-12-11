

object Day11 {
  fun p1(input: String): Long {
    val maxValue = parseDivisors(input).reduce { acc, i -> acc * i }
    val monkeys: List<Monkey> = input
      .split("\n\n")
      .filter { it.isNotEmpty() }
      .map { Monkey.parse(it, maxValue) }

    for (i in 1..20) {
      for (monkey in monkeys) {
        monkey.takeTurn(monkeys, maxValue, 3)
      }
    }

    val topTwo = monkeys
      .sortedBy { it.inspectionCount }
      .reversed()
      .take(2)

    return topTwo[0].inspectionCount * topTwo[1].inspectionCount
  }

  fun p2(input: String): Long {
    val maxValue = parseDivisors(input).reduce { acc, i -> acc * i }
    val monkeys: List<Monkey> = input
      .split("\n\n")
      .filter { it.isNotEmpty() }
      .map { Monkey.parse(it, maxValue) }

    for (i in 1..10_000) {
      for (monkey in monkeys) {
        monkey.takeTurn(monkeys, maxValue, 1)
      }
    }

    val topTwo = monkeys
      .sortedBy { it.inspectionCount }
      .reversed()
      .take(2)

    return topTwo[0].inspectionCount * topTwo[1].inspectionCount
  }

  fun parseDivisors(input: String): Set<Long> {
    return input
      .lines()
      .filterIndexed { i, line -> i == 3 || (i - 3) % 7 == 0 }
      .map { line -> line.split("divisible by")[1].trim().toLong() }
      .toSet()
  }

  class Monkey private constructor(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val divisor: Int,
    val truePass: Int,
    val falsePass: Int,
    var inspectionCount: Long = 0
  ) {

    fun takeTurn(monkeys: List<Monkey>, maxValue: Long, worryDivisor: Int) {
      inspectionCount += items.size

      for (item in items) {
        val newItem = (operation(item) % maxValue) / worryDivisor
        if (newItem % divisor == 0L) {
          monkeys[truePass].items += newItem
        } else {
          monkeys[falsePass].items += newItem
        }
      }

      items.clear()
    }

    companion object {

      fun parse(input: String, maxValue: Long): Monkey {
        val items: MutableList<Long> = mutableListOf()
        var operation: (Long) -> Long = { 0L }
        var divisor: Int = -1
        var truePass = -1
        var falsePass = -1

        input.lines().forEachIndexed { i, line ->
          when (i) {
            1 -> {
              items += line.split(":")[1].split(",").map { it.trim().toLong() % maxValue }
            }
            2 -> {
              val opParts = line.split("new = ")[1].split(" ").map { it.trim() }
              operation = { old ->
                val rhs = if (opParts[2] == "old") old else opParts[2].toLong()

                if (opParts[1] == "+") {
                  old + rhs
                } else {
                  old * rhs
                }
              }
            }
            3 -> {
              divisor = line.split("divisible by ")[1].trim().toInt()
            }
            4 -> {
              truePass = line.split(" monkey ")[1].trim().toInt()
            }
            5 -> {
              falsePass = line.split(" monkey ")[1].trim().toInt()
            }
          }
        }
        return Monkey(
          items = items,
          operation = operation,
          divisor = divisor,
          truePass = truePass,
          falsePass = falsePass
        )
      }
    }
  }
}
