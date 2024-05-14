package com.example.cp.controllers;

import com.example.cp.history.History;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryImplemented;
import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.production_orders.*;
import com.example.cp.write_off.ReportWO;
import com.example.cp.write_off.WriteOff;
import com.example.cp.write_off.WriteOffImplemented;
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
    public ResponseEntity<List<ReportWO>> suppliers(@RequestBody ReportWO reportWO) {
        System.out.println("Выполняется формирование отчёта о поставщиках...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.suppliersReport(reportWO), HttpStatus.OK);
    }
    @PostMapping ("/abc")
    public ResponseEntity<List<ReportWO>> abc(@RequestBody ReportWO reportWO) {
        System.out.println("Выполняется ABC анализ расхода материалов...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.ABCReport(reportWO), HttpStatus.OK);
    }
    @PostMapping ("/abc_xlsx")
    public ResponseEntity<String> abc_xlsx(@RequestBody ReportWO reportWO) {
        System.out.println("Выполняется формирование XLSX файла 'ABC анализ расхода материалов'...");
        WriteOff writeOff = new WriteOffImplemented();
        writeOff.ABCXlsx(reportWO);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PostMapping ("/suppliers_xlsx")
    public ResponseEntity<String> suppliers_xlsx(@RequestBody ReportWO reportWO) {
        System.out.println("Выполняется формирование XLSX файла 'Отчёт о поставщиках'...");
        WriteOff writeOff = new WriteOffImplemented();
        writeOff.SuppliersXlsx(reportWO);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping ("/avg_suppliers")
    public ResponseEntity<ArrayList<Report>> avgSuppliers() {
        System.out.println("Выполняется формирование отчёта о поставщиках...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.avgSuppliersReport(), HttpStatus.OK);
    }

}
