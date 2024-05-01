package com.example.cp.supply_documents;



import java.util.List;

public interface SupplyDocuments {

    List<SupplyDocumentsDB> getAll();
    List<SupplyDocumentsDB> search(String string);
    List<SupplyDocumentsDB> getLots(Integer id);
    void deleteById(Integer id);
    void alignLeftovers();

    void save(SupplyDocumentsDB supplyDocumentsDB);

    void update(SupplyDocumentsDB supplyDocumentsDB);
    Float count(SupplyDocumentsDB supplyDocumentsDB);
}
