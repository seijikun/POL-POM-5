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

package com.playonlinux.dto.ui;

import static org.junit.Assert.assertEquals;

import com.playonlinux.apps.entities.AppsCategory;
import org.junit.Before;
import org.junit.Test;

public class AppsCategoryTest {

    private AppsCategory appsCategory;

    @Before
    public void setUp() {
        this.appsCategory = new AppsCategory("Name");
    }
    
    @Test
    public void testCenterCategoryDTO_CreateDTO_nameIsPopulated() {
        assertEquals("Name", appsCategory.getName());
    }

    @Test
    public void testCenterCategoryDTO_CreateDTO_iconIsPopulated() {
        assertEquals("apps/applications-name.png", appsCategory.getIconName());
    }
}