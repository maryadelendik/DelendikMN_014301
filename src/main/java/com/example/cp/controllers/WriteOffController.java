package com.example.cp.controllers;

import com.example.cp.production_orders.ProductionOrders;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.ProductionOrdersImplemented;
import com.example.cp.production_orders.SearchProductionOrders;
import com.example.cp.write_off.WriteOff;
import com.example.cp.write_off.WriteOffImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Integer> EachWriteOff(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется списание материала по себестоимости каждой единицы...");
        WriteOff writeOff = new WriteOffImplemented();
        return new ResponseEntity<>(writeOff.WriteOff(productionOrdersDB, 3), HttpStatus.OK);
    }
}
