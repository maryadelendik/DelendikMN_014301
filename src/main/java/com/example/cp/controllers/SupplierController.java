package com.example.cp.controllers;

import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.suppliers.Suppliers;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.suppliers.SuppliersImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @GetMapping ("/delete/{id}")
    public ResponseEntity<SuppliersDB> deleteSupplier(@PathVariable Integer id) {
        System.out.println("Выполняется удаление поставщика...");
        Suppliers suppliers = new SuppliersImplemented();
        suppliers.deleteById(id);
     return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/view_all")
    public ResponseEntity<List<SuppliersDB>> viewAll() {
        System.out.println("Запрос к БД на получение информации о поставщиках...");
        Suppliers suppliers= new SuppliersImplemented();
        List<SuppliersDB> suppliersDB = suppliers.getAll();
        return new ResponseEntity<>(suppliersDB, HttpStatus.OK);
    }
    @GetMapping ("/find_suppliers")
    public ResponseEntity<HashSet<String>> findTypes() {
        System.out.println("Выполняется поиск поставщиков...");
        Suppliers suppliers = new SuppliersImplemented();
        return new ResponseEntity<>(suppliers.findSuppliers(), HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<HashSet<String>> addSupplier(@RequestBody SuppliersDB suppliersDB) {
        System.out.println("Выполняется добавление поставщика...");
        Suppliers suppliers = new SuppliersImplemented();
        suppliers.save(suppliersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/check_if_exists/{name}")
    public ResponseEntity<Boolean> checkIfExists(@PathVariable String name) {
        System.out.println("Выполняется проверка наличия поставщика...");
        Suppliers suppliers = new SuppliersImplemented();
        return new ResponseEntity<>(suppliers.checkIfExists(name), HttpStatus.OK);
    }
    @PostMapping ("/update")
    public ResponseEntity<HashSet<String>> updateSupplier(@RequestBody SuppliersDB suppliersDB) {
        System.out.println("Выполняется обновление поставщика...");
        Suppliers suppliers = new SuppliersImplemented();
        suppliers.update(suppliersDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/check_for_update")
    public ResponseEntity<Boolean> checkForUpdate(@RequestBody SuppliersDB suppliersDB) {
        System.out.println("Выполняется проверка поставщиков...");
        Suppliers suppliers = new SuppliersImplemented();
        return new ResponseEntity<>(suppliers.checkForUpdate(suppliersDB), HttpStatus.OK);
    }
    @GetMapping ("/search/{string}")
    public ResponseEntity<List<SuppliersDB>> search(@PathVariable String string) {
        System.out.println("Выполняется поиск поставщиков...");
        Suppliers suppliers = new SuppliersImplemented();
        List<SuppliersDB> suppliersDB = suppliers.search(string);
        return new ResponseEntity<>(suppliersDB, HttpStatus.OK);
    }
}
