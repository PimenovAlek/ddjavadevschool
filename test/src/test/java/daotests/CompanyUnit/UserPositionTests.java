package daotests.CompanyUnit;

import daoandtests.enitity.UserPosition;
import daoandtests.imp.DaoUserPosition;
import liquibase.pro.packaged.S;
import org.junit.Before;
import org.junit.Test;

public class UserPositionTests {
    DaoUserPosition daoUserPosition;

    @Before
    public void init(){
        daoUserPosition = new DaoUserPosition();
    }

    @Test
    public void update(){
        UserPosition userPosition = new UserPosition(6l,"test");
        daoUserPosition.update(userPosition);
    }
}
