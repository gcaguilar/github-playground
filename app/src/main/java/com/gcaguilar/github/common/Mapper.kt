package com.gcaguilar.github.common

interface Mapper<Input, Output: Any> {
    fun map(input: Input): Output
    fun map(input: List<Input>): List<Output> = input.map { map(it) }.toImmutableList()
}