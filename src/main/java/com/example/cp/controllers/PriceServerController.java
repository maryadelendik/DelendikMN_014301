package com.example.cp.controllers;

import com.example.cp.prices.Prices;
import com.example.cp.prices.PricesDB;
import com.example.cp.prices.PricesImplemented;
import com.example.cp.suppliers.Suppliers;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.suppliers.SuppliersImplemented;
import com.example.cp.supply_documents.SupplyDocuments;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/price")
public class PriceServerController {
    @PostMapping ("/add")
    public ResponseEntity<HashSet<String>> addPrice(@RequestBody PricesDB pricesDB) {
        System.out.println("Выполняется добавление цены...");
        Prices prices = new PricesImplemented();
        prices.save(pricesDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/check")
    public ResponseEntity<Boolean> check(@RequestBody PricesDB pricesDB) {
        System.out.println("Выполняется проверка наличия цены...");
        Prices prices = new PricesImplemented();
        return new ResponseEntity<>(prices.check(pricesDB), HttpStatus.OK);
    }
    @PostMapping ("/count")
    public ResponseEntity<Float> count(@RequestBody SupplyDocumentsDB supplyDocumentsDB) {
        System.out.println("Выполняется расчёт цены...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        return new ResponseEntity<>(supplyDocuments.count(supplyDocumentsDB), HttpStatus.OK);
    }
    @PostMapping ("/find")
    public ResponseEntity<Float> find(@RequestBody PricesDB pricesDB) {
        System.out.println("Выполняется поиск цены...");
        Prices prices = new PricesImplemented();
        return new ResponseEntity<>(prices.getForMatSup(pricesDB), HttpStatus.OK);
    }
}
