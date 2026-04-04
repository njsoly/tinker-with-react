package org.njsoly.tinker.resistors.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.njsoly.tinker.resistors.api.ResistorsApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ResistorsApplicationTests {

    @Test
    fun contextLoads() {
        val app = ResistorsApplication()
        assertNotNull(app)
    }

}
