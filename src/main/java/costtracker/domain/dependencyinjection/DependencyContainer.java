package costtracker.domain.dependencyinjection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class DependencyContainer {
	private HashMap<Class<?>, Object> singletons = new HashMap<>();

	private HashMap<Class<?>, Class<?>> scopeds = new HashMap<>();

	private static DependencyContainer instance;
	
	private DependencyContainer() {
		
	}
	
	public static DependencyContainer getInstance() {
		if(instance == null)
			instance = new DependencyContainer();
		return instance;
	}
	
	public void registerSingleton(Class<?> i, Object o) {
		singletons.put(i, o);
	}

	public void registerScoped(Class<?> i, Class<?> c) {
		scopeds.put(i, c);
	}

	@SuppressWarnings("unchecked")
	public <T> T getDependency(Class<T> type) {
		if (singletons.containsKey(type)) {
			return (T) singletons.get(type);
		} else if (scopeds.containsKey(type)) {
			Class<?> c = scopeds.get(type);
			try {
				Constructor<?> constructor = c.getDeclaredConstructor();
				return (T) constructor.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				return null;
			}
		}
		return null;
	}
}
