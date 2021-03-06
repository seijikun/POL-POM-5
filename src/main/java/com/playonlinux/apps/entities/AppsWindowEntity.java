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

package com.playonlinux.apps.entities;

import com.playonlinux.core.entities.Entity;

import java.util.List;

public class AppsWindowEntity implements Entity {
    public List<AppEntity> getAppsItemDTOs() {
        return appsItemDTOs;
    }

    public List<AppsCategory> getCategoryDTOs() {
        return categoryDTOs;
    }

    public boolean isDownloadFailed() {
        return downloadFailed;
    }

    public boolean isDownloading() {
        return downloading;
    }

    final List<AppEntity> appsItemDTOs;
    final List<AppsCategory> categoryDTOs;
    final boolean downloading;
    final boolean downloadFailed;

    private AppsWindowEntity(Builder builder) {
        appsItemDTOs = builder.appsItemDTOs;
        categoryDTOs = builder.appsCategories;
        downloadFailed = builder.downloadFailed;
        downloading = builder.downloading;
    }

    public static class Builder {
        List<AppEntity> appsItemDTOs;
        List<AppsCategory> appsCategories;
        boolean downloading;
        boolean downloadFailed;

        public Builder withAppsItem(List<AppEntity> appsItemDTOs) {
            this.appsItemDTOs = appsItemDTOs;
            return this;
        }

        public Builder withAppsCategory(List<AppsCategory> appsCategories) {
            this.appsCategories = appsCategories;
            return this;
        }

        public Builder withDownloading(boolean downloading) {
            this.downloading = downloading;
            return this;
        }

        public Builder withDownloadFailed(boolean downloadFailed) {
            this.downloadFailed = downloadFailed;
            return this;
        }

        public AppsWindowEntity build() {
            return new AppsWindowEntity(this);
        }
    }
}
