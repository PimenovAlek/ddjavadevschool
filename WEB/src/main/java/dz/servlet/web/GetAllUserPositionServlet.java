package dz.servlet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import daoandtests.enitity.UserPosition;
import daoandtests.imp.DaoUserPosition;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name="GetAllUserPositionServlet", urlPatterns = "/all_user_positions")
public class GetAllUserPositionServlet extends HttpServlet {
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
        List<UserPosition> userPositions = this.daoUserPosition.getAll();
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter pw = resp.getWriter()){
            resp.setContentType("application/json; charset=utf-8");
            resp.setHeader("Content-type", "text/html;charset=UTF-8");

//            for(UserPosition up : userPositions){
//                System.out.println(objectMapper.writeValueAsString(up));
//                pw.write(objectMapper.writeValueAsString(up));
//            }
            pw.write(objectMapper.writeValueAsString(userPositions));
        }
    }
}
