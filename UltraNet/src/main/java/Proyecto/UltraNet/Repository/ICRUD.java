package Proyecto.UltraNet.Repository;

import java.util.ArrayList;

public interface ICRUD <T,integer>{

    public T add(T element);
    public ArrayList<T> getAll();
    public T update(T element);
    public T edit(T element);
    public T delete(Integer id);
    public T search(Integer id);

    //Integer getId();

}
