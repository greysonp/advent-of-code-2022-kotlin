object Day04 {
  fun p1(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(",")
        val r1 = Range.parse(parts[0])
        val r2 = Range.parse(parts[1])
        if (r1.covers(r2) || r2.covers(r1)) {
          1
        } else {
          0
        }
      }
      .sum()
  }

  fun p2(input: String): Int {
    return input
      .lines()
      .filter { it.isNotEmpty() }
      .map { line ->
        val parts = line.split(",")
        val r1 = Range.parse(parts[0])
        val r2 = Range.parse(parts[1])
        if (r1.overlaps(r2) || r2.overlaps(r1)) {
          1
        } else {
          0
        }
      }
      .sum()
  }

  data class Range(val from: Int, val to: Int) {
    fun covers(other: Range): Boolean {
      return this.from <= other.from && this.to >= other.to
    }

    fun overlaps(other: Range): Boolean {
      return covers(other)
          || (this.from <= other.from && this.to >= other.from)
          || (this.from <= other.to && this.to >= other.to)
    }

    companion object {
      fun parse(input: String): Range {
        val parts = input.split("-")
        return Range(
          from = parts[0].toInt(),
          to = parts[1].toInt()
        )
      }
    }
  }
}