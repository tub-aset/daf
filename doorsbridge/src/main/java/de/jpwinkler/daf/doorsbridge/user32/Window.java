/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.doorsbridge.user32;

import com.sun.jna.Native;

public class Window {

    private final int hWnd;

    public Window(final int hWnd) {
        super();
        this.hWnd = hWnd;
    }

    public String getClassName() {
        final byte[] buffer = new byte[1024];
        User32.instance.GetClassNameA(hWnd, buffer, buffer.length);
        return Native.toString(buffer);
    }

    public String getWindowText() {
        final byte[] buffer = new byte[1024];
        User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
        return Native.toString(buffer);
    }

    public boolean isVisible() {
        return User32.instance.IsWindowVisible(hWnd);
    }
}
