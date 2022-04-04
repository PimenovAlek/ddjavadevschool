package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.UserPosition;
import daoandtests.util.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUserPosition implements Dao<UserPosition> {

    public UserPosition get(long id) {
        UserPosition up = null;

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from user_position where id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                up= new UserPosition();
                up.setId(rs.getLong(1));
                up.setName(rs.getString(2));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return up;
    }

    public List<UserPosition> getAll() {
        List<UserPosition> userPositions = new ArrayList<>();
        UserPosition up = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user_position");


            while(rs.next()){
                up= new UserPosition();
                up.setId(rs.getLong(1));
                up.setName(rs.getString(2));
                userPositions.add(up);
            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        return userPositions;
    }

    public void save(UserPosition userPosition) {

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO user_position(name) VALUES (?)");
            preparedStatement.setString(1,userPosition.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void update(UserPosition userPosition) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE user_position set name = ? where id = ?");
            preparedStatement.setString(1, userPosition.getName());
            preparedStatement.setLong(2, userPosition.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM user_position where id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
