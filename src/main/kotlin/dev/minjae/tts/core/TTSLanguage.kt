package dev.minjae.tts.core

enum class TTSLanguage(val googleCode: String, val clovaCode: String) {
    ENGLISH("en-us", "en"),
    KOREAN("ko-kr", "ko"),
    JAPANESE("ja-jp", "ja"),
    CHINESE("zh-cn", "zh"),
    SPANISH("es-es", "es")
}
