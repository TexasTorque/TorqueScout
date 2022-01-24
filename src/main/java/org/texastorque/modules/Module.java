/**
 * Copyright (C) 2021-2022 Texas Torque - All Rights Reserved.
 * 
 * This file is part of TorqueScout which is proprietary software.
 * See file license.txt or write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.modules;

import javafx.scene.layout.Pane;

/**
 * Abstract base class for program modules.
 * 
 * @author Justus Languell
 */
public abstract class Module {
    protected String name;
    protected Pane panel = new Pane();

    protected abstract void init();

    public abstract String getName();

    public abstract Pane getPanel();
}
