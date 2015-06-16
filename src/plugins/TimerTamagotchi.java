package plugins;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TimerTask;

public class TimerTamagotchi extends TimerTask {

	private Tamagotchi tama;
	private int val;
	private Appli app;
	private String attr;
	
	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public Appli getApp() {
		return app;
	}

	public void setApp(Appli app) {
		this.app = app;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Tamagotchi getTama() {
		return tama;
	}

	public void setTama(Tamagotchi tama) {
		this.tama = tama;
	}

	@Override
	public void run() {
		Method m;
		try {
			m = tama.getClass().getMethod("get" + attr);
			int value = (int) m.invoke(tama);
			m = tama.getClass().getMethod("set" + attr, int.class);
			m.invoke(tama, value - val);
			app.newGame(tama.getEspece());
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
