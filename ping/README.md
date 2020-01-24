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

### Add Permission

Internet permission is required to ping and test the network.

Add permission to AndroidManifest.xml

```
    <uses-permission android:name="android.permission.INTERNET" />
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

## API

### Class Ping

Ping class has a function that returns the average network latency based on 5 pings

Create a Ping

```
val ping = Ping()    
```

### Function pingLatency

```
fun pingLatency(host: String): Float 
```

#### Parameters


@param host: String
        
String with hostname or address of the server to ping.  For example: wwww.samknows.com, pingLatency

```
val latency: Float = pingLatency.pingLatency("wwww.samknows.com")

val latency: Float = pingLatency.pingLatency("178.79.128.50") 
```

#### Return

@return Float

Returns average latency over 5 pings in milliseconds onSuccess().
If there is an error, -1 is returned.  Check if host name is correct
        
For example 
```
val latency = Ping().pingLatency("wwww.samknows.com")
print("ping:"$latency)
```

```
output:
ping:11.656
```


#### Exceptions

```
@throws  SecurityException
       If a security manager exists and its
       {@link SecurityManager#checkExec checkExec}
       method doesn't allow creation of the subprocess
       
       Add to AndroidManifest.xml the following
       ```
           <uses-permission android:name="android.permission.INTERNET" />
       ```

@throws  IOException
       If an I/O error occurs
     
```