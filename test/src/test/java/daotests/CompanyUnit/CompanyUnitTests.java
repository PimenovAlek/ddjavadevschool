package daotests.CompanyUnit;

import daoandtests.enitity.CompanyUnit;
import daoandtests.imp.DaoCompanyUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CompanyUnitTests {
    DaoCompanyUnit daoCompanyUnit;
    String unitName = "Test";
    String modifiedName = "Test modified";
    Long headquarterId = 1l;


    @Before
    public void init(){
        daoCompanyUnit = new DaoCompanyUnit();
    }

    @Test
    public void getAllCompanyUnits(){
        List<CompanyUnit> companyUnits = daoCompanyUnit.getAll();
        Assert.assertNotNull(companyUnits);
    }

    @Test
    public void getCompanyUnitById(){
        CompanyUnit companyUnit = daoCompanyUnit.get(1);
        Assert.assertNotNull(daoCompanyUnit);
        Assert.assertTrue(companyUnit.getName().equals("ДД"));
    }

    @Test
    public void createCompanyUnit(){

        CompanyUnit companyUnit = new CompanyUnit(unitName,headquarterId);
        daoCompanyUnit.save(companyUnit);

        List<CompanyUnit> companyUnits = daoCompanyUnit.getAll();
        boolean res = false;
        for(CompanyUnit cu : companyUnits){
            if(cu.getName().equals(unitName)){
                res = true;
                break;
            }
        }
        Assert.assertTrue(res);
    }
    @Test
    public void updateCompanyUnit(){
        CompanyUnit companyUnit = daoCompanyUnit.get(11);
        companyUnit.setHeadquarterId(5l);

        daoCompanyUnit.update(companyUnit);
    }







}
