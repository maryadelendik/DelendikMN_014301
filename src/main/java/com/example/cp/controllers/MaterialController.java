package com.example.cp.controllers;

import com.example.cp.authorization.Authorization;
import com.example.cp.materials.Materials;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsImplemented;
import com.example.cp.materials.SearchByType;
import com.example.cp.suppliers.Suppliers;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.suppliers.SuppliersImplemented;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/material")
public class MaterialController {
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<MaterialsDB> deleteMaterial(@PathVariable Integer id) {
        System.out.println("Выполняется удаление материала...");
        Materials materials = new MaterialsImplemented();
        materials.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   /* @GetMapping ("/delete/{id}")
    public ResponseEntity<MaterialsDB> deleteMaterial(@PathVariable Integer id) {
        System.out.println("Выполняется удаление материала...");
        Materials materials = new MaterialsImplemented();
        materials.deleteById(id);
     return new ResponseEntity<>(HttpStatus.OK);
    }*/
    @GetMapping ("/check_if_exists/{number}")
    public ResponseEntity<Boolean> checkIfExists(@PathVariable String number) {
        System.out.println("Выполняется проверка наличия материала...");
        Materials materials = new MaterialsImplemented();
        return new ResponseEntity<>(materials.checkIfExists(number), HttpStatus.OK);
    }
    @GetMapping ("/view_all")
    public ResponseEntity<List<MaterialsDB>> viewAll() {
        System.out.println("Запрос к БД на получение информации о материалах...");
        Materials materials = new MaterialsImplemented();
        List<MaterialsDB> materialsDB = materials.getAll();
        return new ResponseEntity<>(materialsDB, HttpStatus.OK);
    }
    @GetMapping ("/find_types")
    public ResponseEntity<HashSet<String>> findTypes() {
        System.out.println("Выполняется поиск типов...");
        Materials materials = new MaterialsImplemented();
        return new ResponseEntity<>(materials.findTypes(), HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<HashSet<String>> addMaterial(@RequestBody MaterialsDB materialsDB) {
        System.out.println("Выполняется добавление материала...");
        Materials materials = new MaterialsImplemented();
        materials.save(materialsDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping ("/find_materials")
    public ResponseEntity<HashSet<String>> findMaterials() {
        System.out.println("Выполняется поиск материалов...");
        Materials materials = new MaterialsImplemented();
        return new ResponseEntity<>(materials.findMaterials(), HttpStatus.OK);
    }
    @PostMapping ("/update")
    public ResponseEntity<HashSet<String>> updateMaterial(@RequestBody  MaterialsDB materialsDB) {
        System.out.println("Выполняется обновление материала...");
        Materials materials = new MaterialsImplemented();
        materials.update(materialsDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping ("/check_for_update")
    public ResponseEntity<Boolean> checkForUpdate(@RequestBody MaterialsDB materialsDB) {
        System.out.println("Выполняется проверка материалов...");
        Materials materials = new MaterialsImplemented();
        return new ResponseEntity<>(materials.checkForUpdate(materialsDB), HttpStatus.OK);
    }
    @PostMapping ("/search")
    public ResponseEntity<List<MaterialsDB>> search(@RequestBody SearchByType searchByType) {
        System.out.println("Выполняется поиск материалов...");
        Materials materials = new MaterialsImplemented();
        List<MaterialsDB> materialsDB = materials.search(searchByType.getString(), searchByType.getHas_type(),
                searchByType.getType());
        return new ResponseEntity<>(materialsDB, HttpStatus.OK);
    }
    @GetMapping ("/view_by_type")
    public ResponseEntity<List<MaterialsDB>> viewByType(@RequestParam String type) {
        System.out.println("Запрос к БД на получение информации о материалах..." );
        Materials materials = new MaterialsImplemented();
        List<MaterialsDB> materialsDB = materials.getAllByType(type);
        return new ResponseEntity<>(materialsDB, HttpStatus.OK);
    }
}
