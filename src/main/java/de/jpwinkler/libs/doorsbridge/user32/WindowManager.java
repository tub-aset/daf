package de.jpwinkler.libs.doorsbridge.user32;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {

	public List<Window> listWindows() {
		final List<Window> result = new ArrayList<>();
		
		User32.instance.EnumWindows(new WndEnumProc() {

			@Override
			public boolean callback(int hWnd, int lParam) {
				result.add(new Window(hWnd));
				return true;
			}
		}, 0);
		return result;
	}

}
