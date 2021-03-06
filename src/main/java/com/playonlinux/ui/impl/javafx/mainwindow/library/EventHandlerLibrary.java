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

package com.playonlinux.ui.impl.javafx.mainwindow.library;

import com.playonlinux.app.PlayOnLinuxException;
import com.playonlinux.core.injection.Inject;
import com.playonlinux.core.injection.Scan;
import com.playonlinux.library.dto.InstalledApplicationDTO;
import com.playonlinux.library.dto.LibraryWindowDTO;
import com.playonlinux.ui.api.EntitiesProvider;
import com.playonlinux.ui.api.UIEventHandler;
import com.playonlinux.ui.events.EventHandler;
import com.playonlinux.ui.impl.javafx.common.ErrorMessage;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;

@Scan
class EventHandlerLibrary implements UIEventHandler {
    @Inject
    static EventHandler mainEventHandler;

    private static final Logger LOGGER = Logger.getLogger(EventHandlerLibrary.class);

    public EntitiesProvider<InstalledApplicationDTO, LibraryWindowDTO> getInstalledApplications() {
        return mainEventHandler.getInstalledApplications();
    }

    public void runLocalScript(File scriptToRun) throws PlayOnLinuxException {
        mainEventHandler.runLocalScript(scriptToRun);
    }

    @Override
    public EventHandler getMainEventHandler() {
        return mainEventHandler;
    }

    public void onApplicationStarted() throws MalformedURLException {
        mainEventHandler.onApplicationStarted();
    }

    public void runApplication(String applicationName) {
        try {
            mainEventHandler.runApplication(applicationName);
        } catch (PlayOnLinuxException e) {
            LOGGER.error(e);
            new ErrorMessage("Error while trying to run the application", e).show();
        }
    }

}
