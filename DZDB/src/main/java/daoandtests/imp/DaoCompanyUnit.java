package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.CompanyUnit;
import daoandtests.enitity.EmployeeCard;
import daoandtests.enitity.Rights;
import daoandtests.util.DBConnectionFactory;
import liquibase.pro.packaged.D;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DaoCompanyUnit implements Dao<CompanyUnit> {
    public CompanyUnit get(long id) {
        CompanyUnit cu = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from company_unit where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                cu= new CompanyUnit();
                cu.setId(rs.getLong(1));
                cu.setName(rs.getString(2));
                cu.setHeadquarterId(rs.getLong(4));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                cu.setCreationDate(LocalDateTime.parse(rs.getString(3), dtf));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return cu;

    }

    public List<CompanyUnit> getAll() {
        List<CompanyUnit> companyUnits = new ArrayList<>();
        CompanyUnit cu = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from company_unit");


            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                cu= new CompanyUnit();
                cu.setId(rs.getLong(1));
                cu.setName(rs.getString(2));
                cu.setHeadquarterId(rs.getLong(4));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                cu.setCreationDate(LocalDateTime.parse(rs.getString(3), dtf));
                companyUnits.add(cu);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return companyUnits;
    }

    public void save(CompanyUnit o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO company_unit(name, headquarter_id, creationdate) VALUES(?,?,?)");
            preparedStatement.setString(1, o.getName());
            preparedStatement.setLong(2, o.getHeadquarterId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            preparedStatement.executeUpdate();

        }catch (SQLException se){
            se.printStackTrace();
        }

    }

    public void update(CompanyUnit o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE company_unit set name = ?, headquarter_id = ? where id = ?");
            preparedStatement.setLong(3, o.getId());
            preparedStatement.setString(1, o.getName());
            preparedStatement.setLong(2, o.getHeadquarterId());
            preparedStatement.executeUpdate();

        }catch (SQLException se){
            se.printStackTrace();
        }

    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM company_unit where id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
