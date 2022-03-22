# TorqueScout

TorqueScout is Texas Torque's scouting utility for the 2022 season, Rapid React. The program is divided into two parts:

- the data logger
- the data analyzer

TorqueScout is designed to extract a few specific metrics that we use in formulating alliance selection decisions.

- individual team score
- accuracy during teleop and autonomous
- autonomous performance v.s. that of our own
- climb performance v.s. that of our own

The program is also used for team feedback and match strategy.

## Build

### Build and Run Development Application

To compile and execute the development application, run:

`mvn clean javafx:run`

### Build and Generate JAR

`mvn package` to generate JAR

`java -jar .\target\TorqueScout-1.0.0.jar` to execute JAR

`TorqueScout-1.0.0.jar` runs standalone (tested with Java 16 SE)

[Can be accessed here](https://drive.google.com/file/d/1EHjj3KXQCD2eKivbDu614zc0oZFsY0FF/view?usp=sharing)

### Build Distribution with Sustom JRE

To generate the custom JRE bundle, run:

`mvn clean javafx:jlink`.

The bundle can be ran at

`target/TorqueScout/bin/launcher`

## License

Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.

This file is part of TorqueScout which is proprietary software.
TorqueScout is not available for modification or distribution without express consent from TexasTorque.
See file [./license.txt](https://github.com/TexasTorque/TorqueScout/blob/master/license.txt) or go write <jus@gtsbr.org> for full license details.
