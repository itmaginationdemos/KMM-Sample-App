package com.example.voicenotes

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}