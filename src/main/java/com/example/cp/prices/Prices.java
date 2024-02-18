package com.example.cp.prices;


public interface Prices {


    Float getForMatSup(PricesDB pricesDB);
    Boolean check(PricesDB pricesDB);
    void save(PricesDB pricesDB);

}
