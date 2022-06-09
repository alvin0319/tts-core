package dev.minjae.tts.core

import kotlin.test.Test
import kotlin.test.assertTrue

class TextToSpeechDataTest {
    @Test
    fun assertTTS() {
        val clova = TTSEngine(TTSType.CLOVA)

        val google = TTSEngine(TTSType.GOOGLE)

        assertTrue(
            clova.getSpeech("Hello world!", Gender.WOMAN, TTSLanguage.ENGLISH).isNotEmpty(),
            "Received invalid bytes (clova)"
        )
        assertTrue(
            google.getSpeech("Hello world!", Gender.MAN, TTSLanguage.ENGLISH).isNotEmpty(),
            "Received invalid bytes (google)"
        )
//        Paths.get("").toAbsolutePath().resolve("test_results").toFile().apply {
//            if (!exists()) {
//                mkdirs()
//            }
//
//            val data = ByteArrayOutputStream().use { baos ->
//                Channels.newChannel(baos).use {
//                    val tag = ID3v24Tag()
//                    tag.setField(FieldKey.TITLE, "TTS")
//                    tag.write(it)
//                    baos.toByteArray()
//                    it.write(ByteBuffer.wrap(google.getSpeech("Hello world!", Gender.MAN, TTSLanguage.ENGLISH)))
//                    baos.toByteArray()
//                }
//            }
//            resolve("test.mp3").apply {
//                if (!exists()) {
//                    createNewFile()
//                }
//                writeBytes(data)
//            }
//        }
    }
}
