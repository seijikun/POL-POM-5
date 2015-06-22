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

package com.playonlinux.webservice;

import com.playonlinux.app.PlayOnLinuxContext;
import com.playonlinux.injection.Inject;
import com.playonlinux.installer.Script;
import com.playonlinux.installer.ScriptFactory;
import com.playonlinux.messages.ParametrableRunnable;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteScriptDownloader {
    @Inject
    private static DownloadManager downloadManager;

    @Inject
    private static PlayOnLinuxContext playOnLinuxContext;

    private final URL scriptUrl;

    RemoteScriptDownloader(int scriptId) throws MalformedURLException {
        this(new URL(
                playOnLinuxContext.getProperty("webservice.script")
                        .replace("{ID}", Integer.toString(scriptId))
                )
        );
    }

    RemoteScriptDownloader(URL scriptUrl) {
        this.scriptUrl = scriptUrl;
    }

    Script download(ScriptFactory scriptFactory) {
        return null; // TODO!
    }

}
