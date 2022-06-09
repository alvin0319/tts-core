package dev.minjae.tts.core

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class TTSType(private val endpoint: String) {
    CLOVA("https://dict.naver.com/api/nvoice"),
    GOOGLE("https://translate.google.com/translate_tts");

    fun getSpeech(client: OkHttpClient, text: String, gender: Gender, language: TTSLanguage): ByteArray =
        client.newCall(
            when (this) {
                CLOVA -> {
                    if (gender == Gender.MAN) {
                        throw IllegalArgumentException("Cannot get TTS of man on clova")
                    }
                    Request.Builder()
                        .header("Referer", "https://dict.naver.com")
                        .url(endpoint)
                        .post(
                            FormBody.Builder()
                                .add("service", "dictionary")
                                .add("speech_fmt", "mp3")
                                .add("text", text)
                                .add("speaker", gender.speakers[language]!!)
                                .add("speed", "0")
                                .build()
                        )
                        .build()
                }
                GOOGLE -> {
                    val encodedString = URLEncoder.encode(text, StandardCharsets.UTF_8)
                    Request.Builder()
                        .header(
                            "User-Agent",
                            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"
                        )
                        .url(
                            endpoint + "?ie=UTF-8&client=tw-ob&q=$encodedString&tl=${language.googleCode}"

                        )
                        .get()
                        .build()
                }
            }
        ).execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("Request failed with status code ${response.code}")
            }
            val bytes = response.body.bytes()
            return if (bytes.isNotEmpty()) bytes else throw IllegalStateException("No data bytes received from the server")
        }
}
