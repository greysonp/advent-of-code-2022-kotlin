object Day02 {
  fun p1(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(" ")
        val opponent = parts[0][0].code - 'A'.code
        val me = parts[1][0].code - 'X'.code
        score(opponent, me)
      }
      .sum()
  }

  fun p2(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(" ")
        val opponent = parts[0][0].code - 'A'.code

        when (parts[1][0]) {
          'X' -> score(opponent, (opponent + 2) % 3)
          'Y' -> score(opponent, opponent)
          'Z' -> score(opponent, (opponent + 1) % 3)
          else -> error("")
        }
      }
      .sum()
  }

  private fun score(opponent: Int, me: Int): Int {
    val score = when {
      opponent == me -> 3
      (opponent + 1) % 3 == me -> 6
      else -> 0
    }

    return score + me + 1
  }
}
