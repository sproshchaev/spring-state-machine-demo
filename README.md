# spring-state-machine-demo

           +-----------+
           |           |
           |   START   |
           |           |
           +-----+-----+
                 |
                 | (автoматически)
                 v
           +-----------+                        +-----------+
           |           |                        |           |
           |   OFF     +----------------------->+    ON     |
           |           |       TOGGLE/          |           |
           +----+------+      sendEvent()       +-----+-----+
                ^                                    |
                |                                    |
                |         TOGGLE/                    |
                |        sendEvent()                 |
                +------------------------------------+


```bash
curl get http://localhost:8080/lightbulb/status
```

```bash
curl get http://localhost:8080/lightbulb/toggle
```
