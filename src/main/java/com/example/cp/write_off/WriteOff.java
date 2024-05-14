package com.example.cp.write_off;



import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.Report;
import com.example.cp.supply_documents.SupplyDocumentsDB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface WriteOff {

    List<WriteOffDB> getAll();
    List<WriteOffDB> search(String string);
    List<WriteOffDB> getLots(Integer id);
    void reject(WriteOffDB writeOffDB);
    void deleteById(Integer id);
    Integer EachWriteOff(List<SupplyDocumentsDB> supplyDocumentsDB);
    Integer WriteOff(ProductionOrdersDB productionOrdersDB, Integer type);
    void save(WriteOffDB writeOffDB);
    void backWriteOff(ProductionOrdersDB productionOrdersDB);
    void print(Integer id) throws IOException, ParseException;
    void update(WriteOffDB writeOffDB);
    Float count(WriteOffDB writeOffDB);
    ArrayList<ReportWO> suppliersReport(ReportWO reportWO);
    ArrayList<ReportWO> ABCReport(ReportWO reportWO);
    void ABCXlsx(ReportWO reportWO);
    void SuppliersXlsx(ReportWO reportWO);
}
