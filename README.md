# micrometer-new-relic-otlp
This project is a sample application that setup micrometer to send metrics to New Relic via OTLP.
The settings are set in the [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) file.
The test controller is in the  file [TestController.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fnewrelicmicrometer%2FTestController.java) file.
This controller has an endpoint that records a random integer value to simulate response size.
You can use `watch curl http://localhost:8080/test` to generate data for micrometer to push to New Relic.