package daoandtests.imp;

import daoandtests.Dao;
import daoandtests.enitity.UnitPosition;
import daoandtests.enitity.UserPosition;
import daoandtests.util.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUnitPosition implements Dao<UnitPosition> {
    public UnitPosition get(long id) {

        UnitPosition up = null;
        ResultSet rs = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection.prepareStatement("select * from unit_position where id = ?");
            preparedStatement.setLong(1, id);

            rs = preparedStatement.executeQuery();

            if(rs.next()){
                up= new UnitPosition();
                up.setId(rs.getLong(1));
                up.setParent_id(rs.getLong(2));


            }

        }catch (SQLException se){
            se.printStackTrace();
        }

        return up;
    }

    public List<UnitPosition> getAll() {
        List<UnitPosition> unitPositions = new ArrayList<>();
        UnitPosition up = null;
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from unit_position");


            while(rs.next()){
                up= new UnitPosition();
                up.setId(rs.getLong(1));
                up.setParent_id(rs.getLong(2));

            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        return unitPositions;

    }

    public void save(UnitPosition unitPosition) {

        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO unit_position(parent_id) VALUES (?)");
            preparedStatement.setLong(1,unitPosition.getParent_id());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void update(UnitPosition unitPosition) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE unit_position set parent_id = ? where id = ?");

            preparedStatement.setLong(1,unitPosition.getParent_id());
            preparedStatement.setLong(2,unitPosition.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public void delete(long id) {
        try(Connection connection = DBConnectionFactory.getDBConnectionFromPool()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM unit_position where id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
