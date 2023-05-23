package costtracker.ui;

import java.util.List;
import java.util.function.Function;

public class GetBusinessObject {

	public static <T> T getById(int id, List<T> objects, Function<T, Integer> idGetter){
		T object;
		try {
			object = objects.stream().filter(o -> idGetter.apply(o) == id).findFirst().get();
		}
		catch(Exception e) {
			object = null;
		}
		return object;
		
	}
}
