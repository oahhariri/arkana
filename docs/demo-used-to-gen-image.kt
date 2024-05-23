// DO NOT MODIFY
// Automatically generated by Arkana (https://github.com/rogerluan/arkana)
package com.arkanakeys

object MySecrets {
    private val salt = listOf(0x3f, 0xb6, 0x2, 0x3, 0xbf, 0x54, 0xf5, 0x67, 0x95, 0xc, 0x56, 0x47, 0x87, 0x55, 0x60, 0x74, 0x6, 0x77, 0x8b, 0xd6, 0x88, 0x41, 0x99, 0xe2, 0x97, 0x92, 0x9f, 0x68, 0x7d, 0x6c, 0x39, 0x64, 0xca, 0x98, 0xe7, 0x8d, 0xe8, 0x9e, 0x1f, 0xe5, 0xad, 0x45, 0x32, 0xac, 0xc5, 0xe1, 0xf6, 0x4f, 0x67, 0xcc, 0x6a, 0xee, 0x66, 0xac, 0x80, 0xea, 0x78, 0x1b, 0xd6, 0x78, 0x4, 0x97, 0xfa, 0xcc)

    internal fun decode(encoded: List<Int>, cipher: List<Int>): String {
        val decoded = encoded.mapIndexed { index, item ->
            (item xor cipher[(index % cipher.size)]).toByte()
        }.toByteArray()
        return decoded.decodeToString()
    }

    internal fun decodeInt(encoded: List<Int>, cipher: List<Int>): Int {
        return decode(encoded = encoded, cipher = cipher).toInt()
    }

    internal fun decodeBoolean(encoded: List<Int>, cipher: List<Int>): Boolean {
        return decode(encoded = encoded, cipher = cipher).toBoolean()
    }

    object Global {
        val someBooleanSecret: Boolean
            get() {
                val encoded = listOf(0x4b, 0xc4, 0x77, 0x66)
                return decodeBoolean(encoded = encoded, cipher = salt)
            }

        val someIntSecret: Int
            get() {
                val encoded = listOf(0xb, 0x84)
                return decodeInt(encoded = encoded, cipher = salt)
            }

        val mySecretAPIKey: String
            get() {
                val encoded = listOf(0x6, 0x84, 0x30, 0x30, 0x8c, 0x63, 0xc7, 0x57, 0xa6, 0x3a, 0x6e, 0x72, 0xb3, 0x62, 0x57, 0x41, 0x3e, 0x47, 0xbc, 0xef, 0xba, 0x73, 0xaa, 0xd1, 0xa0, 0xa0, 0xaf, 0x5b, 0x4b, 0x54, 0xc, 0x50, 0xfd, 0xaf, 0xd2, 0xb5, 0xd8, 0xa9)
                return decode(encoded = encoded, cipher = salt)
            }
    }

    object Dev : MySecretsEnvironment {
        override val serviceKey: String
            get() {
                val encoded = listOf(0x4b, 0xde, 0x6b, 0x70, 0x9f, 0x30, 0x90, 0x11, 0xb5, 0x67, 0x33, 0x3e, 0xa7, 0x3c, 0x13, 0x54, 0x75, 0x12, 0xe8, 0xa4, 0xed, 0x35)
                return decode(encoded = encoded, cipher = salt)
            }
    }

    object Staging : MySecretsEnvironment {
        override val serviceKey: String
            get() {
                val encoded = listOf(0x4b, 0xde, 0x6b, 0x70, 0x9f, 0x27, 0x81, 0x6, 0xf2, 0x65, 0x38, 0x20, 0xa7, 0x3e, 0x5, 0xd, 0x26, 0x1e, 0xf8, 0xf6, 0xfb, 0x24, 0xfa, 0x90, 0xf2, 0xe6)
                return decode(encoded = encoded, cipher = salt)
            }
    }
}
