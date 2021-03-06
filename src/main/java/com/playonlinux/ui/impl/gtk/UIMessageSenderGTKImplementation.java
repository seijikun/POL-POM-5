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

package com.playonlinux.ui.impl.gtk;

import com.playonlinux.core.messages.Message;
import com.playonlinux.core.messages.SynchronousMessage;
import com.playonlinux.core.scripts.CancelException;
import com.playonlinux.ui.api.UIMessageSender;
import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UIMessageSenderGTKImplementation<RETURN> implements UIMessageSender<RETURN>  {
    private static final Logger LOGGER = Logger.getLogger(UIMessageSenderGTKImplementation.class);

    @Override
    public RETURN synchronousSendAndGetResult(SynchronousMessage<RETURN> message) throws CancelException {
        this.synchronousSend(message);
        return message.getResponse();
    }

    @Override
    public void synchronousSend(Message message) {
        final Semaphore lock = new Semaphore(0);
        Executors.newSingleThreadExecutor().submit(() -> {
            message.run();
            lock.release();
        });
        try {
            lock.acquire();
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
    }

    @Override
    public void asynchronousSend(Message message) {
        Executors.newSingleThreadExecutor().submit(message);
    }
}
