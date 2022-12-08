object Day08 {
  fun p1(input: String): Int {
    val grid: Grid = Grid.fromString(input)
    val visible: MutableSet<Point> = mutableSetOf()

    for (x in grid.xAxis) {
      // top to bottom
      var largestSeen = -1
      for (y in grid.yAxis) {
        if (grid.get(x, y) > largestSeen) {
          visible += Point(x, y)
          largestSeen = grid.get(x, y)
        }
      }

      // bottom to top
      largestSeen = -1
      for (y in grid.yAxisReversed) {
        if (grid.get(x, y) > largestSeen) {
          visible += Point(x, y)
          largestSeen = grid.get(x, y)
        }
      }
    }

    for (y in grid.yAxis) {
      // left to right
      var largestSeen = -1
      for (x in grid.xAxis) {
        if (grid.get(x, y) > largestSeen) {
          visible += Point(x, y)
          largestSeen = grid.get(x, y)
        }
      }

      // right to left
      largestSeen = -1
      for (x in grid.xAxisReversed) {
        if (grid.get(x, y) > largestSeen) {
          visible += Point(x, y)
          largestSeen = grid.get(x, y)
        }
      }
    }

    return visible.size
  }

  fun p2(input: String): Int {
    val grid: Grid = Grid.fromString(input)

    var maxScore = 0
    for (x in grid.xAxis) {
      for (y in grid.yAxis) {
        maxScore = Math.max(maxScore, scenicScore(grid, Point(x, y)))
      }
    }

    return maxScore
  }

  private fun scenicScore(grid: Grid, p: Point): Int {
    val treeHeight = grid.get(p.x, p.y)

    var downScore = 0
    for (y in p.y + 1 until grid.height) {
      downScore++
      if (grid.get(p.x, y) >= treeHeight) {
        break
      }
    }

    var upScore = 0
    for (y in p.y - 1 downTo 0) {
      upScore++
      if (grid.get(p.x, y) >= treeHeight) {
        break
      }
    }

    var rightScore = 0
    for (x in p.x + 1 until grid.width) {
      rightScore++
      if (grid.get(x, p.y) >= treeHeight) {
        break
      }
    }

    var leftScore = 0
    for (x in p.x - 1 downTo 0) {
      leftScore++
      if (grid.get(x, p.y) >= treeHeight) {
        break
      }
    }

    return downScore * upScore * leftScore * rightScore
  }

  private class Grid private constructor(private val grid: List<List<Int>>) {

    val width = grid[0].size
    val height = grid.size

    val xAxis: IntRange = 0 until width
    val xAxisReversed: IntProgression = width - 1 downTo 0

    val yAxis: IntRange = 0 until height
    val yAxisReversed: IntProgression = height - 1 downTo 0

    fun get(x: Int, y: Int): Int {
      return grid[y][x]
    }

    companion object {
      fun fromString(string: String): Grid {
        val grid = string
          .lines()
          .filter { it.isNotEmpty() }
          .map { line ->
            line.toCharArray().map { it.digitToInt() }
          }

        return Grid(grid)
      }
    }
  }

  data class Point(val x: Int, val y: Int)
}
