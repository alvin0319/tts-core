package dev.minjae.tts.core

import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class TTSEngine @JvmOverloads constructor(
    private val type: TTSType,
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .fastFallback(true)
        .cache(
            Cache(File("cache"), 50 * 1024 * 1024)
        )
        .build()
) {

    fun getSpeech(text: String, gender: Gender, language: TTSLanguage): ByteArray =
        type.getSpeech(okHttpClient, text, gender, language)
}
