package tech.pixela.jetfm.utils.general

import java.io.UnsupportedEncodingException
import java.security.MessageDigest

class LastfmSignature {
    companion object {
        fun signature(key: String, password: String, secret: String, username: String) =
            md5("api_key${key}methodauth.getMobileSessionpassword${password}username${username}$secret")

        private fun md5(text: String): String {
            return try {
                val bytes: ByteArray = MessageDigest.getInstance("MD5").digest(text.toByteArray())
                val builder = StringBuilder(32)
                bytes.forEach {
                    val hex = Integer.toHexString(it.toInt() and 0xFF)
                    if (hex.length == 1) builder.append('0')
                    builder.append(hex)
                }
                builder.toString()
            } catch (e: UnsupportedEncodingException) {
                ""
            }
        }
    }
}
