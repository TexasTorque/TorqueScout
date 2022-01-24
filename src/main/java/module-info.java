/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
module TorqueScout {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.texastorque to javafx.fxml;
    exports org.texastorque;
}