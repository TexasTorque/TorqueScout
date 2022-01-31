# TorqueScout

Written by Justus and Suhas as a replacement to [TorqueScout-Deprecated](https://github.com/TexasTorque/TorqueScout-Deprecated).

## Build

### Build and run development application

To compile and execute the development application, run:

`mvn clean javafx:run`

### Build distribution with custom JRE

To generate the custom JRE bundle, run:

`mvn clean javafx:jlink`.

The bundle can be ran at

`target/TorqueScout/bin/launcher`
