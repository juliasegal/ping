# SamKnows Code Challenge

## Documentation
https://github.com/juliasegal/ping/blob/master/ping/README.md

 
## Approach
1. On command line, tried out ping and read the man docs.
2. Determined that 'ping -c 5' fulfills requirement to get average of 5 pings
3. Created a simple Ping Android app to host the ping library
    - This was done from a template app I recently created with the following 
        - Android MVVM architecture
        - Coroutines for aysnc functions
        - Koin for DI (Actually Service Locator)
        - Retrofit for REST endpoint to get list of hosts
    - Once the app was in place
        - added to MainActivity 
            - layout with list, layout for list item
            - adapter / viewholder /etc
        - added to MainView model
            - call to getHosts which will populate the list
    - Create library for Ping() that uses Runtime, Process to send command line argument
        - added pingLatency() functionality
        - called  Ping().pingLatency() for each host
 

# Proud of

I'm pleased this quickly came together.  
It shows a nice list, and asynchronously collects data for each item, updating each. 


# Things to do differently

1.  I would create tests for pingLatency().
    Tests would 
        include known hosts with known latencies and compare if data looks correct
        hosts would be tested with hosts names and address
        bad hosts names
        bad addresses
        invalid host names
        hosts with extremely long latencies
        
2. Provide more params for  pingLatency() to let app control more things like:
    - timeout
    - number of pings
    - maybe get other latencies (best/worst)
    
3. Errors: instead of returning -1 for a pingLatency(), I'd change to pingLatency(): Float? and return null for error
        Or perhaps more error codes
        
4. Unit test App, instrumented test for all components in the test app

5. UI needs to be polished.  If no icon, show broken icon, layouts cleaned up, error shown better

# Library used
- Retrofit for endpoint https://gist.githubusercontent.com/anonymous/290132e587b77155eebe44630fcd12cb/raw/777e85227d0c1c16e466475bb438e0807900155c/sk_hosts
- Glide for showing icons
- Koin for DI (Actually Service Locator) to have a clean architecture for testing