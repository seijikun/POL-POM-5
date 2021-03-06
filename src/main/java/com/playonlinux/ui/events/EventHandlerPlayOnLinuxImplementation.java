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

package com.playonlinux.ui.events;

import com.playonlinux.app.PlayOnLinuxContext;
import com.playonlinux.app.PlayOnLinuxException;
import com.playonlinux.apps.AppsEntitiesProvider;
import com.playonlinux.apps.AppsManager;
import com.playonlinux.apps.AppsManagerException;
import com.playonlinux.apps.InstallerDownloaderEntityProvider;
import com.playonlinux.containers.VirtualDriveDTO;
import com.playonlinux.core.injection.Inject;
import com.playonlinux.core.injection.Scan;
import com.playonlinux.core.scripts.Script;
import com.playonlinux.core.scripts.ScriptFactory;
import com.playonlinux.core.services.manager.ServiceManager;
import com.playonlinux.core.services.virtualdrives.InstalledVirtualDrivesPlayOnLinuxDefaultImplementation;
import com.playonlinux.engines.wine.WineVersionEntitiesProvider;
import com.playonlinux.engines.wine.entities.WineVersionDistributionItemEntity;
import com.playonlinux.engines.wine.entities.WineVersionsWindowEntity;
import com.playonlinux.library.LibraryEntitiesProvider;
import com.playonlinux.library.dto.InstalledApplicationDTO;
import com.playonlinux.library.dto.LibraryWindowDTO;
import com.playonlinux.ui.api.EntitiesProvider;

import java.io.File;

@Scan
public final class EventHandlerPlayOnLinuxImplementation implements EventHandler {
    @Inject
    static ServiceManager playOnLinuxBackgroundServicesManager;

    @Inject
    static PlayOnLinuxContext playOnLinuxContext;

    @Inject
    private static ScriptFactory scriptFactory;

    private InstalledVirtualDrivesPlayOnLinuxDefaultImplementation virtualDrives;

    @Override
    public void runLocalScript(File scriptToRun) throws PlayOnLinuxException {
        Script playonlinuxScript = scriptFactory.createInstance(scriptToRun);
        playOnLinuxBackgroundServicesManager.register(playonlinuxScript);
    }

    @Override
    public void runApplication(String applicationName) throws PlayOnLinuxException {
        Script playonLinuxScript = scriptFactory.createInstance(
                new File(playOnLinuxContext.makeShortcutsScriptsPath(), applicationName)
        );
        playOnLinuxBackgroundServicesManager.register(playonLinuxScript);
    }

    @Override
    public Iterable<VirtualDriveDTO> getInstalledVirtualDrives() throws PlayOnLinuxException {
        if(virtualDrives == null) {
            virtualDrives = new InstalledVirtualDrivesPlayOnLinuxDefaultImplementation();
        }

        return this.virtualDrives;
    }

    @Override
    public AppsManager getAppsManager() {
        return playOnLinuxBackgroundServicesManager.getService(AppsManager.class);
    }

    @Override
    public AppsEntitiesProvider getRemoteAvailableInstallers() {
        return playOnLinuxBackgroundServicesManager.getService(AppsEntitiesProvider.class);
    }

    @Override
    public EntitiesProvider<WineVersionDistributionItemEntity, WineVersionsWindowEntity> getRemoteWineVersions() {
        return playOnLinuxBackgroundServicesManager.getService(WineVersionEntitiesProvider.class);
    }

    @Override
    public EntitiesProvider<InstalledApplicationDTO, LibraryWindowDTO> getInstalledApplications() {
        return playOnLinuxBackgroundServicesManager.getService(LibraryEntitiesProvider.class);
    }

    @Override
    public void refreshAvailableInstallers() throws PlayOnLinuxException {
        getAppsManager().refresh();
    }

    @Override
    public InstallerDownloaderEntityProvider getInstallerDownloaderEntityProvider(String scriptUrl) throws AppsManagerException {
        return getAppsManager().getDownloaderEntityProvider(scriptUrl);
    }

    @Override
    public void onApplicationStarted() {
        // Will be implemented later
    }



}
