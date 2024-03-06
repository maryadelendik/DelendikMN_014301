package com.example.cp.prices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PricesImplementedTest {

    private Prices prices;

    @BeforeEach
    public void initTest() {
        prices = new PricesImplemented();
    }

    @Test
    public void testGetForMatSup() {
        PricesDB pricesDB = new PricesDB();
        pricesDB.setMat_name("pricesDB");
        pricesDB.setSupplier(1);
        float price = prices.getForMatSup(pricesDB);
        assertEquals(0f, price);
    }

    @Test
    public void testCheck() {
        PricesDB pricesDB = new PricesDB();
        pricesDB.setMat_name("pricesDB");
        pricesDB.setSupplier(1);
        boolean result = prices.check(pricesDB);
        assertFalse(result);
    }

    @Test
    public void testSave() {
        PricesDB pricesDB = new PricesDB();
        pricesDB.setMat_name("pricesDB");
        pricesDB.setSupplier(31);
        pricesDB.setPrice(100F);
        prices.save(pricesDB);
    }
}
