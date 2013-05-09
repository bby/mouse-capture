mouse-capture
=============

Simple, hacky bit of Java using NativeHook library to capture mouse events at a Global (window independent) level. With a clunky class for parsing the stored distances from a file and putting them into a DB using a cron job.

Run
---
ant

java -cp JNativeHook.jar:. GlobalMouseListenerExample


TODO
----
There is also a the skeleton of socket.io connectivity to broadcast live updates but it needs to handle disconnections
