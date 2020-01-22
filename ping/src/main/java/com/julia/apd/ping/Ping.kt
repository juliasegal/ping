package com.julia.apd.ping

import java.io.BufferedReader
import java.io.InputStreamReader

/**
*  Ping contains functions to ping servers.
*/
class Ping  {

    /**
     * Returns average latency in milliseconds based on 5 pings
     *
     * @param  host
     *         url of server to ping.
     *
     * @throws  SecurityException
     *          If a security manager exists and its
     *          {@link SecurityManager#checkExec checkExec}
     *          method doesn't allow creation of the subprocess
     *
     * @throws  IOException
     *          If an I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>command</code> is <code>null</code>
     *
     * @throws  IllegalArgumentException
     *          If <code>command</code> is empty
     *
     *
     * @return  the average latency, or -1F if error
     */
    fun pingLatency(host: String): Float {
        var averageLatency = -1F
        val command = "$PING_COMMAND $host"
        val process: Process = Runtime.getRuntime().exec(command)
        val inputReader = BufferedReader(InputStreamReader(process.inputStream))
        val exitCode = process.waitFor()
        if (exitCode == 0) {
            val lines = inputReader.readLines()
            if (lines.isNotEmpty()) {
                // TODO do this with REGEX
                // expecting "rtt min/avg/max/mdev = 10.482/17.242/24.884/4.579 ms"
                val rttLine = lines[lines.size-1]
                val rttSplit = rttLine.split("=")
                if (rttSplit.size == 2) {
                    val rttData = rttSplit[1]
                    val dataSplit = rttData.split("/")
                    if (dataSplit.size == 4) {
                        averageLatency = dataSplit[1].toFloat()
                    }
                }
            }
        }
        process.destroy()
        return averageLatency
    }

    companion object {
        const val PING_COMMAND = "/system/bin/ping -c 5"
    }
}