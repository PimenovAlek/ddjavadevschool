package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import daoandtests.enitity.UserPosition;
import daoandtests.imp.DaoUserPosition;
import dz.servlet.dto.CreateUserPositionRequest;
import dz.servlet.dto.UpdateUserPositionRequest;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "UserPositionServlet", urlPatterns = "/user_position")
public class UserPositionServlet extends HttpServlet {
    DaoUserPosition daoUserPosition;
    ObjectMapper objectMapper;
    ModelMapper modelMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        daoUserPosition = new DaoUserPosition();
        objectMapper = new ObjectMapper();
        modelMapper = new ModelMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
        if(id != null){
            UserPosition userPosition = this.daoUserPosition.get(Long.parseLong(id));
            resp.setCharacterEncoding("UTF-8");
            try(PrintWriter writer = resp.getWriter()){
                resp.setContentType("application/json; charset=utf-8");
                writer.write(objectMapper.writeValueAsString(userPosition));
            }
        }else{
            resp.sendError(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        System.out.println(body);

        CreateUserPositionRequest userPositionRequest = objectMapper.readValue(body,CreateUserPositionRequest.class);
        UserPosition userPosition = modelMapper.map(userPositionRequest, UserPosition.class);

        this.daoUserPosition.save(userPosition);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        System.out.println(body);
        UpdateUserPositionRequest userPositionRequest = objectMapper.readValue(body, UpdateUserPositionRequest.class);
        UserPosition userPosition = modelMapper.map(userPositionRequest, UserPosition.class);

        this.daoUserPosition.update(userPosition);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
        if(id != null){
            this.daoUserPosition.delete(Long.parseLong(id));
        }else{
            resp.sendError(400);
        }
    }
}
