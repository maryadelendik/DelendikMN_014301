package com.example.cp.supply_documents;

import com.example.cp.materials.MaterialsDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SupplyDocumentsImplementedTest {

    private SupplyDocuments supplyDocuments;

    @BeforeEach
    public void initTest() {
        supplyDocuments = new SupplyDocumentsImplemented();
    }

    @Test
    public void testGetAll() {
        List<SupplyDocumentsDB> supplyDocumentsList = supplyDocuments.getAll();
        assertNotNull(supplyDocumentsList);
    }

    @Test
    public void testSearch() {
        List<SupplyDocumentsDB> supplyDocumentsList = supplyDocuments.search("876");
        assertNotNull(supplyDocumentsList);
    }

    @Test
    public void testDeleteById() {
        Integer idToDelete = 133;
        supplyDocuments.deleteById(idToDelete);
    }

    @Test
    public void testSave() {
        SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
        supplyDocumentsDB.setNumber("xcvbn");
        supplyDocumentsDB.setMat_sup(2);
        supplyDocumentsDB.setQuantity(6);
        supplyDocumentsDB.setPrice(20F);
        supplyDocumentsDB.setDate("14.02.2024");
        supplyDocuments.save(supplyDocumentsDB);
    }

    @Test
    public void testUpdate() {
        SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
        supplyDocumentsDB.setId(30);
        supplyDocumentsDB.setNumber("xcvbn");
        supplyDocumentsDB.setMat_sup(2);
        supplyDocumentsDB.setQuantity(666);
        supplyDocumentsDB.setPrice(20F);
        supplyDocumentsDB.setDate("14.02.2024");
        supplyDocuments.update(supplyDocumentsDB);
    }

    @Test
    public void testCount() {
        SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
        supplyDocumentsDB.setSupplier("СтройМатериалы");
        supplyDocumentsDB.setMaterial("JJ111");
        supplyDocumentsDB.setQuantity(10);
        float totalCost = supplyDocuments.count(supplyDocumentsDB);
        Assertions.assertTrue(totalCost >= 0);
    }
}

