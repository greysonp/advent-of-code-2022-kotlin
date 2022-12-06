object Day01 {
  fun p1(input: String): Int {
    return input
      .split("\n\n")
      .filter { it.isNotEmpty() }
      .map { chunk ->
        chunk
          .lines()
          .filter { it.isNotEmpty() }
          .sumOf { Integer.parseInt(it) }
      }
      .max()
  }

  fun p2(input: String): Int {
    return input
      .split("\n\n")
      .filter { it.isNotEmpty() }
      .map { chunk ->
        chunk
          .lines()
          .filter { it.isNotEmpty() }
          .sumOf { Integer.parseInt(it) }
      }
      .sortedDescending()
      .take(3)
      .sum()
  }
}
