package caloriecounter.input;


public interface Input<E> {
    Iterable<E> getContents();
}
