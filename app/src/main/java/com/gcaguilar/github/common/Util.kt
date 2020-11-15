package com.gcaguilar.github.common

import java.util.Collections

fun <T> List<T>.toImmutableList(): List<T> {
    return Collections.unmodifiableList(toMutableList())
}