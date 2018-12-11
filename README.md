# mongo

https://stackoverflow.com/questions/6520439/how-to-configure-mongodb-java-driver-mongooptions-for-production-use


https://scalegrid.io/blog/understanding-mongodb-client-timeout-options/


## ServerSelctionTimeOut

Server selection timeout is the number of milliseconds the mongo driver will wait to select a server for an operation before giving up and raising an error.Mongo driver uses 30s as a default value of the server selection timeout. Depending on the use case, we can increase or decrease this threshold.

## Connection Timeout 

Connection timeout is the number of milliseconds the driver will wait before a new connection attempt is aborted.The default value of a connection timeout depends on the version and language of the driver. Mongo Java & Ruby latest driver versions have a 10s default timeout for connection establishments while the NodeJs driver has no timeout.If the timeout is too high, you risk stalling your application. If the timeout is too low, you can give up too quickly. It is better to test with different values to find the right timeout for your application.

## SocketTimeout 

Socket timeout is the number of milliseconds a send or receive on a socket can take before time out.Mongo Java & Nodejs Driver have a default socket timeout of 0s which means basically no timeout. While Ruby offers a 5s socket timeout. You don’t want to put a limit on this timeout as different operations will take the variable time to operate.

## maxWaitTime

Number of ms a thread will wait for a connection to become available on the connection pool, and raises an exception if this does not happen in time. Keep default.

## How to run this app?

currently - from the debugger (IDE).