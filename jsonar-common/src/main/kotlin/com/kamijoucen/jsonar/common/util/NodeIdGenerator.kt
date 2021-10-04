package com.kamijoucen.jsonar.common.util

import com.kamijoucen.kirauid.impl.DefaultIdGenerator

object NodeIdGenerator {

    private val generator = DefaultIdGenerator.createDefaultGenerator()

    fun generator(type: Long) = generator.nextId(type)

}