#!/usr/bin/env python3

from random import randint

def randomBool():
    return randint(0, 1) == 1

for n in range(1, 3):
    f = open("user" + str(n) + ".tsr", 'w')
    content = ""
    for i in range(0, 2):
        for team in [118, 148, 254, 1477, 1678, 1690, 2910, 3310, 4414]:
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
            content += f"2022-02-05@10:55:46,{team},unused,{match},{allainceColor},{taxi},{autoLower},{autoUpper},{autoMissed},{autoIntaken},{teleopLower},{teleopUpper},{teleopMissed},{teleopIntaken},{climb},comment \n"
    f.write(content)


        
 
    
    
        
    
    