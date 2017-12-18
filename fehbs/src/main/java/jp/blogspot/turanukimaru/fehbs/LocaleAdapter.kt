package jp.blogspot.turanukimaru.fehbs

import jp.blogspot.turanukimaru.fehs.Locale

/**
 * Javaのロケールをモデルのものに切り替えるためのアダプタ。Multiplatformで必要になる予定
 */
class LocaleAdapter(private val javaLocale: java.util.Locale) {
    fun locale() = when (javaLocale) {
        java.util.Locale.JAPAN -> Locale.JAPAN
        java.util.Locale.JAPANESE -> Locale.JAPANESE
        else -> Locale.OTHER
    }
}