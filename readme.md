# TorqueScout

Written by Justus and Suhas as a replacement to [TorqueScout-Deprecated](https://github.com/TexasTorque/TorqueScout-Deprecated).

## Build

### Build and Run Development Application

To compile and execute the development application, run:

`mvn clean javafx:run`

### Build and Generate JAR

`mvn package` to generate JAR

`java -jar .\target\TorqueScout-1.0.0.jar` to execute JAR

`TorqueScout-1.0.0.jar` runs standalone (tested with Java 16 SE)
https://drive.google.com/file/d/1EHjj3KXQCD2eKivbDu614zc0oZFsY0FF/view?usp=sharing

### Build Distribution with Sustom JRE

To generate the custom JRE bundle, run:

`mvn clean javafx:jlink`.

The bundle can be ran at

`target/TorqueScout/bin/launcher`
