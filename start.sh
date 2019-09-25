#!/bin/bash

# /Users/xxdeng/Documents/workspace-sts-3.9.4.RELEASE/SpringCloudWeather/WeatherMicroService/
java -jar WeatherEurekaServer/target/WeatherEurekaServer-0.0.1-SNAPSHOT.jar --server.port=8761

java -jar citydataservice/target/citydataserver-0.0.1-SNAPSHOT.jar --server.port=9001
java -jar citydataservice/target/citydataserver-0.0.1-SNAPSHOT.jar --server.port=9002

java -jar weathercollectionserver/target/weathercollectionserver-0.0.1-SNAPSHOT.jar --server.port=9003
java -jar weathercollectionserver/target/weathercollectionserver-0.0.1-SNAPSHOT.jar --server.port=9004

java -jar weatherqueryserver/target/weatherqueryserver-0.0.1-SNAPSHOT.jar --server.port=9005
java -jar weatherqueryserver/target/weatherqueryserver-0.0.1-SNAPSHOT.jar --server.port=9006

java -jar weatherreportservice/target/weatherreportserver-0.0.1-SNAPSHOT.jar --server.port=9007
java -jar weatherreportservice/target/weatherreportserver-0.0.1-SNAPSHOT.jar --server.port=9008

java -jar weathergatewayserver/target/weathergatewayserver-0.0.1-SNAPSHOT.jar --server.port=9010

java -jar micro-weather-config-server/target/micro-weather-config-server-0.0.1-SNAPSHOT.jar --server.port=8888

java -jar micro-weather-config-server/target/micro-weather-config-server-0.0.1-SNAPSHOT.jar --server.port=8881


