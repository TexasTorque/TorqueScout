/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.components;

import javafx.scene.control.Button;

/**
 * Implements the FadeButtonSkin into a custom button class, providing
 * a function interface for using fading buttons.
 */
public class FadeButton extends Button {
    public FadeButton() {
        super();
        setSkin(new FadeButtonSkin(this));
    }
}
