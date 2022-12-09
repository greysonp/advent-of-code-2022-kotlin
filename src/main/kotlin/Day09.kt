object Day09 {
  fun p1(input: String): Int {
    val tail = Knot(null, 0, 0)
    val head = Knot(tail, 0, 0)
    val visited = mutableSetOf<Point>()

    input
      .lines()
      .filter { it.isNotEmpty() }
      .forEach { line ->
        val parts = line.split(" ")
        val direction = parts[0]
        val count = parts[1].toInt()

        head.move(direction, count, visited)
      }

    return visited.size
  }

  fun p2(input: String): Int {
    var head = Knot(null, 0, 0)
    for (i in 2..10) {
      head = Knot(head, 0, 0)
    }

    val visited = mutableSetOf<Point>()

    input
      .lines()
      .filter { it.isNotEmpty() }
      .forEach { line ->
        val parts = line.split(" ")
        val direction = parts[0]
        val count = parts[1].toInt()

        head.move(direction, count, visited)
      }

    return visited.size
  }

  data class Knot(val child: Knot?, var x: Int, var y: Int) {
    fun move(direction: String, count: Int, visited: MutableSet<Point>) {
      for (i in 1..count) {
        when (direction) {
          "U" -> y -= 1
          "D" -> y += 1
          "L" -> x -= 1
          "R" -> x += 1
          else -> error("Unexpected direction: $direction")
        }

        child?.follow(this)

        val last = lastChild
        visited += Point(last.x, last.y)
      }
    }

    private fun follow(parent: Knot) {
      if (parent.x - this.x > 1) {
        this.x += 1

        if (parent.y > this.y) {
          this.y += 1
        } else if (parent.y < this.y) {
          this.y -= 1
        }
      } else if (parent.x - this.x < -1) {
        this.x -= 1

        if (parent.y > this.y) {
          this.y += 1
        } else if (parent.y < this.y) {
          this.y -= 1
        }
      } else if (parent.y - this.y > 1) {
        this.y += 1

        if (parent.x > this.x) {
          this.x += 1
        } else if (parent.x < this.x) {
          this.x -= 1
        }
      } else if (parent.y - this.y < -1) {
        this.y -= 1

        if (parent.x > this.x) {
          this.x += 1
        } else if (parent.x < this.x) {
          this.x -= 1
        }
      }

      child?.follow(this)
    }

    private val lastChild: Knot
      get() = child?.lastChild ?: this
  }

  data class Point(val x: Int, val y: Int)
}
