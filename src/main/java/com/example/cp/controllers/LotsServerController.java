package com.example.cp.controllers;

import com.example.cp.history.History;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryImplemented;
import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.supply_documents.SupplyDocuments;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/lots")
public class LotsServerController {

    @GetMapping ("/view/{id}")
    public ResponseEntity<List<SupplyDocumentsDB>> view(@PathVariable Integer id) {
        System.out.println("Запрос к БД на получение информации о партиях материала..." );
        SupplyDocuments supplyDocuments = new SupplyDocumentsImplemented();
        List<SupplyDocumentsDB> supplyDocumentsDB = supplyDocuments.getLots(id);
        return new ResponseEntity<>(supplyDocumentsDB, HttpStatus.OK);
    }
}
