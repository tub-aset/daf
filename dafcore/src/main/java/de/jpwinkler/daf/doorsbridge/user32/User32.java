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

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface User32 extends Library {

    final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

    boolean EnumWindows(WndEnumProc wndenumproc, int lParam);

    boolean IsWindowVisible(int hWnd);

    void GetWindowTextA(int hWnd, byte[] buffer, int buflen);

    int GetClassNameA(int hWnd, byte[] lpClassName, int nMaxCount);

    final int GW_HWNDNEXT = 2;

}
