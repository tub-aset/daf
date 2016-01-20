package de.jpwinkler.libs.doorsbridge.user32;

import com.sun.jna.Native;

public class Window {

	private int hWnd;

	public Window(int hWnd) {
		super();
		this.hWnd = hWnd;
	}

	public String getClassName() {
		byte[] buffer = new byte[1024];
		User32.instance.GetClassNameA(hWnd, buffer, buffer.length);
		return Native.toString(buffer);
	}

	public String getWindowText() {
		byte[] buffer = new byte[1024];
		User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
		return Native.toString(buffer);
	}

	public boolean isVisible() {
		return User32.instance.IsWindowVisible(hWnd);
	}
}
