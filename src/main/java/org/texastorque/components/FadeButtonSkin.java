/**
 * Copyright (C) 2021-2022 Texas Torque - All Rights Reserved.
 * 
 * This file is part of TorqueScout which is proprietary software.
 * See file license.txt or write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.components;

import org.texastorque.utils.AnimationUtils;

import javafx.animation.FadeTransition;
import javafx.scene.control.skin.ButtonSkin;

/**
 * A button skin wrapper that adds a click animation
 * to a button that is designed to be clicked and not
 * change state.
 * 
 * @author Justus Languell
 */
public class FadeButtonSkin extends ButtonSkin {

    public FadeButtonSkin(FadeButton control) {
        super(control);

        final FadeTransition fadeIn = AnimationUtils.timedFade(control, 200, 1);
        final FadeTransition fadeOut = AnimationUtils.timedFade(control, 200, .5);

        // control.setOnMouseExited(e -> fadeOut.playFromStart());
        // control.setOnMouseEntered(e -> fadeIn.playFromStart());

        control.onMousePressedProperty().set(e -> {
            fadeOut.playFromStart();
        });

        control.onMouseReleasedProperty().set(e -> {
            fadeIn.playFromStart();
        });
    }

}
