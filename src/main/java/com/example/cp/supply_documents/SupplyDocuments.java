package com.example.cp.supply_documents;



import java.util.List;

public interface SupplyDocuments {

    List<SupplyDocumentsDB> getAll();
    List<SupplyDocumentsDB> search(String string);

    void deleteById(Integer id);

    void save(SupplyDocumentsDB supplyDocumentsDB);

    void update(SupplyDocumentsDB supplyDocumentsDB);
    Float count(SupplyDocumentsDB supplyDocumentsDB);
}
