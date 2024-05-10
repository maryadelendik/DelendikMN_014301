package com.example.cp.supply_documents;


import com.example.cp.DatabaseConnection;
import com.example.cp.prices.Prices;
import com.example.cp.prices.PricesDB;
import com.example.cp.prices.PricesImplemented;
import com.example.cp.production_orders.ProductionOrdersDB;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SupplyDocumentsImplemented implements SupplyDocuments {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<SupplyDocumentsDB> getAll() {
       List<SupplyDocumentsDB> supplyDocumentsDB;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT * FROM supply_documents";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            supplyDocumentsDB = new ArrayList<>();

            while (resultSet.next()) {
                SupplyDocumentsDB supplyDocumentsDBs = new SupplyDocumentsDB();
                supplyDocumentsDBs.setId(resultSet.getInt("id"));
                supplyDocumentsDBs.setNumber(resultSet.getString("number"));
                supplyDocumentsDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    supplyDocumentsDBs.setMaterial(resultSet2.getString(1));
                    supplyDocumentsDBs.setSupplier(resultSet2.getString(2));
                }
                supplyDocumentsDBs.setQuantity(resultSet.getInt("quantity"));

                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                supplyDocumentsDBs.setDate(result);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                //supplyDocumentsDBs.setDate(String.format(resultSet.getString("date"), formatter));
               // System.out.println(LocalDate.parse(resultSet.getString("date"), formatter));
                //supplyDocumentsDBs.setDate(String.valueOf(LocalDate.parse(resultSet.getString("date"), DateTimeFormatter.BASIC_ISO_DATE)));
                //supplyDocumentsDBs.setDate(resultSet.getString("date"));
                supplyDocumentsDBs.setPrice(resultSet.getFloat("price"));
                supplyDocumentsDBs.setPrice_item(resultSet.getFloat("price_item"));
                supplyDocumentsDBs.setMonth_leftovers(resultSet.getInt("month_leftovers"));
                supplyDocumentsDBs.setLot(resultSet.getString("lot"));
                supplyDocumentsDBs.setCurrent_stock(resultSet.getInt("current_stock"));
                supplyDocumentsDB.add(supplyDocumentsDBs);
            }
            supplyDocumentsDB.sort(Comparator.comparing(SupplyDocumentsDB::getId).reversed());
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return supplyDocumentsDB;
    }

    @Override
    public List<SupplyDocumentsDB> search(String string) {
        List<SupplyDocumentsDB> supplyDocumentsDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM warehouse.supply_documents WHERE mat_sup IN (SELECT id FROM warehouse.material_supplier\n" +
                    "WHERE supplier IN (SELECT id FROM warehouse.suppliers where name like '%"+string+"%') OR material IN\n" +
                    "(SELECT id FROM warehouse.materials where number like '%"+string+"%')) OR date like '%"+string+"%' or number like '%"+string+"%' " +
                    "or lot like '%"+string+"%'" ;

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            supplyDocumentsDB = new ArrayList<>();

            while (resultSet.next()) {
                SupplyDocumentsDB supplyDocumentsDBs = new SupplyDocumentsDB();
                supplyDocumentsDBs.setId(resultSet.getInt("id"));
                supplyDocumentsDBs.setNumber(resultSet.getString("number"));
                supplyDocumentsDBs.setMat_sup(resultSet.getInt("mat_sup"));

                Statement statement2 = connection.createStatement();
                String sqlResponse2 = "SELECT M.number, S.name\n" +
                        "FROM warehouse.materials M \n" +
                        "INNER JOIN warehouse.material_supplier MS ON M.id=MS.material\n" +
                        "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id\n" +
                        "WHERE MS.id="+resultSet.getInt("mat_sup");
                ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);
                while (resultSet2.next()) {
                    supplyDocumentsDBs.setMaterial(resultSet2.getString(1));
                    supplyDocumentsDBs.setSupplier(resultSet2.getString(2));
                }
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                supplyDocumentsDBs.setDate(result);
                supplyDocumentsDBs.setQuantity(resultSet.getInt("quantity"));
          //      supplyDocumentsDBs.setDate(resultSet.getString("date"));
                supplyDocumentsDBs.setPrice(resultSet.getFloat("price"));
                supplyDocumentsDBs.setPrice_item(resultSet.getFloat("price_item"));
                supplyDocumentsDBs.setMonth_leftovers(resultSet.getInt("month_leftovers"));
                supplyDocumentsDBs.setLot(resultSet.getString("lot"));
                supplyDocumentsDBs.setCurrent_stock(resultSet.getInt("current_stock"));
                supplyDocumentsDB.add(supplyDocumentsDBs);
            }
            supplyDocumentsDB.sort(Comparator.comparing(SupplyDocumentsDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return supplyDocumentsDB;
    }

    @Override
    public List<SupplyDocumentsDB> getLots(Integer id) {
        List<SupplyDocumentsDB> supplyDocumentsDB;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT lot, current_stock FROM warehouse.supply_documents WHERE mat_sup IN " +
                    "(SELECT id FROM warehouse.material_supplier WHERE material = "+id+") AND current_stock > 0";
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            supplyDocumentsDB = new ArrayList<>();
            while (resultSet.next()) {
                SupplyDocumentsDB supplyDocumentsDBs = new SupplyDocumentsDB();
                supplyDocumentsDBs.setId(0);
                //supplyDocumentsDBs.setNumber(null);
                supplyDocumentsDBs.setMat_sup(0);
                supplyDocumentsDBs.setQuantity(0);
                supplyDocumentsDBs.setPrice((float) 0);
                supplyDocumentsDBs.setPrice_item((float) 0);
                supplyDocumentsDBs.setMonth_leftovers(0);
              //  supplyDocumentsDBs.setDate(null);

                supplyDocumentsDBs.setLot(resultSet.getString("lot"));
                supplyDocumentsDBs.setCurrent_stock(resultSet.getInt("current_stock"));
                supplyDocumentsDB.add(supplyDocumentsDBs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplyDocumentsDB;
    }

    @Override
    public void deleteById(Integer id) {
        String get_material = "SELECT MS.material, D.quantity FROM warehouse.supply_documents D " +
                "INNER JOIN warehouse.material_supplier MS ON MS.id=D.mat_sup " +
                "WHERE D.id=" + id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get_material);
            while (resultSet.next()) {
                    String reduce_order_quantity = "UPDATE materials SET stock_quantity = stock_quantity - " + resultSet.getInt(2)+
                            " WHERE id = " + resultSet.getInt(1);
                    PreparedStatement preparedStatement = connection.prepareStatement(reduce_order_quantity);
                    preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql  = "DELETE FROM supply_documents " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alignLeftovers() {
        String sqlRequest = "UPDATE supply_documents SET month_leftovers = current_stock ";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(SupplyDocumentsDB supplyDocumentsDB) {
        String sqlRequest = "INSERT INTO supply_documents (number, mat_sup, quantity, date, price,lot,price_item, current_stock) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String sql = "UPDATE materials SET" +
                " stock_quantity = stock_quantity + ?" +
                " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, supplyDocumentsDB.getNumber());

            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+supplyDocumentsDB.getMaterial()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE name = '"+supplyDocumentsDB.getSupplier()+"')";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+supplyDocumentsDB.getMaterial()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE name = '"+supplyDocumentsDB.getSupplier()+"')";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                statement.setInt(2, resultSet22.getInt(1));
            }

            float price_item =supplyDocumentsDB.getPrice()/supplyDocumentsDB.getQuantity();
            statement.setInt(3, supplyDocumentsDB.getQuantity());
            statement.setString(4, supplyDocumentsDB.getDate());
            statement.setFloat(5, supplyDocumentsDB.getPrice());
            statement.setString(6, supplyDocumentsDB.getLot());
            statement.setFloat(7, Float.parseFloat(String.format("%.2f", price_item).replace(',', '.')));
            statement.setInt(8, supplyDocumentsDB.getCurrent_stock());
           // statement.setFloat(7, Float.parseFloat((new DecimalFormat("##.##").format(price_item))));
            statement.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, supplyDocumentsDB.getQuantity());
            preparedStatement.setInt(2, findIdMaterial(supplyDocumentsDB.getMaterial()));
            preparedStatement.executeUpdate();
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
    public void update(SupplyDocumentsDB supplyDocumentsDB) {
        String sqlRequest = "UPDATE supply_documents SET number = ?, mat_sup = ?, quantity = ?, date = ?, price = ? ," +
                "price_item = ?, month_leftovers = ?, lot = ?, current_stock = ?" +
                " WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, supplyDocumentsDB.getNumber());

            Statement statement2 = connection.createStatement();
            String sqlResponse2 = "SELECT id FROM warehouse.material_supplier WHERE material = " +
                    "(SELECT id FROM materials WHERE number = '"+supplyDocumentsDB.getMaterial()+"') and\n" +
                    "supplier= (SELECT id FROM suppliers WHERE name = '"+supplyDocumentsDB.getSupplier()+"')";
            ResultSet resultSet2 = statement2.executeQuery(sqlResponse2);

            if(!resultSet2.next()){
                String sqlResponse3 = "INSERT INTO warehouse.material_supplier SET material = " +
                        "(SELECT id FROM materials WHERE number = '"+supplyDocumentsDB.getMaterial()+"'),\n" +
                        "supplier= (SELECT id FROM suppliers WHERE name = '"+supplyDocumentsDB.getSupplier()+"')";
                PreparedStatement statement3 = connection.prepareStatement(sqlResponse3);
                statement3.executeUpdate();
            }
            ResultSet resultSet22 = statement2.executeQuery(sqlResponse2);
            while (resultSet22.next()) {
                statement.setInt(2, resultSet22.getInt(1));
            }
            float price_item =supplyDocumentsDB.getPrice()/supplyDocumentsDB.getQuantity();
            statement.setInt(3, supplyDocumentsDB.getQuantity());
            statement.setString(4, supplyDocumentsDB.getDate());
            statement.setFloat(5, supplyDocumentsDB.getPrice());
            statement.setInt(10, supplyDocumentsDB.getId());
            statement.setFloat(6, Float.parseFloat(String.format("%.2f", price_item).replace(',', '.')));
            statement.setInt(7, supplyDocumentsDB.getMonth_leftovers());
            statement.setString(8, supplyDocumentsDB.getLot());
            statement.setInt(9, supplyDocumentsDB.getCurrent_stock());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Float count(SupplyDocumentsDB supplyDocumentsDB) {
        PricesDB pricesDB = new PricesDB();
        pricesDB.setSupplier(findIdSupplier(supplyDocumentsDB.getSupplier()));
        pricesDB.setMat_name(supplyDocumentsDB.getMaterial());
        Prices prices = new PricesImplemented();
    /*    System.out.println(supplyDocumentsDB.getSupplier());
        System.out.println(supplyDocumentsDB.getMaterial());
        System.out.println(findIdSupplier(supplyDocumentsDB.getSupplier()));*/
        return supplyDocumentsDB.getQuantity()*prices.getForMatSup(pricesDB);
    }

    public Integer findIdSupplier(String name) {
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
        return id;
    }

}
