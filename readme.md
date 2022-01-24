<!-- Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.

This file is part of TorqueScout which is proprietary software.
TorqueScout is not available for modification or distribution without express consent from TexasTorque.
See file ./license.txt or go write <jus@gtsbr.org> for full license details. -->

# TorqueScout

Written by Justus Languell to replacement [TorqueScout-Deprecated](https://github.com/TexasTorque/TorqueScout-Deprecated).

This repo contains only the second edition of TorqueScout. Commits start at the first refactoring, as the initial repository was squashed.

## Build

### Build and run development application

To compile and execute the development application, run:

`mvn clean javafx:run`

### Build distribution with custom JRE

To generate the custom JRE bundle, run:

`mvn clean javafx:jlink`.

The bundle can be ran at

`target/TorqueScout/bin/launcher`
