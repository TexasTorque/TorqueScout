/**
 * Copyright (C) 2021-2022 Texas Torque - All Rights Reserved.
 * 
 * This file is part of TorqueScout which is proprietary software.
 * See file license.txt or write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.pages;

import javafx.scene.layout.Pane;

public abstract class Page {
    protected Pane panel;

    public abstract Pane getPanel();
}
