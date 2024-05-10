package com.example.cp.write_off;



import com.example.cp.production_orders.ProductionOrdersDB;

import java.util.List;

public interface WriteOff {

    List<WriteOffDB> getAll();
    List<WriteOffDB> search(String string);

    void deleteById(Integer id);
    Integer WriteOff(ProductionOrdersDB productionOrdersDB, Integer type);
    void save(WriteOffDB writeOffDB);

    void update(WriteOffDB writeOffDB);
    Float count(WriteOffDB writeOffDB);
}
