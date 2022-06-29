Spring Boot Application which I use to test actuator AOT support.

## Notes

* `AuditEvent` takes a `Map<String, Object>` which is then serialized to JSON. How to
  generate hints for the `Object`?
* `/conditions` is completely empty. I think this must be because all the conditions are
  evaluated at build time. Double check!
* `/heapdump` is not supported on native-image
* Flyway support is incomplete. It starts up, but doesn't find any migrations.
  But it's enough for the `/flyway` endpoint.
* `/threaddump` is not supported:
  ```
  com.oracle.svm.core.jdk.UnsupportedFeatureError: ThreadMXBean methods
        at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:89) ~[na:na]
        at com.oracle.svm.core.jdk.management.SubstrateThreadMXBean.dumpAllThreads(SubstrateThreadMXBean.java:240) ~[na:na]
        at org.springframework.boot.actuate.management.ThreadDumpEndpoint.getFormattedThreadDump(ThreadDumpEndpoint.java:51) ~[actuator-aot:3.0.0-SNAPSHOT]
        at org.springframework.boot.actuate.management.ThreadDumpEndpoint.textThreadDump(ThreadDumpEndpoint.java:47) ~[actuator-aot:3.0.0-SNAPSHOT]
  ```
