package jp.blogspot.turanukimaru.fehbs

import jp.blogspot.turanukimaru.fehs.Locale

/**
 * Javaのロケールをモデルのものに切り替えるためのアダプタ。MultiPlatformで必要になる予定
 */
class LocaleAdapter(private val javaLocale: java.util.Locale) {
    val locale
        get() = when (javaLocale) {
            java.util.Locale.JAPAN -> Locale.JAPANESE
            java.util.Locale.JAPANESE -> Locale.JAPANESE
            java.util.Locale.CHINA -> Locale.TAIWAN
            java.util.Locale.CHINESE -> Locale.TAIWAN
            java.util.Locale.TAIWAN -> Locale.TAIWAN

            else -> Locale.OTHER
        }
}