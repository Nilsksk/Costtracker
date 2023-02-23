package costtracker.buisnesslogic.interfaces;

public interface StateHandler<T> {

    boolean enable();

    boolean disable();

    T[] getEnabled();

}
