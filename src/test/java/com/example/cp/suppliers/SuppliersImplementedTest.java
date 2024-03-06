package com.example.cp.suppliers;

import com.example.cp.materials.MaterialsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuppliersImplementedTest {

    private Suppliers suppliers;

    @BeforeEach
    public void initTest() {
        suppliers = new SuppliersImplemented();
    }

    @Test
    public void testGetAll() {
        assertFalse(suppliers.getAll().isEmpty());
    }

    @Test
    public void testSearch() {
        String searchString = "Поставщик";
        assertFalse(suppliers.search(searchString).isEmpty());
    }

    @Test
    public void testFindSuppliers() {
        assertFalse(suppliers.findSuppliers().isEmpty());
    }

    @Test
    public void testDeleteById() {
        assertTrue(suppliers.checkIfExists("TESTDEL"));
        suppliers.deleteById(100);
        assertFalse(suppliers.checkIfExists("TESTDEL"));
    }

    @Test
    public void testCheckIfExists() {
        String name = "TEST"; // Пример имени для тестирования
        assertFalse(suppliers.checkIfExists(name));
    }

    @Test
    public void testCheckForUpdate() {
        SuppliersDB suppliersDB = new SuppliersDB();
      suppliersDB.setName("TEST");
        suppliersDB.setId(2);
        assertFalse(suppliers.checkForUpdate(suppliersDB));
    }

    @Test
    public void testSave() {
        SuppliersDB suppliersDB = new SuppliersDB();
        suppliersDB.setName("TEST");
        suppliersDB.setPhone_number("TEST");
        suppliersDB.setEmail("TEST");
        suppliersDB.setAddress("TEST");
        suppliers.save(suppliersDB);
        assertTrue(suppliers.checkIfExists("TEST"));
    }

    @Test
    public void testUpdate() {
        SuppliersDB suppliersDB = new SuppliersDB();
        suppliersDB.setId(100);
        suppliersDB.setName("TEST");
        suppliersDB.setPhone_number("TEST");
        suppliersDB.setEmail("TEST");
        suppliersDB.setAddress("TEST");
        suppliers.update(suppliersDB);
    }
}

