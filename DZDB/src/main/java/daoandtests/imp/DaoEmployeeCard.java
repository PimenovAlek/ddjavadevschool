package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.EmployeeCard;
import daoandtests.util.DBConnectionFactory;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DaoEmployeeCard implements Dao<EmployeeCard> {
    public EmployeeCard get(long id) {
        EmployeeCard ec = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee_card where id = ?");
            preparedStatement.setLong(1, id);


            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                ec= new EmployeeCard();
                ec.setId(rs.getLong(1));
                ec.setFirstName(rs.getString(2));
                ec.setLastName(rs.getString(3));
                ec.setThirdName(rs.getString(4));
                ec.setLogin(rs.getString(5));
                ec.setPassword(rs.getString(6));
                //rs.getString(7)
                DaoCompanyUnit dc = new DaoCompanyUnit();
                ec.setCompanyUnit(dc.get(rs.getLong(10)));
                DaoRoles daoRoles = new DaoRoles();
                ec.setRoles(daoRoles.get(rs.getLong(8)));
                DaoUserPosition userPosition = new DaoUserPosition();
                ec.setUserPosition(userPosition.get(rs.getLong(9)));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                ec.setCreationDate(LocalDateTime.parse(rs.getString(7), dtf));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return ec;
    }

    public List<EmployeeCard> getAll() {
        List<EmployeeCard> employeeCards = new ArrayList<>();
        EmployeeCard ec = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from employee_card");

            while(rs.next()){
                ec = new EmployeeCard();
                ec.setId(rs.getLong(1));
                ec.setFirstName(rs.getString(2));
                ec.setLastName(rs.getString(3));
                ec.setThirdName(rs.getString(4));
                ec.setLogin(rs.getString(5));
                ec.setPassword(rs.getString(6));
                //rs.getString(7)
                DaoCompanyUnit dc = new DaoCompanyUnit();
                ec.setCompanyUnit(dc.get(rs.getLong(10)));
                DaoRoles daoRoles = new DaoRoles();
                ec.setRoles(daoRoles.get(rs.getLong(8)));
                DaoUserPosition userPosition = new DaoUserPosition();
                ec.setUserPosition(userPosition.get(rs.getLong(9)));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                ec.setCreationDate(LocalDateTime.parse(rs.getString(7), dtf));
                employeeCards.add(ec);

            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return employeeCards;
    }

    public void save(EmployeeCard o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO employee_card(firstname,lastname,thirdname,login,password,creationdate,role_id,position_id,unit_id) " +
                            "VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,o.getFirstName());
            preparedStatement.setString(2,o.getLastName());
            preparedStatement.setString(3,o.getThirdName());
            preparedStatement.setString(4,o.getLogin());
            preparedStatement.setString(5,o.getPassword());
            preparedStatement.setLong(9,o.getCompanyUnit().getId());
            preparedStatement.setLong(7,o.getRoles().getId());
            preparedStatement.setLong(8,o.getUserPosition().getId());

            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));



            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void update(EmployeeCard o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("Update employee_card set firstname = ?,lastname = ?,thirdname = ?,login = ?," +
                            "password = ?, role_id = ?,position_id = ?,unit_id = ? where id = ?");
            preparedStatement.setString(1, o.getFirstName());
            preparedStatement.setString(2, o.getLastName());
            preparedStatement.setString(3, o.getThirdName());
            preparedStatement.setString(4, o.getLogin());
            preparedStatement.setString(5, o.getPassword());
            preparedStatement.setLong(8, o.getCompanyUnit().getId());
            preparedStatement.setLong(6, o.getRoles().getId());
            preparedStatement.setLong(7, o.getUserPosition().getId());
            preparedStatement.setLong(9, o.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM employee_card where id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public List<EmployeeCard> getEmployeesFromUnit(long id){
        List<EmployeeCard> employeeCards = new ArrayList<>();
        EmployeeCard ec = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * FROM employee_card where unit_id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                ec = new EmployeeCard();
                ec.setId(rs.getLong(1));
                ec.setFirstName(rs.getString(2));
                ec.setLastName(rs.getString(3));
                ec.setThirdName(rs.getString(4));
                ec.setLogin(rs.getString(5));
                ec.setPassword(rs.getString(6));
                //rs.getString(7)
                DaoCompanyUnit dc = new DaoCompanyUnit();
                ec.setCompanyUnit(dc.get(rs.getLong(10)));
                DaoRoles daoRoles = new DaoRoles();
                ec.setRoles(daoRoles.get(rs.getLong(8)));
                DaoUserPosition userPosition = new DaoUserPosition();
                ec.setUserPosition(userPosition.get(rs.getLong(9)));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                ec.setCreationDate(LocalDateTime.parse(rs.getString(7), dtf));

                employeeCards.add(ec);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        return employeeCards;
    }


}
