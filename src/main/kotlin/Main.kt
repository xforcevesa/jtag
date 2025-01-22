package org.jtag

import java.io.File
import java.util.regex.Pattern

// Token Regex
val tokenPatterns = mapOf(
    "COMMENT" to "(\\/\\*(?:\\*(?!\\/)|[^\\*])*\\*\\/|//.*[\n\r]*)",
    "KEYWORD" to "(int|float|if|else|while|return|void)",
    "IDENTIFIER" to "[a-zA-Z_][a-zA-Z0-9_]*",
    "NUMBER" to "[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)?",
    "OPERATOR" to "[+\\-*/=<>!&|]+",
    "DELIMITER" to "[{}();,]",
    "WHITESPACE" to "\\s+"
)

// Extractor
fun tokenExtractor(input: String): List<Pair<String, String>> {
    val tokens = mutableListOf<Pair<String, String>>()
    var remainingInput = input

    while (remainingInput.isNotEmpty()) {
        var matched = false

        for ((tokenType, pattern) in tokenPatterns) {
            val regex = Pattern.compile("^$pattern")
            val matcher = regex.matcher(remainingInput)

            if (matcher.find()) {
                val match = matcher.group()
                if (tokenType != "WHITESPACE" && tokenType != "COMMENT") { // 忽略空白和注释
                    tokens.add(Pair(tokenType, match))
                }
                remainingInput = remainingInput.substring(match.length)
                matched = true
                break
            }
        }

        if (!matched) {
            throw IllegalArgumentException("Analysis Error: ${remainingInput.take(10)}...")
        }
    }

    return tokens
}

// Test function
fun tokenExtractorTest() {
    val code = """
        int main() {
            float x = 3.14;
            int y = 10;
            if (x <= y) {
                return 1; // yes
            } /* return now /* k
            yes */
            return 0;
        }
    """.trimIndent()

    val tokens = tokenExtractor(code)
    for ((type, value) in tokens) {
        println("$type: $value")
    }
}

fun main() {
    tokenExtractorTest()
    File("HelloWorld.class").writeBytes(HelloWorldDump.dump())
}
