package com.kamijoucen.jsonar.common.util

import com.kamijoucen.kirauid.impl.DefaultIdGenerator

object NodeIdGenerator {

    private val generator = DefaultIdGenerator.createDefaultGenerator()

    fun nextId(type: Long) = generator.nextId(type)

    fun parser(id: Long) = generator.parseId(id)!!
}