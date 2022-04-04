package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daoandtests.enitity.CompanyUnit;
import daoandtests.enitity.EmployeeCard;
import daoandtests.imp.DaoCompanyUnit;
import daoandtests.imp.DaoEmployeeCard;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="GetAllCompanyUnitsServlet", urlPatterns="/all_company_units")
public class GetAllCompanyUnitsServlet extends HttpServlet {

    DaoCompanyUnit daoCompanyUnit;
    DaoEmployeeCard daoEmployeeCard;
    ObjectMapper objectMapper;
    ModelMapper modelMapper;
    @Override
    public void init() throws ServletException {
        super.init();
        daoCompanyUnit = new DaoCompanyUnit();
        daoEmployeeCard = new DaoEmployeeCard();
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyUnit> companyUnits = this.daoCompanyUnit.getAll();
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter pw = resp.getWriter()){
            resp.setContentType("application/json; charset=utf-8");

            for(CompanyUnit cu : companyUnits){
                List<EmployeeCard> employeeCards = this.daoEmployeeCard.getEmployeesFromUnit(cu.getId());
                cu.setEmployees(employeeCards);

            }
            pw.write(objectMapper.writeValueAsString(companyUnits));
        }
    }


}
