Spring Boot Application which I use to test actuator AOT support.

## Notes

* `AuditEvent` takes a `Map<String, Object>` which is then serialized to JSON. How to
  generate hints for the `Object`?
* `/conditions` is completely empty. I think this must be because all the conditions are
  evaluated at build time. Double check!
* `/heapdump` is not supported on native-image
* Flyway support is incomplete. It starts up, but doesn't find any migrations.
  But it's enough for the `/flyway` endpoint.
