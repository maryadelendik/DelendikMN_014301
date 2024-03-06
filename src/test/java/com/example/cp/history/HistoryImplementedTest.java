package com.example.cp.history;

import com.example.cp.materials.MaterialsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryImplementedTest {

    private History history;

    @BeforeEach
    public void initTest() {
        history = new HistoryImplemented();
    }

    @Test
    public void testGetAll() {
        Integer materialId = 1;
        List<HistoryDB> historyList = history.getAll(materialId);
        assertNotNull(historyList);
        assertFalse(historyList.isEmpty());
    }

    @Test
    public void testGetAll_WithInvalidId() {
        Integer invalidMaterialId = -1;
        List<HistoryDB> historyList = history.getAll(invalidMaterialId);
        assertNotNull(historyList);
        assertTrue(historyList.isEmpty());
    }

    @Test
    public void testSave() {
        HistoryDB historyDB = new HistoryDB();
        historyDB.setUser("1");
        historyDB.setChanging_type("ИЗМЕНЕНИЕ");
        historyDB.setMaterial(12);
        historyDB.setOld_value("old");
        historyDB.setNew_value("new");
        history.save(historyDB);
    }

}
