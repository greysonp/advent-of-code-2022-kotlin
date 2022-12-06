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

  private fun readFile(path: String): String {
    return javaClass.getResource(path)!!.readText()
  }
}