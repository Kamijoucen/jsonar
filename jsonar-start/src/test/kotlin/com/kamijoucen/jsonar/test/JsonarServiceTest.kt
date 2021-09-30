package com.kamijoucen.jsonar.test

import com.kamijoucen.jsonar.service.IAnalyseService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JsonarServiceTest {

    @Autowired
    lateinit var analyseService: IAnalyseService

    @Test
    fun testParse() {
        analyseService.parser()
        println("11111")
    }
}