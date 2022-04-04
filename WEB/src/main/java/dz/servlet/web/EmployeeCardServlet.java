package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daoandtests.enitity.EmployeeCard;
import daoandtests.imp.DaoEmployeeCard;
import dz.servlet.dto.CreateEmployeeCardRequest;
import dz.servlet.dto.UpdateEmployeeCardRequest;
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

@WebServlet(name = "EmployeeCardServlet", urlPatterns = "/employee_card")
public class EmployeeCardServlet extends HttpServlet {
    DaoEmployeeCard daoEmployeeCard;
    ObjectMapper objectMapper;
    ModelMapper modelMapper;
    @Override
    public void init() throws ServletException {
        super.init();
        daoEmployeeCard = new DaoEmployeeCard();
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
        if(id != null){
            EmployeeCard employeeCard = this.daoEmployeeCard.get(Long.parseLong(id));
            resp.setCharacterEncoding("UTF-8");
            try(PrintWriter pw = resp.getWriter()){
                resp.setContentType("application/json; charset=utf-8");
                pw.write(objectMapper.writeValueAsString(employeeCard));
            }
        }else {
            resp.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        CreateEmployeeCardRequest createEmployeeCardRequest = objectMapper.readValue(body, CreateEmployeeCardRequest.class);
        EmployeeCard employeeCard = modelMapper.map(createEmployeeCardRequest, EmployeeCard.class);

        this.daoEmployeeCard.save(employeeCard);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        UpdateEmployeeCardRequest employeeCardRequest = objectMapper.readValue(body, UpdateEmployeeCardRequest.class);
        EmployeeCard employeeCard = modelMapper.map(employeeCardRequest, EmployeeCard.class);

        this.daoEmployeeCard.update(employeeCard);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null){
            this.daoEmployeeCard.delete(Long.parseLong(id));
        }else {
            resp.sendError(400);
        }
    }


}
