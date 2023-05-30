package costtracker.adapter.dependencyinjection;
//package consttracker.adapter.dependencyinjection;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.util.HashMap;
//
//public class DependencyContainer {
//	private static DependencyContainer instance;
//
//	private final HashMap<Class<?>, Class<?>> dependencies = new HashMap<>();
//
//	private final HashMap<Class<?>, Object> singletons = new HashMap<Class<?>, Object>();
//
//	public static DependencyContainer getInstance() {
//		if (instance == null) {
//			instance = new DependencyContainer();
//		}
//		return instance;
//	}
//
//	public <TInterface, TClass> void registerScoped(Class<TInterface> port, Class<TClass> implementation) {
//		dependencies.put(port, implementation);
//	}
//
//	public <TClass> void registerScoped(Class<TClass> implementation) {
//		dependencies.put(implementation, implementation);
//	}
//
//	public <TInterface, TClass> void registerSingleton(Class<TInterface> port, Class<TClass> implementation) {
//	}
//
//	public <TClass> void registerSingleton(Class<TClass> implementation)
//			throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException,
//			NoSuchMethodException, SecurityException {
//		Constructor<TClass> constructor =  implementation.getDeclaredConstructor();
//		constructor.setAccessible(true);
//		var instance = constructor.newInstance();
//		for (var field : implementation.getFields()) {
//			injectField(instance, field);
//		}
//	}
//
//	private Object instantiateClass(Class<?> c) throws NoSuchMethodException, SecurityException, InstantiationException,
//			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Constructor<?> contructor = c.getDeclaredConstructor();
//		return contructor.newInstance();
//	}
//
//	private void injectField(Object instance, Field field) throws IllegalArgumentException, IllegalAccessException,
//			NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException {
//		field.setAccessible(true);
//		Object obj = singletons.get(field.getClass());
//		if (obj != null) {
//			field.set(instance, obj);
//			return;
//		}
//		Class<?> c = dependencies.get(field.getClass());
//		if (c != null) {
//			Object o = instantiateClass(c);
//			for (var f :c.getFields()) {
//				injectField(o, f);
//			}
//			field.set(instance, o);
//		}
//		return;
//	}
//
//	public <T> T getDependency(Class<T> type) {
//
//		var x = dependencies.get(type);
//		if (x == null)
//			throw new RuntimeException("No dependency of type " + type.getName() + " found.");
//		try {
//			var y = x.getDeclaredConstructor();
//		} catch (NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//}
