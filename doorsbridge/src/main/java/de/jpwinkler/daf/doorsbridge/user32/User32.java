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
