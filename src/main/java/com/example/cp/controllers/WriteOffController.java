package com.example.cp.controllers;

import com.example.cp.production_orders.ProductionOrders;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.ProductionOrdersImplemented;
import com.example.cp.production_orders.SearchProductionOrders;
import com.example.cp.supply_documents.SupplyDocuments;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsImplemented;
import com.example.cp.write_off.WriteOff;
import com.example.cp.write_off.WriteOffDB;
import com.example.cp.write_off.WriteOffImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/write_off")
public class WriteOffController {

    @PostMapping ("/fifo")
    public ResponseEntity<Integer> FIFOWriteOff(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется списание материала методом FIFO...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.WriteOff(productionOrdersDB, 1), HttpStatus.OK);
    }
    @PostMapping ("/mid")
    public ResponseEntity<Integer> MidWriteOff(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется списание материала по средней себестоимости...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.WriteOff(productionOrdersDB, 2), HttpStatus.OK);
    }
    @PostMapping ("/each")
    public ResponseEntity<Integer> EachWriteOff(@RequestBody List<SupplyDocumentsDB> supplyDocumentsDB) {
        System.out.println("Выполняется списание материала по себестоимости каждой единицы...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.EachWriteOff(supplyDocumentsDB), HttpStatus.OK);
    }
    @PostMapping ("/back")
    public ResponseEntity<Integer> backWriteOff(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется отмена списания материала...");
        WriteOff writeOff = new WriteOffImplemented();
        writeOff.backWriteOff(productionOrdersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/print/{id}")
    public ResponseEntity<String> print(@PathVariable Integer id) throws IOException, ParseException {
        System.out.println("Формирование акта списания материалов в производство..." );
        WriteOff writeOff = new WriteOffImplemented();
        writeOff.print(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/view/{id}")
    public ResponseEntity<List<WriteOffDB>> view(@PathVariable Integer id) {
        System.out.println("Запрос к БД на получение информации о партиях материала..." );
        WriteOff writeOff = new WriteOffImplemented();
        List<WriteOffDB> writeOffDB = writeOff.getLots(id);
        return new ResponseEntity<>(writeOffDB, HttpStatus.OK);
    }
    @PostMapping ("/add_rejected")
    public ResponseEntity<Integer> addRejected(@RequestBody WriteOffDB writeOffDB) {
        System.out.println("Выполняется обновление бракованного материала...");
        WriteOff writeOff = new WriteOffImplemented();
        writeOff.reject(writeOffDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
