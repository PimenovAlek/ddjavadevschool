

import daoandtests.enitity.Rights;
import daoandtests.imp.DaoRights;

import java.io.IOException;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/";
        String dbName = "postgres";
        String login = "postgres";
        String password = "root";


        DaoRights daoRights = new DaoRights();
        Rights rights = new Rights();
        rights = daoRights.get(1);
        System.out.println(rights.getName());
        rights.setName("просмотр");
        daoRights.update(rights);


    }
}
