package com.github.codandotv.craftd.compose

private fun unescapeBasicEntities(s: String): String =
    s.replace("&nbsp;", " ")
        .replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&#39;", "'")

actual fun parseHtmlToPlainText(html: String): String {
    val withBreaks = html
        .replace(Regex("(?i)<br\\s*/?>"), "\n")
        .replace(Regex("(?i)</p>"), "\n")

    val noTags = withBreaks.replace(Regex("<[^>]+>"), "") // remove tags

    // Colapsa múltiplas quebras/espaços e desescapa entidades básicas
    return unescapeBasicEntities(
        noTags
            .replace(Regex("[ \\t\\x0B\\f\\r]+"), " ")
            .replace(Regex("\\n{3,}"), "\n\n")
            .trim()
    )
}
