package com.example.cp.production_orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductionOrdersImplementedTest {

    private ProductionOrders productionOrders;

    @BeforeEach
    public void initTest() {
        productionOrders = new ProductionOrdersImplemented();
    }

    @Test
    public void testGetAll() {
        assertFalse(productionOrders.getAll().isEmpty());
    }

    @Test
    public void testSearch() {
        SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
        searchProductionOrders.setDate("2002-08-04");
        searchProductionOrders.setSearch("");
        searchProductionOrders.setIs_open(false);
        assertFalse(productionOrders.search(searchProductionOrders).isEmpty());
    }

    @Test
    public void testDeleteById() {
        Integer id = 200;
        productionOrders.deleteById(id);
    }

    @Test
    public void testSave() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
        productionOrdersDB.setNumber_material("TEST");
        productionOrdersDB.setNeed_quantity(10);
        productionOrdersDB.setDate("11.03.2024");
        productionOrders.save(productionOrdersDB);
        searchProductionOrders.setSearch("TEST");
        assertFalse(productionOrders.search(searchProductionOrders).isEmpty());
    }

    @Test
    public void testUpdate() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setDate("11.03.2024");
        productionOrdersDB.setId(19);
        productionOrders.update(productionOrdersDB);
    }

  /*  @Test
    public void testReject() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setReject_quantity(2);
        productionOrdersDB.setId(17);
        productionOrders.reject(productionOrdersDB);
    }*/

    @Test
    public void testWriteOff() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setMaterial(12);
        productionOrdersDB.setId(17);
        productionOrdersDB.setNeed_quantity(10);
        Integer result = productionOrders.writeOff(productionOrdersDB);
        assertNotNull(result);
    }

   /* @Test
    public void testBackWriteOff() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setMaterial(12);
        productionOrdersDB.setId(17);
        productionOrdersDB.setNeed_quantity(10);
        productionOrders.backWriteOff(productionOrdersDB);
    }*/

    @Test
    public void testConsumptionReport() {
        SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
        searchProductionOrders.setDate_from("2023-11-23");
        searchProductionOrders.setDate_to("2023-12-23");
        assertFalse(productionOrders.consumptionReport(searchProductionOrders).isEmpty());
    }

    @Test
    public void testQualityReport() {
        SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
        searchProductionOrders.setDate_from("2023-11-23");
        searchProductionOrders.setDate_to("2023-12-23");
        assertFalse(productionOrders.qualityReport(searchProductionOrders).isEmpty());
    }

    @Test
    public void testSuppliersReport() {
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setNumber_material("JJ111");
        assertFalse(productionOrders.suppliersReport(productionOrdersDB).isEmpty());
    }

    @Test
    public void testAvgSuppliersReport() {
        assertFalse(productionOrders.avgSuppliersReport().isEmpty());
    }
}
