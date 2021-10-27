package oblig2;

public interface Tree<E> extends Iterable<E>{
    boolean search(E e);
    boolean insert(E e);
    boolean delete(E e);
    int getSize();

}
