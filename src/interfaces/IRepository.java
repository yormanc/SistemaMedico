package interfaces;
public interface IRepository<T> {
    boolean add(T item);
    boolean update(T item);
    boolean remove(T item);
}
