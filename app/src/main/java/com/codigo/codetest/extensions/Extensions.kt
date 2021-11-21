package com.codigo.codetest.extensions

fun Exception.errorMessage() : String = "${this.cause} : ${this.message}"
fun Throwable.errorMessage() : String = "${this.cause} : ${this.message}"