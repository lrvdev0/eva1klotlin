package com.example.rest

data class Platillo(val nombre: String, val precio: Int)

data class Pedido(val platillo: Platillo, var cantidad: Int = 0) {
    fun subtotal(): Int {
        return platillo.precio * cantidad
    }
}