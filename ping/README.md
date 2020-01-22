# ping

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
ping.pingLatency("www.samknows.com")
```

To call asynchronously

```
  suspend fun pingAsync(host: String) = withContext(Dispatchers.IO) {
    ping.pingLatency(host)
  }
            
```