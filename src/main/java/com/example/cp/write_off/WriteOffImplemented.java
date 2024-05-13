package com.example.cp.write_off;


import com.example.cp.DatabaseConnection;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.prices.Prices;
import com.example.cp.prices.PricesDB;
import com.example.cp.prices.PricesImplemented;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.Report;
import com.example.cp.supply_documents.SupplyDocumentsDB;

import javax.persistence.criteria.CriteriaBuilder;
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
                report_fill.setPercent( String.format("%.2f", (float) resultSet.getInt(4) /resultSet.getInt(2) *100).replace(',', '.') + " %");
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
}
