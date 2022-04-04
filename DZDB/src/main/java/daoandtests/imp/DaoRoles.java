package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.Roles;
import daoandtests.util.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRoles implements Dao<Roles> {
    public Roles get(long id) {
        Roles r = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from roles where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                r= new Roles();
                r.setId(rs.getLong(1));
                r.setName(rs.getString(2));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return r;
    }

    public List<Roles> getAll() {
        List<Roles> roles = new ArrayList<>();
        Roles r = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from roles");


            while(rs.next()){
                r= new Roles();
                r.setId(rs.getLong(1));
                r.setName(rs.getString(2));
                roles.add(r);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        return roles;
    }

    public void save(Roles roles) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO roles(name) VALUES (?)");
            preparedStatement.setString(1,roles.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void update(Roles roles) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE roles set name = ? where id = ?");
            preparedStatement.setLong(2,roles.getId());
            preparedStatement.setString(1,roles.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM roles where id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
