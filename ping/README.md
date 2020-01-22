# ping

Ping is a library with a tool to ping a server with one function pingLatency() that returns the average latency in ms.

## Getting Started

### Import Library

```app build.gradle
buildscript {

    dependencies {
    ...
    implementation project(":ping")
    ...
    }
}
```

## Usage

```
val ping = Ping()
val averageLatency: Float = ping.pingLatency("www.samknows.com")
```

pingLatency() returns the average latency of 5 pings, or -1 if error.

To call asynchronously

```
suspend fun pingAsync(host: String) = withContext(Dispatchers.IO) {
    Ping().pingLatency(host)
}     
```