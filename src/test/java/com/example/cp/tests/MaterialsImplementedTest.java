package com.example.cp.tests;
import com.example.cp.history.HistoryDB;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

public class MaterialsImplementedTest {

    private MaterialsImplemented materials;

    @BeforeEach
    public void initTest() {
        materials = new MaterialsImplemented();
    }

    @Test
    public void testGetAll() {
        List<MaterialsDB> materialsList = materials.getAll();
        assertNotNull(materialsList);
        assertFalse(materialsList.isEmpty());
    }

    @Test
    public void testGetAllByType() {
        String type = "Колесо";
        List<MaterialsDB> materialsList = materials.getAllByType(type);
        assertNotNull(materialsList);
        assertFalse(materialsList.isEmpty());
        for (MaterialsDB material : materialsList) {
            assertEquals(type, material.getType());
        }
    }

    @Test
    public void testSearch() {
        String searchString = "MAT";
        List<MaterialsDB> materialsList = materials.search(searchString, false, "");
        assertNotNull(materialsList);
        assertFalse(materialsList.isEmpty());
        for (MaterialsDB material : materialsList) {
            assertTrue(material.getName().contains(searchString) || material.getNumber().contains(searchString));
        }
    }

    @Test
    public void testFindTypes() {
        HashSet<String> types = materials.findTypes();
        assertNotNull(types);
        assertFalse(types.isEmpty());
    }

    @Test
    public void testCheckIfExists() {
        boolean exists = materials.checkIfExists("MAT2");
        assertTrue(exists);
    }

    @Test
    public void testCheckForUpdate() {
        MaterialsDB material = new MaterialsDB();
        boolean canUpdate = materials.checkForUpdate(material);
        assertTrue(canUpdate);
    }

    @Test
    public void testFindIdUser() {
        int id = materials.findIdUser("1");
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void testFindIdMaterial() {
        int id = materials.findIdMaterial("MAT2");
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void testDeleteById() {
        assertTrue(materials.checkIfExists("sdg"));
        MaterialsDB testMaterial = new MaterialsDB();
        testMaterial.setId(52);
        materials.deleteById(testMaterial.getId());
        assertFalse(materials.checkIfExists("sdg"));
    }

    @Test
    public void testSave() {
        MaterialsDB material = new MaterialsDB();
        String number = "TEST";
        material.setName("TEST");
        material.setType("TEST");
        material.setNumber(number);
        material.setStock_quantity(2);
        material.setUser_login("1");
        materials.save(material);
        assertTrue(materials.checkIfExists(number));
    }

    @Test
    public void testFindMaterials() {
        HashSet<String> materialsSet = materials.findMaterials();
        assertNotNull(materialsSet);
        assertFalse(materialsSet.isEmpty());
    }

    @Test
    public void testUpdate() {
        MaterialsDB material = new MaterialsDB();
        material.setId(53);
        material.setNumber("TEST");
        material.setName("TEST2");
        material.setType("TEST2");
        material.setStock_quantity(22);
        material.setOrder_quantity(0);
        materials.update(material);
    }
}
