package com.example.cp.production_orders;


import com.example.cp.DatabaseConnection;
import com.example.cp.suppliers.SuppliersDB;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductionOrdersImplemented implements ProductionOrders {

    Connection connection = DatabaseConnection.getConnection();


    @Override
    public List<ProductionOrdersDB> getAll() {
       List<ProductionOrdersDB> productionOrdersDB;

        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT * FROM production_orders";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            productionOrdersDB = new ArrayList<>();

            while (resultSet.next()) {
                ProductionOrdersDB productionOrdersDBs = new ProductionOrdersDB();
                productionOrdersDBs.setId(resultSet.getInt("id"));
                productionOrdersDBs.setMaterial(resultSet.getInt("material"));
                productionOrdersDBs.setNumber_material(findNumberMaterial(resultSet.getInt("material")));
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                productionOrdersDBs.setDate(result);
               // productionOrdersDBs.setDate(resultSet.getString("date"));
                productionOrdersDBs.setNeed_quantity(resultSet.getInt("need_quantity"));
                productionOrdersDBs.setReject_quantity(resultSet.getInt("reject_quantity"));
                productionOrdersDBs.setStatus(resultSet.getBoolean("status"));
                productionOrdersDB.add(productionOrdersDBs);
            }
            productionOrdersDB.sort(Comparator.comparing(ProductionOrdersDB::getId).reversed());
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return productionOrdersDB;
    }


    public String findNumberMaterial(Integer id) {
        String number = null;
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT number FROM materials WHERE id="+id+"" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                number = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return number;
    }
    @Override
    public List<ProductionOrdersDB> search(SearchProductionOrders searchProductionOrders) {
        List<ProductionOrdersDB> productionOrdersDB;
        if (Objects.equals(searchProductionOrders.getSearch(),"")&&searchProductionOrders.getDate()==null&&!searchProductionOrders.getIs_open())
        {
            return getAll();
        }
        else {

            try {
            Statement statement = connection.createStatement();
            String sqlResponse;
            if(Objects.equals(searchProductionOrders.getSearch(),"")&&!searchProductionOrders.getIs_open()&&searchProductionOrders.getDate()!=null){
                sqlResponse = "SELECT * FROM production_orders where date = '"+
                        searchProductionOrders.getDate()+"'";
                System.out.println("1");
            } else if (searchProductionOrders.getDate()==null&&!searchProductionOrders.getIs_open()) {
                sqlResponse = "SELECT * FROM production_orders where material IN (SELECT id FROM warehouse.materials " +
                        "where number like '%"+searchProductionOrders.getSearch()+"%')  ";
                System.out.println("2");
            } else if (Objects.equals(searchProductionOrders.getSearch(),"")&&searchProductionOrders.getDate()==null&&searchProductionOrders.getIs_open()) {
                sqlResponse = "SELECT * FROM production_orders where status " +
                        "="+!searchProductionOrders.getIs_open();
                System.out.println("3");
            } else if (Objects.equals(searchProductionOrders.getSearch(),"")&&searchProductionOrders.getIs_open()&&searchProductionOrders.getDate()!=null) {
                sqlResponse = "SELECT * FROM production_orders where " +
                        "status = "+!searchProductionOrders.getIs_open() +" AND date = '"+
                        searchProductionOrders.getDate()+"'";
                System.out.println("4");
            } else if (!Objects.equals(searchProductionOrders.getSearch(),"")&&searchProductionOrders.getIs_open()&&searchProductionOrders.getDate()==null) {
                sqlResponse = "SELECT * FROM production_orders where material IN (SELECT id FROM warehouse.materials " +
                        "where number like '%"+searchProductionOrders.getSearch()+"%') AND " +
                        "status = "+!searchProductionOrders.getIs_open() ;
                System.out.println("5");
            } else if (!Objects.equals(searchProductionOrders.getSearch(),"")&&!searchProductionOrders.getIs_open()&&searchProductionOrders.getDate()!=null) {
                sqlResponse = "SELECT * FROM production_orders where material IN (SELECT id FROM warehouse.materials " +
                        "where number like '%"+searchProductionOrders.getSearch()+"%') AND " +
                        "date = '"+ searchProductionOrders.getDate()+"'";
                System.out.println("6");
            }else {
                sqlResponse = "SELECT * FROM production_orders where material IN (SELECT id FROM warehouse.materials " +
                        "where number like '%"+searchProductionOrders.getSearch()+"%') AND " +
                        "status ="+!searchProductionOrders.getIs_open() +" AND date = '"+
                        searchProductionOrders.getDate()+"'";
                System.out.println("7");
            }
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            productionOrdersDB = new ArrayList<>();

            while (resultSet.next()) {
                ProductionOrdersDB productionOrdersDBs = new ProductionOrdersDB();
                productionOrdersDBs.setId(resultSet.getInt("id"));
                productionOrdersDBs.setMaterial(resultSet.getInt("material"));
                productionOrdersDBs.setNumber_material(findNumberMaterial(resultSet.getInt("material")));
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                java.util.Date date = oldDateFormat.parse(resultSet.getString("date"));
                String result = newDateFormat.format(date);
                productionOrdersDBs.setDate(result);
               // productionOrdersDBs.setDate(resultSet.getString("date"));
                productionOrdersDBs.setNeed_quantity(resultSet.getInt("need_quantity"));
                productionOrdersDBs.setReject_quantity(resultSet.getInt("reject_quantity"));
                productionOrdersDBs.setStatus(resultSet.getBoolean("status"));
                productionOrdersDB.add(productionOrdersDBs);
            }
                productionOrdersDB.sort(Comparator.comparing(ProductionOrdersDB::getId).reversed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            return productionOrdersDB;}
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
    public void deleteById(Integer id) {
        String get_material = "SELECT material, need_quantity, status FROM production_orders " +
                "WHERE id = " + id;
        String sql  = "DELETE FROM production_orders " +
                "WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get_material);
            while (resultSet.next()) {
                if(!resultSet.getBoolean(3)){
                String reduce_order_quantity = "UPDATE materials SET order_quantity = order_quantity - " + resultSet.getInt(2)+
                        " WHERE id = " + resultSet.getInt(1);
                PreparedStatement preparedStatement = connection.prepareStatement(reduce_order_quantity);
                preparedStatement.executeUpdate();
                }
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(ProductionOrdersDB productionOrdersDB) {
        System.out.println(String.valueOf(productionOrdersDB.getDate()));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sqlRequest = "INSERT INTO production_orders (material, date, need_quantity, status) " +
                "VALUES(?, ?, ?, ?)";
        String sql = "UPDATE materials SET" +
                " order_quantity =order_quantity + ?" +
                " WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, findIdMaterial(productionOrdersDB.getNumber_material()));
            statement.setString(2, productionOrdersDB.getDate());
            statement.setInt(3, productionOrdersDB.getNeed_quantity());
            statement.setBoolean(4, false);
            statement.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productionOrdersDB.getNeed_quantity());
            preparedStatement.setInt(2, findIdMaterial(productionOrdersDB.getNumber_material()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ProductionOrdersDB productionOrdersDB) {
        String sql = "UPDATE production_orders SET" +
                " date = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productionOrdersDB.getDate());
            preparedStatement.setInt(2, productionOrdersDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void reject(ProductionOrdersDB productionOrdersDB) {
        String sql = "UPDATE production_orders SET" +
                " reject_quantity = reject_quantity +?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productionOrdersDB.getReject_quantity());
            preparedStatement.setInt(2, productionOrdersDB.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Алгоритм списания материала со склада
    @Override
    public Integer writeOff(ProductionOrdersDB productionOrdersDB) {
        Integer stock_quantity = null, order_quantity = null;
        String sql = "UPDATE production_orders SET" +
                " status = ?" +
                " WHERE id = ?";
        String write_off = "UPDATE materials SET" +
                " stock_quantity = ?," +
                " order_quantity = ?" +
                " WHERE id = ?";
        try {
            Statement statement = connection.createStatement();
            String sqlResponse = "SELECT stock_quantity, order_quantity FROM materials WHERE id='"+productionOrdersDB.getMaterial()+"'" ;
            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                stock_quantity = resultSet.getInt(1);
                order_quantity = resultSet.getInt(2);
            }
            if(stock_quantity<productionOrdersDB.getNeed_quantity()){
                return 0;
            }else if(stock_quantity-productionOrdersDB.getNeed_quantity()<15){
                PreparedStatement preparedStatement2 = connection.prepareStatement(write_off);
                preparedStatement2.setInt(1, stock_quantity-productionOrdersDB.getNeed_quantity());
                if(order_quantity-productionOrdersDB.getNeed_quantity()<0) preparedStatement2.setInt(2, 0);
                else preparedStatement2.setInt(2, order_quantity-productionOrdersDB.getNeed_quantity());
                preparedStatement2.setInt(3, productionOrdersDB.getMaterial());
                preparedStatement2.executeUpdate();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, productionOrdersDB.getId());
                preparedStatement.executeUpdate();
                return 2;
            }
            else {
            PreparedStatement preparedStatement2 = connection.prepareStatement(write_off);
            preparedStatement2.setInt(1, stock_quantity-productionOrdersDB.getNeed_quantity());
            if(order_quantity-productionOrdersDB.getNeed_quantity()<0) preparedStatement2.setInt(2, 0);
                else preparedStatement2.setInt(2, order_quantity-productionOrdersDB.getNeed_quantity());
            preparedStatement2.setInt(3, productionOrdersDB.getMaterial());
            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, productionOrdersDB.getId());
            preparedStatement.executeUpdate();}
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
    }

    @Override
    public ArrayList<Report> consumptionReport(SearchProductionOrders searchProductionOrders){
        ArrayList<Report> report = new ArrayList<Report>();
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT material, sum(need_quantity) FROM warehouse.production_orders " +
                    "WHERE (date BETWEEN '"+searchProductionOrders.getDate_from()+"' AND '"+
                    searchProductionOrders.getDate_to()+"') GROUP BY material";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
         /*   if(!resultSet.next()){

            }
            else {*/
                while (resultSet.next()) {
                    Report report_fill = new Report();
                    report_fill.setMaterial(findNumberMaterial(resultSet.getInt(1)));
                    report_fill.setQuantity(resultSet.getInt(2));
                    report.add(report_fill);
            }
        //}

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    @Override
    public ArrayList<Report> qualityReport(SearchProductionOrders searchProductionOrders) {
        ArrayList<Report> report = new ArrayList<Report>();
        Float count;
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT material, sum(need_quantity), sum(reject_quantity) FROM warehouse.production_orders " +
                    "WHERE (date BETWEEN '"+searchProductionOrders.getDate_from()+"' AND '"+
                    searchProductionOrders.getDate_to()+"') AND status = 1 GROUP BY material";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                Report report_fill = new Report();
                report_fill.setMaterial(findNumberMaterial(resultSet.getInt(1)));
                report_fill.setQuantity(resultSet.getInt(2));
                report_fill.setReject(resultSet.getInt(3));
                count= Float.valueOf(resultSet.getInt(3))/Float.valueOf(resultSet.getInt(2));
                if(count<0.2){
                    report_fill.setQuality_num(0);
                    report_fill.setQuality("Хорошее");
                } else if (0.2<=count&&count<=0.6){
                    report_fill.setQuality_num(1);
                    report_fill.setQuality("Среднее");
                }else if (count>0.6){
                    report_fill.setQuality_num(2);
                    report_fill.setQuality("Плохое");
                }
                report.add(report_fill);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    @Override
    public ArrayList<Report> suppliersReport(ProductionOrdersDB productionOrdersDB) {
        ArrayList<Report> report = new ArrayList<Report>();
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT S.name, price FROM warehouse.material_supplier MS \n" +
                    "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id " +
                    "WHERE material = (SELECT id FROM materials WHERE number = '"+productionOrdersDB.getNumber_material()+"');";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                Report report_fill = new Report();
                report_fill.setSupplier(resultSet.getString(1));
                report_fill.setPrice(resultSet.getFloat(2));
                report.add(report_fill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

    @Override
    public ArrayList<Report> avgSuppliersReport() {
        ArrayList<Report> report = new ArrayList<Report>();
        try {
            Statement statement = connection.createStatement();

            String sqlResponse = "SELECT S.name, ROUND(AVG(price),2) FROM warehouse.material_supplier MS \n" +
                    "INNER JOIN warehouse.suppliers S ON MS.supplier=S.id GROUP BY MS.supplier;";

            ResultSet resultSet = statement.executeQuery(sqlResponse);
            while (resultSet.next()) {
                Report report_fill = new Report();
                report_fill.setSupplier(resultSet.getString(1));
                report_fill.setPrice(resultSet.getFloat(2));
                report.add(report_fill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }
}
