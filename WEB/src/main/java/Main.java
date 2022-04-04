import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daoandtests.enitity.EmployeeCard;
import daoandtests.imp.DaoEmployeeCard;
import daoandtests.imp.DaoUserPosition;
import liquibase.pro.packaged.E;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        DaoEmployeeCard daoEmployeeCard = new DaoEmployeeCard();
        EmployeeCard employeeCard = daoEmployeeCard.get(1);
        String a = objectMapper.writeValueAsString(employeeCard);
        System.out.println(a);
        System.out.println("++++++++++");
        System.out.println(employeeCard);
    }
}
