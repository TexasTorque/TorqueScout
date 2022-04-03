#!/usr/bin/env python3

# Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
#
# This file is part of TorqueScout which is proprietary software.
# TorqueScout is not available for modification or distribution without express consent from TexasTorque.
# See file ./license.txt or go write <jus@gtsbr.org> for full license details.
# 
# @author Justus Languell
 
from random import randint

def randomBool():
    return randint(0, 1) == 1

for n in range(1, 7):
    f = open("user" + str(n) + ".tsr", 'w')
    content = ""
    for i in range(0, 2):
        for team in [118, 148, 254, 624, 1477, 1678, 1690, 2468, 2910, 3005, 3310, 3847, 4414, 6800]:
            match = randint(1, 35)
            allainceColor = "red" if randomBool() else "blue"
            taxi = "true" if randomBool() else "false"
            shootsHigh = randomBool()
            autoLower, autoUpper = 0, 0
            teleopLower, teleopUpper = 0, 0
            
            
            if taxi == "true":
                autoIntaken = randint(0, 4)
            else:
                autoIntaken = 0
            autoMissed = randint(0, autoIntaken + 1)
            if shootsHigh:
                autoLower = autoIntaken - autoMissed
                if autoLower <= 0:
                    autoLower = 0
            else:
                autoUpper = autoIntaken - autoMissed
                if autoUpper <= 0:
                    autoUpper = 0
            teleopIntaken = randint(0, 14)
            teleopMissed = randint(0, teleopIntaken + 1)   
            if shootsHigh:
                teleopLower = teleopIntaken - teleopMissed
                if teleopLower <= 0:
                    teleopLower = 0
            else:
                teleopUpper = teleopIntaken - teleopMissed
                if teleopUpper <= 0:
                    teleopUpper = 0
                    
            climb = randint(0, 4)
            climbTime = randint(5,15) * climb
            
            content += f"{team},{match},{allainceColor},{taxi},{autoLower},{autoUpper},{autoMissed},{autoIntaken},{teleopLower},{teleopUpper},{teleopMissed},{teleopIntaken},{climb},{climbTime},comment \n"
    f.write(content)
