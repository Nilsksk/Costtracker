package costtracker.buisnesslogic.interfaces;

public interface DatabaseHandler<T> {

    T getById(int id);

    T[] getAll();

    T deleteById(int id);

    T update(T object);

    T create(T object);

}
