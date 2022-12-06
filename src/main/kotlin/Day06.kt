object Day06 {
  fun p1(input: String): Int {
    return findPacketIndex(input, 4)
  }

  fun p2(input: String): Int {
    return findPacketIndex(input, 14)
  }

  private fun findPacketIndex(input: String, markerLength: Int): Int {
    for (i in 0 until input.length - markerLength) {
      val chunk = input.substring(i, i + markerLength)
      if (chunk.toCharArray().toSet().size  == markerLength) {
        return i + markerLength
      }
    }

    error("Could not find a marker")
  }
}