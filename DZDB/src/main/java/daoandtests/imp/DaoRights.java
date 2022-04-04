package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.Rights;
import daoandtests.enitity.UnitPosition;
import daoandtests.enitity.UserPosition;
import daoandtests.util.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRights implements Dao<Rights> {
    public Rights get(long id) {
        Rights r = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from rights where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                r= new Rights();
                r.setId(rs.getLong(1));
                r.setName(rs.getString(2));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return r;
    }

    public List<Rights> getAll() {
        List<Rights> rights = new ArrayList<>();
        Rights r = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from rights");


            while(rs.next()){
                r= new Rights();
                r.setId(rs.getLong(1));
                r.setName(rs.getString(2));
                rights.add(r);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        return rights;
    }

    public void save(Rights o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO rights(name) VALUES (?)");
            preparedStatement.setString(1,o.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void update(Rights o) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE rights set name = ? where id = ?");
            preparedStatement.setLong(2, o.getId());
            preparedStatement.setString(1, o.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM rights where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
