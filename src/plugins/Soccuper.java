package plugins;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import core.NombreNonValideException;
import interfaces.IAction;

public class Soccuper implements IAction{

	@Override
	public void soccuper(String action, Object game) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NombreNonValideException {
		String[] conf = action.split(";");
		
		Method m = game.getClass().getMethod("get" + conf[0], null);
		
		int oldVal = (int) m.invoke(game);
		
		Method m2 = game.getClass().getMethod("set" + conf[0], int.class);
		
		int val = oldVal;
		if (oldVal < 100){
			if(conf[1].contains("+")) {
				val = Integer.parseInt(conf[1].replace("+", ""));
				val += oldVal;
			}
			else {
				val = Integer.parseInt(conf[1].replace("-", ""));
				val -= oldVal;
			}
		}
		
		if(val > 100) {	
			val = 100;
		}
		m2.invoke(game, val);
		int test = (int) m.invoke(game);
		
		System.out.println(test);
	}

}
