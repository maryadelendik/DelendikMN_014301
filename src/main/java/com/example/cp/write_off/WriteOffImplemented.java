package com.example.cp.write_off;


import com.aspose.words.FindReplaceDirection;
import com.aspose.words.FindReplaceOptions;
import com.example.cp.DatabaseConnection;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.prices.Prices;
import com.example.cp.prices.PricesDB;
import com.example.cp.prices.PricesImplemented;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.Report;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WriteOffImplemented implements WriteOff {

    Connection connection = DatabaseConnection.getConnection();
    java.time.LocalDate currentDate = java.time.LocalDate.now();


    @Override
    public List<WriteOffDB> getAll() {
    /*   List<WriteOffDB> writeOffDB;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT * FROM supply_documents";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            writeOffDB = new ArrayList<>();

            while (resultSet.next()) {
                WriteOffDB writeOffDBs = new WriteOffDB();
                writeOffDBs.setId(resultSet.getInt("id"));
                writeOffDBs.setNumber(resultSet.getString("number"));
                writeOffDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    writeOffDBs.setMaterial(resultSet2.getString(1));
                    writeOffDBs.setSupplier(resultSet2.getString(2));
                }
                writeOffDBs.setQuantity(resultSet.getInt("quantity"));

                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                writeOffDBs.setDate(result);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                //supplyDocumentsDBs.setDate(String.format(resultSet.getString("date"), formatter));
               // System.out.println(LocalDate.parse(resultSet.getString("date"), formatter));
                //supplyDocumentsDBs.setDate(String.valueOf(LocalDate.parse(resultSet.getString("date"), DateTimeFormatter.BASIC_ISO_DATE)));
                //supplyDocumentsDBs.setDate(resultSet.getString("date"));
                writeOffDBs.setPrice(resultSet.getFloat("price"));
                writeOffDB.add(writeOffDBs);
            }
            writeOffDB.sort(Comparator.comparing(WriteOffDB::getId).reversed());
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return writeOffDB;*/
        return null;
    }

    @Override
    public List<WriteOffDB> search(String string) {
    /*    List<WriteOffDB> writeOffDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id FROM warehouse.material_supplier\n" +
                    "WHERE supplier IN (SELECT id FROM warehouse.suppliers where name like '%"+string+"%') OR material IN\n" +
                    "(SELECT id FROM warehouse.materials where number like '%"+string+"%')) OR date like '%"+string+"%' or number like '%"+string+"%'" ;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            writeOffDB = new ArrayList<>();

            while (resultSet.next()) {
                WriteOffDB writeOffDBs = new WriteOffDB();
                writeOffDBs.setId(resultSet.getInt("id"));
                writeOffDBs.setNumber(resultSet.getString("number"));
                writeOffDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    writeOffDBs.setMaterial(resultSet2.getString(1));
                    writeOffDBs.setSupplier(resultSet2.getString(2));
                }
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                writeOffDBs.setDate(result);
                writeOffDBs.setQuantity(resultSet.getInt("quantity"));
          //      supplyDocumentsDBs.setDate(resultSet.getString("date"));
                writeOffDBs.setPrice(resultSet.getFloat("price"));
                writeOffDB.add(writeOffDBs);
            }
            writeOffDB.sort(Comparator.comparing(WriteOffDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return writeOffDB;*/
        return null;
    }

    public String get_lot_name (Integer id){
       String name = null;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT lot FROM warehouse.supply_documents WHERE id ='"+id+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                name = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    @Override
    public List<WriteOffDB> getLots(Integer id) {
        List<WriteOffDB> writeOffDB;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT * FROM warehouse.write_off WHERE production_order = "+id;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            writeOffDB = new ArrayList<>();
            while (resultSet.next()) {
                WriteOffDB writeOffDBS = new WriteOffDB();
                writeOffDBS.setId(resultSet.getInt("id"));
                writeOffDBS.setLot_material(resultSet.getInt("lot_material"));
                writeOffDBS.setQuantity(resultSet.getInt("quantity"));
                writeOffDBS.setProduction_order(resultSet.getInt("production_order"));
                writeOffDBS.setDate(resultSet.getString("date"));
                writeOffDBS.setPrice_item(resultSet.getFloat("price_item"));
                writeOffDBS.setTotal_price(resultSet.getFloat("total_price"));
                writeOffDBS.setReject(resultSet.getInt("reject"));
                writeOffDBS.setType(resultSet.getString("type"));
                writeOffDBS.setLot(get_lot_name(resultSet.getInt("lot_material")));
                writeOffDB.add(writeOffDBS);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return writeOffDB;
    }

    @Override
    public void reject(WriteOffDB writeOffDB) {
        String sql = "UPDATE warehouse.write_off SET" +
                " reject = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, writeOffDB.getReject());
            preparedStatement.setInt(2, writeOffDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
            String sql  = "DELETE FROM warehouse.write_off " +
                "WHERE production_order = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer findIdMaterial(String number) {
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM materials WHERE number='"+number+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    @Override
    public Integer EachWriteOff(List<SupplyDocumentsDB> supplyDocumentsDB) {
        Integer stock_quantity = null, order_quantity = null;
        ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
        productionOrdersDB.setNeed_quantity(supplyDocumentsDB.get(1).getQuantity());
        productionOrdersDB.setMaterial(findIdMaterial(supplyDocumentsDB.get(1).getMaterial()));
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT stock_quantity, order_quantity FROM materials WHERE number='"+supplyDocumentsDB.get(1).getMaterial()+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                stock_quantity = resultSet.getInt(1);
                order_quantity = resultSet.getInt(2);
                if (stock_quantity < supplyDocumentsDB.get(1).getQuantity()) {
                    return 0;
                } else {
                    if (stock_quantity - supplyDocumentsDB.get(1).getQuantity() < 10) {
                        each(supplyDocumentsDB);
                        update_material_stock(productionOrdersDB);
                        set_status_write_off(supplyDocumentsDB.get(1).getProd_order());
                        return 2;
                    } else {
                        each(supplyDocumentsDB);
                        update_material_stock(productionOrdersDB);
                        set_status_write_off(supplyDocumentsDB.get(1).getProd_order());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public void save(WriteOffDB writeOffDB) {
        String sqlRequest = "INSERT INTO write_off (lot_material, quantity, production_order, date, price_item, total_price, type) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, writeOffDB.getLot_material());
            statement.setInt(2, writeOffDB.getQuantity());
            statement.setInt(3, writeOffDB.getProduction_order());
            statement.setString(4, writeOffDB.getDate());
            statement.setFloat(5, writeOffDB.getPrice_item());
            statement.setFloat(6, writeOffDB.getTotal_price());
            statement.setString(7, writeOffDB.getType());
            if (writeOffDB.getQuantity() > 0) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
  /*  public Integer findIdMaterial(String number) {
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM materials WHERE number='"+number+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }*/
    @Override
    public void update(WriteOffDB writeOffDB) {/*
        String sqlRequest = "UPDATE supply_documents SET number = ?, mat_sup = ?, quantity = ?, date = ?, price = ? " +
                " WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, writeOffDB.getNumber());

            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+ writeOffDB.getMaterial()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE name = '"+ writeOffDB.getSupplier()+"')";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                statement.setInt(2, resultSet22.getInt(1));
            }

            statement.setInt(3, writeOffDB.getQuantity());
            statement.setString(4, writeOffDB.getDate());
            statement.setFloat(5, writeOffDB.getPrice());
            statement.setInt(6, writeOffDB.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public Float count(WriteOffDB writeOffDB) {/*
        PricesDB pricesDB = new PricesDB();
        pricesDB.setSupplier(findIdSupplier(writeOffDB.getSupplier()));
        pricesDB.setMat_name(writeOffDB.getMaterial());
        Prices prices = new PricesImplemented();
        System.out.println(writeOffDB.getSupplier());
        System.out.println(writeOffDB.getMaterial());
        System.out.println(findIdSupplier(writeOffDB.getSupplier()));
        return writeOffDB.getQuantity()*prices.getForMatSup(pricesDB);*/
        return null;
    }
    @Override
    public ArrayList<ReportWO> suppliersReport(ReportWO reportWO) {
        ArrayList<ReportWO> report = new ArrayList<ReportWO>();
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT S.name, SD.quantity, ROUND(AVG(SD.price_item),2), SUM(WO.reject)  FROM warehouse.supply_documents SD INNER JOIN \n" +
                    "warehouse.material_supplier MS ON MS.id=SD.mat_sup INNER JOIN\n" +
                    "warehouse.write_off WO ON WO.lot_material=SD.id INNER JOIN\n" +
                    "warehouse.suppliers S ON MS.supplier=S.id WHERE MS.material = (SELECT id FROM warehouse.materials WHERE number = '"+reportWO.getMaterial()+"') \n" +
                    "AND (SD.date BETWEEN '"+reportWO.getDate_from()+"' AND '"+reportWO.getDate_to()+"') GROUP BY mat_sup;";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                ReportWO report_fill = new ReportWO();
                report_fill.setSupplier(resultSet.getString(1));
                report_fill.setTotal_quantity(resultSet.getInt(2));
                report_fill.setAvg_item_price(resultSet.getFloat(3));
                report_fill.setRejected(resultSet.getInt(4));
                report_fill.setPercent( String.format("%.2f", (float) resultSet.getInt(4) /resultSet.getInt(2) * 100).replace(',', '.') + " %");
                report.add(report_fill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    @Override
    public ArrayList<ReportWO> ABCReport(ReportWO reportWO) {
        ArrayList<ReportWO> report = new ArrayList<ReportWO>();
        Float total = (float) 0;
        try {
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            String total_quantity = "SELECT SUM(quantity) FROM warehouse.write_off WHERE \n" +
                    "(date BETWEEN '"+reportWO.getDate_from()+"' AND '"+reportWO.getDate_to()+"');";
            String sqlResponse = "SELECT M.number, SUM(WO.quantity) FROM warehouse.write_off WO INNER JOIN \n" +
                    "warehouse.supply_documents SD ON SD.id=WO.lot_material INNER JOIN\n" +
                    "warehouse.material_supplier MS ON MS.id=SD.mat_sup INNER JOIN\n" +
                    "warehouse.materials M ON M.id=MS.material WHERE \n" +
                    "(WO.date BETWEEN '"+reportWO.getDate_from()+"' AND '"+reportWO.getDate_to()+"') GROUP BY M.number;";
            ResultSet resultSet2 = statement2.executeQuery(total_quantity);
            while (resultSet2.next()) {
                total = (float) resultSet2.getInt(1);
            }
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                ReportWO report_fill = new ReportWO();
                report_fill.setMaterial(resultSet.getString(1));
                report_fill.setTotal_quantity(resultSet.getInt(2));
                report_fill.setPercent(String.format("%.2f", (float) resultSet.getInt(2) /total*100).replace(',', '.')+" %");
                report_fill.setAvg_item_price( Float.parseFloat(String.format("%.2f", (float) resultSet.getInt(2) /total*100).replace(',', '.')));
                report.add(report_fill);
                report.sort(Comparator.comparing(ReportWO::getAvg_item_price).reversed());
            }
            Float total_percent = (float) 0;
            for (int i = 0; i<report.size();i++){
                total_percent += report.get(i).getAvg_item_price();
                report.get(i).setTotal_perc(String.format("%.2f", total_percent).replace(',', '.')+" %");
                if(i == report.size() - 1){
                    report.get(i).setTotal_perc("100 %");
                }
                if (total_percent<80) {
                    report.get(i).setAbc("A");
                } else if (total_percent>=80 && total_percent<=95) {
                    report.get(i).setAbc("B");
                } else if (total_percent>95) {
                    report.get(i).setAbc("C");
                }
            }
          report.sort(Comparator.comparing(ReportWO::getAvg_item_price).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    @Override
    public void ABCXlsx(ReportWO reportWO) {
        ArrayList<ReportWO> report = ABCReport(reportWO);
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Лист 1");
            CellStyle centerAlignStyle = workbook.createCellStyle();
            centerAlignStyle.setAlignment(HorizontalAlignment.CENTER);
            Object[][] data = new Object[report.size()+1][5];
            data[0][0] = "Материал";
            data[0][1] = "Количество";
            data[0][2] = "Класс";
            data[0][3] = "Доля от общего кол-ва";
            data[0][4] = "Совокупный процент";
            for (int i=1; i< report.size()+1; i++){
                data[i][0] = report.get(i-1).getMaterial();
                data[i][1] = report.get(i-1).getTotal_quantity();
                data[i][2] = report.get(i-1).getAbc();
                data[i][3] = report.get(i-1).getPercent();
                data[i][4] = report.get(i-1).getTotal_perc();
            }

            int rowNum = 0;
            for (Object[] rowData : data) {
                XSSFRow row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : rowData) {
                    XSSFCell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellStyle(centerAlignStyle);
                        cell.setCellValue((Integer) field);

                    }
                }
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            try (FileOutputStream outputStream = new FileOutputStream("ABC анализ.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SuppliersXlsx(ReportWO reportWO) {
        ArrayList<ReportWO> report = suppliersReport(reportWO);
        System.out.println(report);
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Лист 1");
            CellStyle centerAlignStyle = workbook.createCellStyle();
            centerAlignStyle.setAlignment(HorizontalAlignment.CENTER);
            Object[][] data = new Object[report.size()+1][5];
            data[0][0] = "Поставщик";
            data[0][1] = "Общее количество материала";
            data[0][2] = "Средняя стоимость за единицу";
            data[0][3] = "Общее количество брака";
            data[0][4] = "Процент брака";
            for (int i=1; i< report.size()+1; i++){
                data[i][0] = report.get(i-1).getSupplier();
                data[i][1] = report.get(i-1).getTotal_quantity();
                data[i][2] = String.valueOf(report.get(i-1).getAvg_item_price());
                data[i][3] = report.get(i-1).getRejected();
                data[i][4] = report.get(i-1).getPercent();
            }
            int rowNum = 0;
            for (Object[] rowData : data) {
                XSSFRow row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : rowData) {
                    XSSFCell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                        cell.setCellStyle(centerAlignStyle);
                    }
                }
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            try (FileOutputStream outputStream = new FileOutputStream("Анализ поставщиков.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer findIdSupplier(String name) {/*
        int id = 0;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT id FROM suppliers WHERE name='"+name+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;*/
        return null;
    }
     public void fifo (ProductionOrdersDB productionOrdersDB){
         Integer stock_quantity = null, need = productionOrdersDB.getNeed_quantity();
         String find_supplies ="SELECT * FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id " +
                 "FROM warehouse.material_supplier WHERE material = "+ productionOrdersDB.getMaterial() +" ) AND " +
                 "current_stock > 0 ORDER BY date";
         try {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(find_supplies);
             while (resultSet.next() && need > 0) {
                 stock_quantity = resultSet.getInt(10);
                 if(stock_quantity<need){
                     WriteOffDB writeOffDB = new WriteOffDB();
                     writeOffDB.setLot_material(resultSet.getInt(1));
                     writeOffDB.setQuantity(stock_quantity);
                     writeOffDB.setProduction_order(productionOrdersDB.getId());
                     writeOffDB.setDate(String.valueOf(currentDate));
                     writeOffDB.setPrice_item(resultSet.getFloat(7));
                     writeOffDB.setTotal_price(Float.parseFloat(String.format("%.2f", resultSet.getFloat(7)*stock_quantity).replace(',', '.')));
                     writeOffDB.setType("FIFO");
                     save(writeOffDB);
                     update_supply_document(resultSet.getInt(1),stock_quantity);
                     need -= stock_quantity;
                 } else {
                     WriteOffDB writeOffDB = new WriteOffDB();
                     writeOffDB.setLot_material(resultSet.getInt(1));
                     writeOffDB.setQuantity(need);
                     writeOffDB.setProduction_order(productionOrdersDB.getId());
                     writeOffDB.setDate(String.valueOf(currentDate));
                     writeOffDB.setPrice_item(resultSet.getFloat(7));
                     writeOffDB.setTotal_price(Float.parseFloat(String.format("%.2f", resultSet.getFloat(7)*need).replace(',', '.')));
                     writeOffDB.setType("FIFO");
                     save(writeOffDB);
                     update_supply_document(resultSet.getInt(1),need);
                     need -= stock_quantity;
                 }
             }
         } catch (SQLException e) {
         throw new RuntimeException(e);
         }
     }
    public void mid (ProductionOrdersDB productionOrdersDB){
        Integer stock_quantity = null, need = productionOrdersDB.getNeed_quantity();
        Float price_item = get_mid_price(productionOrdersDB.getMaterial());
        String find_supplies ="SELECT * FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id " +
                "FROM warehouse.material_supplier WHERE material = "+ productionOrdersDB.getMaterial() +" ) AND " +
                "current_stock > 0 ORDER BY date";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(find_supplies);
            while (resultSet.next() && need > 0) {
                stock_quantity = resultSet.getInt(10);
                if(stock_quantity<need){
                    WriteOffDB writeOffDB = new WriteOffDB();
                    writeOffDB.setLot_material(resultSet.getInt(1));
                    writeOffDB.setQuantity(stock_quantity);
                    writeOffDB.setProduction_order(productionOrdersDB.getId());
                    writeOffDB.setDate(String.valueOf(currentDate));
                    writeOffDB.setPrice_item(price_item);
                    writeOffDB.setTotal_price(Float.parseFloat(String.format("%.2f", price_item*stock_quantity).replace(',', '.')));
                    writeOffDB.setType("По средней себестоимости");
                    save(writeOffDB);
                    update_supply_document(resultSet.getInt(1),stock_quantity);
                    need -= stock_quantity;
                } else {
                    WriteOffDB writeOffDB = new WriteOffDB();
                    writeOffDB.setLot_material(resultSet.getInt(1));
                    writeOffDB.setQuantity(need);
                    writeOffDB.setProduction_order(productionOrdersDB.getId());
                    writeOffDB.setDate(String.valueOf(currentDate));
                    writeOffDB.setPrice_item(price_item);
                    writeOffDB.setTotal_price(Float.parseFloat(String.format("%.2f", price_item*need).replace(',', '.')));
                    writeOffDB.setType("По средней себестоимости");
                    save(writeOffDB);
                    update_supply_document(resultSet.getInt(1),need);
                    need -= stock_quantity;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void each (List<SupplyDocumentsDB> supplyDocumentsDB){
        Integer stock_quantity = null;
        Float price_item = null;
        for (int i = 0; i < supplyDocumentsDB.size(); i++){
            String find_price ="SELECT price_item FROM warehouse.supply_documents WHERE id = " + supplyDocumentsDB.get(i).getId();
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(find_price);
                while (resultSet.next()) {
                 price_item = resultSet.getFloat(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            WriteOffDB writeOffDB = new WriteOffDB();
            writeOffDB.setLot_material(supplyDocumentsDB.get(i).getId());
            writeOffDB.setQuantity(supplyDocumentsDB.get(i).getWrite_off());
            writeOffDB.setProduction_order(supplyDocumentsDB.get(i).getProd_order());
            writeOffDB.setDate(String.valueOf(currentDate));
            writeOffDB.setPrice_item(price_item);
            writeOffDB.setTotal_price(Float.parseFloat(String.format("%.2f", price_item*supplyDocumentsDB.get(i).getWrite_off()).replace(',', '.')));
            writeOffDB.setType("По себестоимости каждой единицы");
            save(writeOffDB);
            update_supply_document(supplyDocumentsDB.get(i).getId(),supplyDocumentsDB.get(i).getWrite_off());
        }
    }
    public Float get_mid_price (Integer id){
        Float price = null;
        String month = "SELECT SUM(price), SUM(quantity) FROM warehouse.supply_documents " +
                "WHERE mat_sup IN (SELECT id FROM warehouse.material_supplier WHERE " +
                "material = " + id + ") AND MONTH(`date`) = MONTH(NOW());";
        String leftovers = "SELECT ROUND(SUM(price_item*month_leftovers),2), SUM(month_leftovers) " +
                "FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id FROM warehouse.material_supplier " +
                "WHERE material = " + id + ") AND month_leftovers > 0 ;";
        try {
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(month);
            ResultSet resultSet2 = statement2.executeQuery(leftovers);
            while (resultSet.next() ) {
                while (resultSet2.next()) {
                    price = (resultSet.getFloat(1) + resultSet2.getFloat(1)) /
                            (resultSet.getFloat(2) + resultSet2.getFloat(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Float.parseFloat(String.format("%.2f", price).replace(',', '.'));
    }
    public void update_material_stock (ProductionOrdersDB productionOrdersDB){
        String write_off = "UPDATE materials SET" +
                " stock_quantity = stock_quantity - ?," +
                " order_quantity = order_quantity - ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(write_off);
            preparedStatement.setInt(1, productionOrdersDB.getNeed_quantity());
            preparedStatement.setInt(2, productionOrdersDB.getNeed_quantity());
            preparedStatement.setInt(3, productionOrdersDB.getMaterial());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update_supply_document (Integer id, Integer quantity){
        String sql = "UPDATE warehouse.supply_documents SET" +
                " current_stock = current_stock - ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void back_supply_document (ProductionOrdersDB productionOrdersDB){
        String find_supplies = "SELECT lot_material, quantity FROM warehouse.write_off " +
                "WHERE production_order = "+productionOrdersDB.getId();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(find_supplies);
            while (resultSet.next() ) {
                String sql = "UPDATE warehouse.supply_documents SET" +
                    " current_stock = current_stock + ?" +
                    " WHERE id = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, resultSet.getInt(2));
                    preparedStatement.setInt(2, resultSet.getInt(1));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void set_status_write_off (Integer id){
        String sql = "UPDATE production_orders SET" +
                " status = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void choice (Integer type, ProductionOrdersDB productionOrdersDB){
        if(type == 1){
            fifo(productionOrdersDB);
        } else if (type == 2) {
            mid(productionOrdersDB);
        }
    }
    @Override
    public Integer WriteOff(ProductionOrdersDB productionOrdersDB, Integer type) {
        Integer stock_quantity = null, order_quantity = null;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT stock_quantity, order_quantity FROM materials WHERE id='"+productionOrdersDB.getMaterial()+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                stock_quantity = resultSet.getInt(1);
                order_quantity = resultSet.getInt(2);

                if (stock_quantity < productionOrdersDB.getNeed_quantity()) {
                    return 0;
                } else {
                    if (stock_quantity - productionOrdersDB.getNeed_quantity() < 10) {
                        choice(type, productionOrdersDB);
                        update_material_stock(productionOrdersDB);
                        set_status_write_off(productionOrdersDB.getId());
                        return 2;
                    } else {
                        choice(type, productionOrdersDB);
                        update_material_stock(productionOrdersDB);
                        set_status_write_off(productionOrdersDB.getId());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
    @Override
    public void backWriteOff(ProductionOrdersDB productionOrdersDB) {
        Integer stock_quantity = null, order_quantity = null;
        String sql = "UPDATE production_orders SET" +
                " status = ?, reject_quantity = ?" +
                " WHERE id = ?";
        String back_write_off = "UPDATE materials SET" +
                " stock_quantity = ?," +
                " order_quantity = ?" +
                " WHERE id = ?";
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT stock_quantity, order_quantity FROM materials WHERE id='"+productionOrdersDB.getMaterial()+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            back_supply_document(productionOrdersDB);
            while (resultSet.next()) {
                stock_quantity = resultSet.getInt(1);
                order_quantity = resultSet.getInt(2);
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(back_write_off);
            preparedStatement2.setInt(1, stock_quantity+productionOrdersDB.getNeed_quantity());
            preparedStatement2.setInt(2, order_quantity+productionOrdersDB.getNeed_quantity());
            preparedStatement2.setInt(3, productionOrdersDB.getMaterial());
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, productionOrdersDB.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        deleteById(productionOrdersDB.getId());
    }

    @Override
    public void print(Integer id) throws IOException, ParseException {
        List<WriteOffDB> list = new ArrayList<WriteOffDB>();
       String date1 = null;
       Float total_price = 0f;
       String type = "";
        String month = "SELECT WO.quantity, WO.date, WO.price_item, WO.total_price, WO.type, M.name, M.number, M.unit, SD.lot FROM warehouse.write_off WO INNER JOIN \n" +
                "warehouse.supply_documents SD ON SD.id=WO.lot_material INNER JOIN\n" +
                "warehouse.material_supplier MS ON MS.id=SD.mat_sup INNER JOIN\n" +
                "warehouse.materials M ON M.id=MS.material WHERE \n" +
                "WO.production_order = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(month);
            while (resultSet.next() ) {
                WriteOffDB writeOffDB = new WriteOffDB();
                writeOffDB.setQuantity(resultSet.getInt(1));
                writeOffDB.setPrice_item(resultSet.getFloat(3));
                writeOffDB.setTotal_price(resultSet.getFloat(4));
                writeOffDB.setLot(resultSet.getString(9));
                writeOffDB.setName_material(resultSet.getString(6));
                writeOffDB.setNumber_material(resultSet.getString(7));
                writeOffDB.setUnit_material(resultSet.getString(8));
              date1 = resultSet.getString(2);
              total_price += resultSet.getFloat(4);
              type = resultSet.getString(5);
              list.add(writeOffDB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        InputStream templateStream = new FileInputStream("template.docx");
        XWPFDocument document = new XWPFDocument(templateStream);

        SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        java.util.Date date = oldDateFormat.parse(date1);
        String result = newDateFormat.format(date);

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            int numberOfRuns = paragraph.getRuns().size();
            StringBuilder sb = new StringBuilder();
            for (XWPFRun r : paragraph.getRuns()){
                int pos = r.getTextPosition();
                if(r.getText(pos) != null) {
                    sb.append(r.getText(pos));
                }
            }
            if(sb.length() > 0 && sb.toString().contains("{{TABLE_PLACEHOLDER}}")) {
                for(int i = 0; i < numberOfRuns; i++) {
                    paragraph.removeRun(i);
                }
                String text = sb.toString().replace("{{TABLE_PLACEHOLDER}}", "");
                XWPFRun run = paragraph.createRun();
                run.setText(text);
                paragraph.addRun(run);
                XmlCursor cursor = paragraph.getCTP().newCursor();//this is the key!
                XWPFTable table = document.insertNewTbl(cursor);
                for (int i =0; i < 6; i++) {
                    table.addNewCol();
                }
                int heightInTwips = (int) (0.8f * 566.9);
                XWPFTableRow tableRow1 = table.getRow(0);
                tableRow1.setHeight(heightInTwips);
                XWPFTableCell cell1 = table.getRow(0).getCell(0);
                XWPFTableCell cell2 = table.getRow(0).getCell(1);
                XWPFTableCell cell3 = tableRow1.getCell(2);
                XWPFTableCell cell4 = tableRow1.getCell(3);
                XWPFTableCell cell5 = tableRow1.getCell(4);
                XWPFTableCell cell6 = tableRow1.getCell(5);
                XWPFTableCell cell7 = tableRow1.getCell(6);
                cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                paragraph.setAlignment(ParagraphAlignment.CENTER);
                cell1.setText("Наименование");
                XWPFParagraph p1 = cell1.getParagraphs().get(0);
                p1.setAlignment(ParagraphAlignment.CENTER);
                cell2.setText("Номенклатурный номер");
                XWPFParagraph p2 = cell2.getParagraphs().get(0);
                p2.setAlignment(ParagraphAlignment.CENTER);
                cell2.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell3.setText("Ед. изм.");
                XWPFParagraph p3 = cell3.getParagraphs().get(0);
                p3.setAlignment(ParagraphAlignment.CENTER);
                cell3.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell4.setText("Кол-во");
                XWPFParagraph p4 = cell4.getParagraphs().get(0);
                p4.setAlignment(ParagraphAlignment.CENTER);
                cell4.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell5.setText("Цена, руб.");
                XWPFParagraph p5 = cell5.getParagraphs().get(0);
                p5.setAlignment(ParagraphAlignment.CENTER);
                cell5.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell6.setText("Сумма, руб.");
                XWPFParagraph p6 = cell6.getParagraphs().get(0);
                p6.setAlignment(ParagraphAlignment.CENTER);
                cell6.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell7.setText("Партия");
                XWPFParagraph p7 = cell7.getParagraphs().get(0);
                p7.setAlignment(ParagraphAlignment.CENTER);
                cell7.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

                XWPFTableRow tableRow2 = table.createRow();

                tableRow2.setHeight(heightInTwips);
                XWPFTableCell cell21 = tableRow2.getCell(0);
                XWPFTableCell cell22 = tableRow2.getCell(1);
                XWPFTableCell cell23 = tableRow2.getCell(2);
                XWPFTableCell cell24 = tableRow2.getCell(3);
                XWPFTableCell cell25 = tableRow2.getCell(4);
                XWPFTableCell cell26 = tableRow2.getCell(5);
                XWPFTableCell cell27 = tableRow2.getCell(6);
                cell21.setText("1");
                XWPFParagraph p21 = cell21.getParagraphs().get(0);
                p21.setAlignment(ParagraphAlignment.CENTER);
                cell21.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell22.setText("2");
                XWPFParagraph p22 = cell22.getParagraphs().get(0);
                p22.setAlignment(ParagraphAlignment.CENTER);
                cell22.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell23.setText("3");
                XWPFParagraph p23 = cell23.getParagraphs().get(0);
                p23.setAlignment(ParagraphAlignment.CENTER);
                cell23.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell24.setText("4");
                XWPFParagraph p24 = cell24.getParagraphs().get(0);
                p24.setAlignment(ParagraphAlignment.CENTER);
                cell24.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell25.setText("5");
                XWPFParagraph p25 = cell25.getParagraphs().get(0);
                p25.setAlignment(ParagraphAlignment.CENTER);
                cell25.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell26.setText("6");
                XWPFParagraph p26 = cell26.getParagraphs().get(0);
                p26.setAlignment(ParagraphAlignment.CENTER);
                cell26.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cell27.setText("7");
                XWPFParagraph p27 = cell27.getParagraphs().get(0);
                p27.setAlignment(ParagraphAlignment.CENTER);
                cell27.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                for (int row = 2; row < list.size()+2; row++) {
                    XWPFTableRow tableRow = table.createRow();
                    tableRow.setHeight(heightInTwips);
                    XWPFTableCell cell01 = tableRow.getCell(0);
                    XWPFTableCell cell02 = tableRow.getCell(1);
                    XWPFTableCell cell03 = tableRow.getCell(2);
                    XWPFTableCell cell04 = tableRow.getCell(3);
                    XWPFTableCell cell05 = tableRow.getCell(4);
                    XWPFTableCell cell06 = tableRow.getCell(5);
                    XWPFTableCell cell07 = tableRow.getCell(6);
                    cell01.setText(list.get(row-2).getName_material());
                    cell01.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell02.setText(list.get(row-2).getNumber_material());
                    XWPFParagraph p02 = cell02.getParagraphs().get(0);
                    p02.setAlignment(ParagraphAlignment.CENTER);
                    cell02.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell03.setText(list.get(row-2).getUnit_material());
                    XWPFParagraph p03 = cell03.getParagraphs().get(0);
                    p03.setAlignment(ParagraphAlignment.CENTER);
                    cell03.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell04.setText(String.valueOf(list.get(row-2).getQuantity()));
                    XWPFParagraph p04 = cell04.getParagraphs().get(0);
                    p04.setAlignment(ParagraphAlignment.CENTER);
                    cell04.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell05.setText(String.valueOf(list.get(row-2).getPrice_item()));
                    XWPFParagraph p05 = cell05.getParagraphs().get(0);
                    p05.setAlignment(ParagraphAlignment.CENTER);
                    cell05.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell06.setText(String.valueOf(list.get(row-2).getTotal_price()));
                    XWPFParagraph p06 = cell06.getParagraphs().get(0);
                    p06.setAlignment(ParagraphAlignment.CENTER);
                    cell06.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    cell07.setText(list.get(row-2).getLot());
                    XWPFParagraph p07 = cell07.getParagraphs().get(0);
                    p07.setAlignment(ParagraphAlignment.CENTER);
                    cell07.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                }
                XWPFTableRow tableRow3 = table.createRow();
                tableRow3.setHeight(heightInTwips);
                XWPFTableCell cell31 = tableRow3.getCell(0);
                XWPFTableCell cell32 = tableRow3.getCell(1);
                XWPFTableCell cell33 = tableRow3.getCell(2);
                XWPFTableCell cell34 = tableRow3.getCell(3);
                XWPFTableCell cell35 = tableRow3.getCell(4);
                XWPFTableCell cell36 = tableRow3.getCell(5);
                cell31.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                cell32.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                cell33.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                cell34.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                cell35.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                cell31.setText("Всего");
                cell36.setText(String.valueOf(total_price));
                XWPFParagraph p36 = cell36.getParagraphs().get(0);
                cell31.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                p36.setAlignment(ParagraphAlignment.CENTER);
                cell36.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            }
        }
/*
        XWPFTable table = document.createTable(list.size()+3, 7);

        XWPFTableRow tableRow1 = table.getRow(0);
        XWPFTableCell cell1 = tableRow1.getCell(0);
        XWPFTableCell cell2 = tableRow1.getCell(1);
        XWPFTableCell cell3 = tableRow1.getCell(2);
        XWPFTableCell cell4 = tableRow1.getCell(3);
        XWPFTableCell cell5 = tableRow1.getCell(4);
        XWPFTableCell cell6 = tableRow1.getCell(5);
        XWPFTableCell cell7 = tableRow1.getCell(6);
        cell1.setText("Наименование");
        cell2.setText("Номенклатурный номер");
        cell3.setText("Ед. изм.");
        cell4.setText("Кол-во");
        cell5.setText("Цена, руб.");
        cell6.setText("Сумма, руб.");
        cell7.setText("Партия");

        XWPFTableRow tableRow2 = table.getRow(1);
        XWPFTableCell cell21 = tableRow2.getCell(0);
        XWPFTableCell cell22 = tableRow2.getCell(1);
        XWPFTableCell cell23 = tableRow2.getCell(2);
        XWPFTableCell cell24 = tableRow2.getCell(3);
        XWPFTableCell cell25 = tableRow2.getCell(4);
        XWPFTableCell cell26 = tableRow2.getCell(5);
        XWPFTableCell cell27 = tableRow2.getCell(6);
        cell21.setText("1");
        cell22.setText("2");
        cell23.setText("3");
        cell24.setText("4");
        cell25.setText("5");
        cell26.setText("6");
        cell27.setText("7");
        for (int row = 2; row < list.size()+2; row++) {
            XWPFTableRow tableRow = table.getRow(row);
            XWPFTableCell cell01 = tableRow.getCell(0);
            XWPFTableCell cell02 = tableRow.getCell(1);
            XWPFTableCell cell03 = tableRow.getCell(2);
            XWPFTableCell cell04 = tableRow.getCell(3);
            XWPFTableCell cell05 = tableRow.getCell(4);
            XWPFTableCell cell06 = tableRow.getCell(5);
            XWPFTableCell cell07 = tableRow.getCell(6);
            cell01.setText(list.get(row-2).getName_material());
            cell02.setText(list.get(row-2).getNumber_material());
            cell03.setText(list.get(row-2).getUnit_material());
            cell04.setText(String.valueOf(list.get(row-2).getQuantity()));
            cell05.setText(String.valueOf(list.get(row-2).getPrice_item()));
            cell06.setText(String.valueOf(list.get(row-2).getTotal_price()));
            cell07.setText(list.get(row-2).getLot());
        }

        XWPFTableRow tableRow3 = table.getRow(list.size()+2);
        XWPFTableCell cell31 = tableRow3.getCell(0);
        XWPFTableCell cell35 = tableRow3.getCell(5);
        cell31.setText("Всего");
        cell35.setText(String.valueOf(total_price));

        int pos1 = 0;
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            int numberOfRuns = paragraph.getRuns().size();
            StringBuilder sb = new StringBuilder();
            for (XWPFRun r : paragraph.getRuns()){
                int pos = r.getTextPosition();
                if(r.getText(pos) != null) {
                    sb.append(r.getText(pos));
                }
            }
            if(sb.length() > 0 && sb.toString().contains("{{TABLE_PLACEHOLDER}}")) {
                for(int i = 0; i < numberOfRuns; i++) {
                    paragraph.removeRun(i);
                }
                String text = sb.toString().replace("{{TABLE_PLACEHOLDER}}", "");
                XWPFRun run = paragraph.createRun();
                run.setText(text);
                paragraph.addRun(run);
                XmlCursor cursor = paragraph.getCTP().newCursor();//this is the key!
                table = document.insertNewTbl(cursor);
             //   XWPFTableCell cell = t2.getRow(0).getCell(0);
             //   cell.setText("GOAL!!!");
              //  XmlCursor cursor = p.getCTP().newCursor();
             //   paragraph.getBody().insertTable(pos1,table);
            //    document.removeBodyElement(pos1);
            //    document.insertTable(pos1,table);
            }
        }

*/

        for (int i = 0; i < document.getParagraphs().size(); i++) {
            XWPFParagraph paragraph = document.getParagraphs().get(i);
            replacePlaceholder(paragraph, "NUMBER", String.valueOf(id));
            replacePlaceholder(paragraph, "DATE", result);
            replacePlaceholder(paragraph, "TYPE", type);
            replacePlaceholder(paragraph, "TOTAL", String.valueOf(total_price));
        }
          /*  List<XWPFRun> runs = paragraph.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String text = run.getText(0);
                if (text != null && text.contains("{{TABLE_PLACEHOLDER}}")) {
                    text = text.replace("{{TABLE_PLACEHOLDER}}", "");
                    run.setText(text, 0);
                    int pos = document.getPosOfParagraph(paragraph);
                    document.removeBodyElement(pos);
                    document.setTable(pos, table);
                }
            }
        }*/
        FileOutputStream outputStream = new FileOutputStream("Акт списания материалов " + id + ".docx");
        document.write(outputStream);
        outputStream.close();
        document = new XWPFDocument(new FileInputStream("Акт списания материалов " + id + ".docx"));
    }
    private static void replacePlaceholder(XWPFParagraph paragraph, String placeholder, String replacement) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null && text.contains(placeholder)) {
                text = text.replace( placeholder , replacement);
                run.setText(text, 0);
            }
        }
    }
}
