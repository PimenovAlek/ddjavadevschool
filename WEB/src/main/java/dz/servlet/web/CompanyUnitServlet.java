package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daoandtests.enitity.CompanyUnit;
import daoandtests.enitity.EmployeeCard;
import daoandtests.imp.DaoCompanyUnit;
import daoandtests.imp.DaoEmployeeCard;
import dz.servlet.dto.CreateCompanyUnitRequest;
import dz.servlet.dto.UpdateCompanyUnitRequest;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CompanyUnitServlet", urlPatterns = "/company_unit")
public class CompanyUnitServlet extends HttpServlet {

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
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        modelMapper = new ModelMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
    if(id != null){
            CompanyUnit companyUnit = this.daoCompanyUnit.get(Long.parseLong(id));
            List<EmployeeCard> employeeCards = this.daoEmployeeCard.getEmployeesFromUnit(Long.parseLong(id));
            companyUnit.setEmployees(employeeCards);
            resp.setCharacterEncoding("UTF-8");
            try(PrintWriter writer = resp.getWriter()){
                resp.setContentType("application/json; charset=utf-8");
                writer.write(objectMapper.writeValueAsString(companyUnit));
            }
        }else{
        resp.sendError(400);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);
        CreateCompanyUnitRequest createCompanyUnitRequest = objectMapper.readValue(body, CreateCompanyUnitRequest.class);

        CompanyUnit companyUnit = modelMapper.map(createCompanyUnitRequest, CompanyUnit.class);
        this.daoCompanyUnit.save(companyUnit);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);

        UpdateCompanyUnitRequest companyUnitRequest = objectMapper.readValue(body,UpdateCompanyUnitRequest.class);
        System.out.println(companyUnitRequest);
        CompanyUnit companyUnit = modelMapper.map(companyUnitRequest, CompanyUnit.class);
        System.out.println(companyUnit);
        this.daoCompanyUnit.update(companyUnit);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
        if(id != null){

           this.daoCompanyUnit.delete(Long.parseLong(id));
        }else {
            resp.sendError(400);
        }
    }


}
