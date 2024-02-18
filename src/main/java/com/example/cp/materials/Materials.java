package com.example.cp.materials;



import com.example.cp.history.HistoryDB;

import java.util.HashSet;
import java.util.List;

public interface Materials {

    List<MaterialsDB> getAll();
    List<MaterialsDB> getAllByType(String type);

    List<MaterialsDB> search(String string, Boolean has_type, String type);
    HashSet<String> findTypes();
    boolean checkIfExists(String number);
    boolean checkForUpdate(MaterialsDB materialsDB);
    void deleteById(Integer id);
    List<HistoryDB> findChanges(MaterialsDB materialsDB);
    void save(MaterialsDB materialsDB);
    HashSet<String> findMaterials();
    void update(MaterialsDB materialsDB);
}
