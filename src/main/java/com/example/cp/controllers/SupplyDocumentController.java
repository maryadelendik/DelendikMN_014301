package com.example.cp.controllers;

import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
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
@RequestMapping("/supply_document")
public class SupplyDocumentController {
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<SupplyDocumentsDB> deleteSupplyDocument(@PathVariable Integer id) {
        System.out.println("Выполняется удаление документа поставки...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        supplyDocuments.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping ("/delete/{id}")
//    public ResponseEntity<SupplyDocumentsDB> deleteSupplyDocument(@PathVariable Integer id) {
//        System.out.println("Выполняется удаление документа поставки...");
//        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
//        supplyDocuments.deleteById(id);
//     return new ResponseEntity<>(HttpStatus.OK);
//    }
    @GetMapping ("/view_all")
    public ResponseEntity<List<SupplyDocumentsDB>> viewAll() {
        System.out.println("Запрос к БД на получение информации о документах поставки...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        List<SupplyDocumentsDB> supplyDocumentsDB = supplyDocuments.getAll();
        return new ResponseEntity<>(supplyDocumentsDB, HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<String> addSupplyDocument(@RequestBody SupplyDocumentsDB supplyDocumentsDB) {
        System.out.println("Выполняется добавление документа поставки...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        supplyDocuments.save(supplyDocumentsDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/align")
    public ResponseEntity<String> alignLeftovers() {
        System.out.println("Выполняется выравнивание остатков...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        supplyDocuments.alignLeftovers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/search/{string}")
    public ResponseEntity<List<SupplyDocumentsDB>> search(@PathVariable String string) {
        System.out.println("Выполняется поиск документов поставки...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        List<SupplyDocumentsDB> supplyDocumentsDB = supplyDocuments.search(string);
        return new ResponseEntity<>(supplyDocumentsDB, HttpStatus.OK);
    }
    @PostMapping ("/update")
    public ResponseEntity<String> updateSupplyDocument(@RequestBody SupplyDocumentsDB supplyDocumentsDB) {
        System.out.println("Выполняется обновление документа поставки...");
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        supplyDocuments.update(supplyDocumentsDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
