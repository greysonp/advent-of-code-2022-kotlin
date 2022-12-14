import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Tests {

  @Test
  fun `day01, part 1`() {
    assertEquals(24000, Day01.p1(readFile("01/sample.txt")))
    assertEquals(75622, Day01.p1(readFile("01/input.txt")))
  }

  @Test
  fun `day01, part 2`() {
    assertEquals(45000, Day01.p2(readFile("01/sample.txt")))
    assertEquals(213159, Day01.p2(readFile("01/input.txt")))
  }

  @Test
  fun `day02, part 1`() {
    assertEquals(15, Day02.p1(readFile("02/sample.txt")))
    assertEquals(13924, Day02.p1(readFile("02/input.txt")))
  }

  @Test
  fun `day02, part 2`() {
    assertEquals(12, Day02.p2(readFile("02/sample.txt")))
    assertEquals(13448, Day02.p2(readFile("02/input.txt")))
  }

  @Test
  fun `day03, part 1`() {
    assertEquals(157, Day03.p1(readFile("03/sample.txt")))
    assertEquals(7997, Day03.p1(readFile("03/input.txt")))
  }

  @Test
  fun `day03, part 2`() {
    assertEquals(70, Day03.p2(readFile("03/sample.txt")))
    assertEquals(2545, Day03.p2(readFile("03/input.txt")))
  }

  @Test
  fun `day04, part 1`() {
    assertEquals(2, Day04.p1(readFile("04/sample.txt")))
    assertEquals(305, Day04.p1(readFile("04/input.txt")))
  }

  @Test
  fun `day04, part 2`() {
    assertEquals(4, Day04.p2(readFile("04/sample.txt")))
    assertEquals(811, Day04.p2(readFile("04/input.txt")))
  }

  @Test
  fun `day05, part 1`() {
    assertEquals("CMZ", Day05.p1(readFile("05/sample.txt")))
    assertEquals("QGTHFZBHV", Day05.p1(readFile("05/input.txt")))
  }

  @Test
  fun `day05, part 2`() {
    assertEquals("MCD", Day05.p2(readFile("05/sample.txt")))
    assertEquals("MGDMPSZTM", Day05.p2(readFile("05/input.txt")))
  }

  @Test
  fun `day06, part 1`() {
    assertEquals(5, Day06.p1("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    assertEquals(6, Day06.p1("nppdvjthqldpwncqszvftbrmjlhg"))
    assertEquals(10, Day06.p1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    assertEquals(11, Day06.p1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    assertEquals(1896, Day06.p1(readFile("06/input.txt")))
  }

  @Test
  fun `day06, part 2`() {
    assertEquals(19, Day06.p2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    assertEquals(23, Day06.p2("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    assertEquals(23, Day06.p2("nppdvjthqldpwncqszvftbrmjlhg"))
    assertEquals(29, Day06.p2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    assertEquals(26, Day06.p2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    assertEquals(3452, Day06.p2(readFile("06/input.txt")))
  }

  @Test
  fun `day07, part 1`() {
    assertEquals(95437, Day07.p1(readFile("07/sample.txt")))
    assertEquals(1243729, Day07.p1(readFile("07/input.txt")))
  }

  @Test
  fun `day07, part 2`() {
    assertEquals(24933642, Day07.p2(readFile("07/sample.txt")))
    assertEquals(4443914, Day07.p2(readFile("07/input.txt")))
  }

  @Test
  fun `day08, part 1`() {
    assertEquals(21, Day08.p1(readFile("08/sample.txt")))
    assertEquals(1546, Day08.p1(readFile("08/input.txt")))
  }

  @Test
  fun `day08, part 2`() {
    assertEquals(8, Day08.p2(readFile("08/sample.txt")))
    assertEquals(519064, Day08.p2(readFile("08/input.txt")))
  }

  @Test
  fun `day09, part 1`() {
    assertEquals(13, Day09.p1(readFile("09/sample1.txt")))
    assertEquals(5874, Day09.p1(readFile("09/input.txt")))
  }

  @Test
  fun `day09, part 2`() {
    assertEquals(36, Day09.p2(readFile("09/sample2.txt")))
    assertEquals(2467, Day09.p2(readFile("09/input.txt")))
  }

  @Test
  fun `day10, part 1`() {
    assertEquals(13140, Day10.p1(readFile("10/sample.txt")))
    assertEquals(16020, Day10.p1(readFile("10/input.txt")))
  }

  @Test
  fun `day10, part 2`() {
    val sampleAnswer = """
      ##..##..##..##..##..##..##..##..##..##..
      ###...###...###...###...###...###...###.
      ####....####....####....####....####....
      #####.....#####.....#####.....#####.....
      ######......######......######......####
      #######.......#######.......#######.....
    """.trimIndent()

    val inputAnswer = """
      ####..##..####.#..#.####..##..#....###..
      #....#..#....#.#..#....#.#..#.#....#..#.
      ###..#......#..#..#...#..#..#.#....#..#.
      #....#.....#...#..#..#...####.#....###..
      #....#..#.#....#..#.#....#..#.#....#.#..
      ####..##..####..##..####.#..#.####.#..#.
    """.trimIndent()

    assertEquals(sampleAnswer, Day10.p2(readFile("10/sample.txt")))
    assertEquals(inputAnswer, Day10.p2(readFile("10/input.txt")))
  }

  @Test
  fun `day11, part 1`() {
    assertEquals(10605, Day11.p1(readFile("11/sample.txt")))
    assertEquals(58794, Day11.p1(readFile("11/input.txt")))
  }

  @Test
  fun `day11, part 2`() {
    assertEquals(2713310158, Day11.p2(readFile("11/sample.txt")))
    assertEquals(20151213744, Day11.p2(readFile("11/input.txt")))
  }

  private fun readFile(path: String): String {
    return javaClass.getResource(path)!!.readText()
  }
}
