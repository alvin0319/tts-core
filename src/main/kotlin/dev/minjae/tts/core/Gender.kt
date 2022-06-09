package dev.minjae.tts.core

enum class Gender(val speakers: Map<TTSLanguage, String>) {
    WOMAN(
        mapOf(
            TTSLanguage.KOREAN to "kyuri",
            TTSLanguage.ENGLISH to "clara",
            TTSLanguage.JAPANESE to "yuri",
            TTSLanguage.CHINESE to "meimei",
            TTSLanguage.SPANISH to "carmen"
        )
    ),
    MAN(emptyMap())
}
