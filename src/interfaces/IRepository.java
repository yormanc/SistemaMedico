package interfaces;

public interface IRepository<T> {
    void add(T item);
    void update(T item);
    void delete(T item);

}
