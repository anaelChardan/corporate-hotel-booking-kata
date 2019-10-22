package unit

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class SampleTest : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
})
