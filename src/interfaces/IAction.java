package interfaces;

import java.lang.reflect.InvocationTargetException;

import core.NombreNonValideException;

/*
 * Interface globale de l'application
 */
public interface IAction {
	public void soccuper(String action, Object game) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NombreNonValideException;
}
