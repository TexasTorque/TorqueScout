/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.utils;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Utilities static class that contains utility functions that aid
 * in working with JavaFX animations.
 * 
 * @author Justus Languell
 */
public class AnimationUtils {

    public static FadeTransition timedFade(Button btn, double duration, double opacity) {
        final FadeTransition fade = new FadeTransition(Duration.millis(duration));
        fade.setNode(btn);
        fade.setToValue(opacity);
        return fade;
    }

}
