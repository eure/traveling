package com.eure.traveling.entity

enum class Type constructor(val id: Int) {
    ANIMATED(0),
    ATTACHMENTS(1),
    DEBUTS(2),
    PLAYOFFS(3),
    REBOUNDS(4),
    TEAMS(5);


    companion object {

        fun valueOf(id: Int): Type {
            values().filter { it.id == id }.first {
                return it
            }
            throw IllegalArgumentException("no such enum object for the id: " + id)
        }
    }

}
