package tool

fun <K, V> MutableMap<K, V>.getOrThrow(key: K, throwable: Throwable): V = this.get(key) ?: throw throwable