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

  private fun readFile(path: String): String {
    return javaClass.getResource(path)!!.readText()
  }
}