/*
 * Copyright (C) 2015 PÂRIS Quentin
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.playonlinux.ui.impl.javafx.common;

import com.playonlinux.app.PlayOnLinuxException;
import javafx.scene.control.Alert;

import static com.playonlinux.core.lang.Localisation.translate;

public class ErrorMessage {
    final Alert alert;

    public ErrorMessage(String message, PlayOnLinuxException exception) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(translate(message));
        alert.setContentText(String.format("The error was: %s", exception));
    }

    public void show() {
        alert.show();
    }
}
