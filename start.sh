#!/bin/bash
java -jar WeatherEurekaServer/target/WeatherEurekaServer-0.0.1-SNAPSHOT.jar --server.port=8761

java -jar citydataservice/target/citydataservice-0.0.1-SNAPSHOT.jar --server.port=9001
java -jar citydataservice/target/citydataservice-0.0.1-SNAPSHOT.jar --server.port=9002

java -jar weathercollectionserver/target/weathercollectionserver-0.0.1-SNAPSHOT.jar --server.port=9003
java -jar weathercollectionserver/target/weathercollectionserver-0.0.1-SNAPSHOT.jar --server.port=9004

java -jar weatherqueryserver/target/weatherqueryserver-0.0.1-SNAPSHOT.jar --server.port=9005
java -jar weatherqueryserver/target/weatherqueryserver-0.0.1-SNAPSHOT.jar --server.port=9006

java -jar weatherreportservice/target/weatherreportservice-0.0.1-SNAPSHOT.jar --server.port=9007
java -jar weatherreportservice/target/weatherreportservice-0.0.1-SNAPSHOT.jar --server.port=9008
