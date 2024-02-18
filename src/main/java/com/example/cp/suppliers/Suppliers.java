package com.example.cp.suppliers;


import java.util.HashSet;
import java.util.List;

public interface Suppliers {

    List<SuppliersDB> getAll();
    List<SuppliersDB> search(String string);
    HashSet<String> findSuppliers();
    void deleteById(Integer id);
    boolean checkIfExists(String name);
    boolean checkForUpdate(SuppliersDB suppliersDB);
    void save(SuppliersDB suppliersDB);

    void update(SuppliersDB suppliersDB);
}
