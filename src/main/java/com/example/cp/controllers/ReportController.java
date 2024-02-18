package com.example.cp.controllers;

import com.example.cp.history.History;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryImplemented;
import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.production_orders.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/report")
public class ReportController {

    @PostMapping ("/consumption")
    public ResponseEntity<List<Report>> consumption(@RequestBody SearchProductionOrders searchProductionOrders) {
        System.out.println("Выполняется формирование отчёта о расходовании материала...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.consumptionReport(searchProductionOrders), HttpStatus.OK);
    }
    @PostMapping ("/quality")
    public ResponseEntity<List<Report>> quality(@RequestBody SearchProductionOrders searchProductionOrders) {
        System.out.println("Выполняется формирование отчёта о качестве материала...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.qualityReport(searchProductionOrders), HttpStatus.OK);
    }
    @PostMapping ("/suppliers")
    public ResponseEntity<List<Report>> suppliers(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется формирование отчёта о поставщиках...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.suppliersReport(productionOrdersDB), HttpStatus.OK);
    }
    @GetMapping ("/avg_suppliers")
    public ResponseEntity<ArrayList<Report>> avgSuppliers() {
        System.out.println("Выполняется формирование отчёта о поставщиках...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.avgSuppliersReport(), HttpStatus.OK);
    }

}
