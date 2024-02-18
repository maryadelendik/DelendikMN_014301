package com.example.cp.controllers;

import com.example.cp.history.History;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryImplemented;
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
@RequestMapping("/history")
public class HistoryServerController {

    @PostMapping ("/value")
    public ResponseEntity<List<HistoryDB>> historyValue(@RequestBody MaterialsDB materialsDB) {
        System.out.println("Выполняется поиск изменений...");
        Materials materials = new MaterialsImplemented();
        return new ResponseEntity<>(materials.findChanges(materialsDB), HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<HashSet<String>> addHistory(@RequestBody HistoryDB historyDB) {
        System.out.println("Выполняется добавление истории изменений...");
        History history = new HistoryImplemented();
        history.save(historyDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/view/{id}")
    public ResponseEntity<List<HistoryDB>> view(@PathVariable Integer id) {
        System.out.println("Запрос к БД на получение информации о истории изменения материала..." );
        History history = new HistoryImplemented();
        List<HistoryDB> historyDB = history.getAll(id);
        return new ResponseEntity<>(historyDB, HttpStatus.OK);
    }
}
