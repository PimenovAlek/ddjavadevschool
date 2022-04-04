package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daoandtests.enitity.EmployeeCard;
import daoandtests.imp.DaoEmployeeCard;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name ="GetAllEmployeeCardsServlet", urlPatterns="/all_employee_cards")
public class GetAllEmployeeCardsServlet extends HttpServlet {
    DaoEmployeeCard daoEmployeeCard;
    ObjectMapper objectMapper;
    ModelMapper modelMapper;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoEmployeeCard = new DaoEmployeeCard();
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeCard> employeeCards = this.daoEmployeeCard.getAll();

        try(PrintWriter pw = resp.getWriter()){
            resp.setContentType("application/json; charset=utf-8");
            for(EmployeeCard e : employeeCards){
                pw.write(objectMapper.writeValueAsString(e));
            }
        }
    }
}
