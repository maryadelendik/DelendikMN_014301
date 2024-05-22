package com.example.cp.controllers;

import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.materials.SearchByType;
import com.example.cp.production_orders.ProductionOrders;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.ProductionOrdersImplemented;
import com.example.cp.production_orders.SearchProductionOrders;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.supply_documents.SupplyDocuments;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/production_order")
public class ProductionOrderController {
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ProductionOrdersDB> deleteProductionOrder(@PathVariable Integer id) {
        System.out.println("Выполняется удаление заказа отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        productionOrders.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   /* @GetMapping ("/delete/{id}")
    public ResponseEntity<ProductionOrdersDB> deleteProductionOrder(@PathVariable Integer id) {
        System.out.println("Выполняется удаление заказа отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        productionOrders.deleteById(id);
     return new ResponseEntity<>(HttpStatus.OK);
    }*/
    @GetMapping ("/view_all")
    public ResponseEntity<List<ProductionOrdersDB>> viewAll() {
        System.out.println("Запрос к БД на получение информации о запросах отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        List<ProductionOrdersDB> productionOrdersDB = productionOrders.getAll();
        return new ResponseEntity<>(productionOrdersDB, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addProductionOrder(@RequestBody  ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется добавление заказа отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        productionOrders.save(productionOrdersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/search")
    public ResponseEntity<List<ProductionOrdersDB>> search(@RequestBody SearchProductionOrders searchProductionOrders) {
        System.out.println("Выполняется поиск заказов отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        List<ProductionOrdersDB> productionOrdersDB = productionOrders.search(searchProductionOrders);
        return new ResponseEntity<>(productionOrdersDB, HttpStatus.OK);
    }
    @PostMapping ("/update")
    public ResponseEntity<String> updateProductionOrder(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется обновление заказа отдела производства...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        productionOrders.update(productionOrdersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/write_off")
    public ResponseEntity<Integer> writeOff(@RequestBody ProductionOrdersDB productionOrdersDB) {
        System.out.println("Выполняется списание материала...");
        ProductionOrders productionOrders = new ProductionOrdersImplemented();
        return new ResponseEntity<>(productionOrders.writeOff(productionOrdersDB), HttpStatus.OK);
    }
}
