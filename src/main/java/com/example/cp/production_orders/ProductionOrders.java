package com.example.cp.production_orders;



import java.util.ArrayList;
import java.util.List;

public interface ProductionOrders {

    List<ProductionOrdersDB> getAll();
    void deleteById(Integer id);
    void save(ProductionOrdersDB productionOrdersDB);
    void update(ProductionOrdersDB productionOrdersDB);

    Integer writeOff(ProductionOrdersDB productionOrdersDB);

    ArrayList<Report> consumptionReport(SearchProductionOrders searchProductionOrders);
    ArrayList<Report> qualityReport(SearchProductionOrders searchProductionOrders);

    ArrayList<Report> avgSuppliersReport();

    List<ProductionOrdersDB> search(SearchProductionOrders searchProductionOrders);
}
