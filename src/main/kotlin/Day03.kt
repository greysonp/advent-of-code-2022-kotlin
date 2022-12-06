object Day03 {
  fun p1(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val midpoint = line.length /2

        val p1: Set<Char> = line.substring(0, midpoint).toCharArray().toSet()
        val p2: Set<Char> = line.substring(midpoint).toCharArray().toSet()

        score(p1.intersect(p2).first())
      }
      .sum()
  }

  fun p2(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .chunked(3)
      .map { lines ->
        val p1 = lines[0].toCharArray().toSet()
        val p2 = lines[1].toCharArray().toSet()
        val p3 = lines[2].toCharArray().toSet()

        score(p1.intersect(p2).intersect(p3).first())
      }
      .sum()
  }

  private fun score(letter: Char): Int {
    return if (letter.code >= 'a'.code) {
      letter.code - 'a'.code + 1
    } else {
      letter.code - 'A'.code + 26 + 1
    }
  }
}