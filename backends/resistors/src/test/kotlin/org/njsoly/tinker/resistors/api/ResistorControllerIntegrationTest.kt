package org.njsoly.tinker.resistors.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.njsoly.tinker.resistors.core.ResistorEvaluationService
import org.njsoly.tinker.resistors.domain.ResistanceDetail
import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor
import org.njsoly.tinker.resistors.domain.ResistorConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

@WebMvcTest(ResistorController::class)
class ResistorControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var resistorEvaluationService: ResistorEvaluationService

    private val objectMapper = ObjectMapper()

    @TestConfiguration
    class TestConfig {
        @Bean
        fun resistorEvaluationService(): ResistorEvaluationService = mockk()
    }

    @Test
    fun `GET ohm-symbol returns omega character`() {
        mockMvc.get("/ohm-symbol")
            .andExpect {
                status { isOk() }
                content { string(ResistorConstants.OHM_SYMBOLS[0] ) }
            }
    }

    @Test
    fun `GET colors returns all resistor colors as JSON array`() {
        mockMvc.get("/colors")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$") { isArray() }
                jsonPath("$.length()") { value(12) }
                jsonPath("$[0]") { exists() }
            }
    }

    @Test
    fun `POST evaluate with valid 3-band pattern returns resistance detail`() {
        val bandPattern = ResistorBandPattern(
            band0 = ResistorColor.Brown,
            band1 = ResistorColor.Black,
            band2 = ResistorColor.Red
        )
        val expectedDetail = ResistanceDetail(
            value = BigDecimal("1000"),
            engineeringNotation = "1 k",
            tolerance = null
        )

        every { resistorEvaluationService.evaluateResistance(bandPattern) } returns expectedDetail

        mockMvc.post("/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bandPattern)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.value") { value(1000) }
            jsonPath("$.engineeringNotation") { value("1 k") }
        }
    }

    @Test
    fun `POST evaluate with 4-band pattern including tolerance returns resistance detail with tolerance`() {
        val bandPattern = ResistorBandPattern(
            band0 = ResistorColor.Red,
            band1 = ResistorColor.Red,
            band2 = ResistorColor.Brown,
            band3 = ResistorColor.Gold
        )
        val expectedDetail = ResistanceDetail(
            value = BigDecimal("220"),
            engineeringNotation = "220",
            tolerance = 5.0
        )

        every { resistorEvaluationService.evaluateResistance(bandPattern) } returns expectedDetail

        mockMvc.post("/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bandPattern)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.value") { value(220) }
            jsonPath("$.engineeringNotation") { value("220") }
            jsonPath("$.tolerance") { value(5.0) }
        }
    }

    @Test
    fun `POST evaluate with 5-band pattern returns high precision resistance`() {
        val bandPattern = ResistorBandPattern(
            band0 = ResistorColor.Brown,
            band1 = ResistorColor.Black,
            band2 = ResistorColor.Black,
            band3 = ResistorColor.Orange,
            band4 = ResistorColor.Brown
        )
        val expectedDetail = ResistanceDetail(
            value = BigDecimal("100000"),
            engineeringNotation = "100 k",
            tolerance = 1.0
        )

        every { resistorEvaluationService.evaluateResistance(bandPattern) } returns expectedDetail

        mockMvc.post("/evaluate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bandPattern)
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.value") { value(100000) }
            jsonPath("$.engineeringNotation") { value("100 k") }
            jsonPath("$.tolerance") { value(1.0) }
        }
    }
}
