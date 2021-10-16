import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main method");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCar_number("11가1111");
        car1.setTYPE("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCar_number("11가2222");
        car2.setTYPE("suv");

        List<Car> carList = Arrays.asList(car1,car2); //이런식으로 변경
        user.setCars(carList);

        System.out.println(user);

        //json 으로 변경

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        //순수한 노드에 접근할 수 있는 방법?
        JsonNode jsonNode = objectMapper.readTree(json);
        String name = jsonNode.get("name").asText();    //타입 변경 asText
        int age = jsonNode.get("age").asInt();  //타입 변경 asInt

        System.out.println("name : " + name);
        System.out.println("age : " + age);

        // List<Car> list = jsonNode.get("cars").as ?? type 이 없음.
        String _list = jsonNode.get("cars").asText();
        System.out.println("_list : " + _list);

        //해결 ArrayNode의 접근 방법, Object에서 접근
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars; //타입 변환
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});

        System.out.println("cars : " + _cars);

        //json을 변경한다면?
        //특정 json값을 바꿀수 없게 햇는데, ObjectNode에서 변경가능하다.
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","steve");
        objectNode.put("age",20);

        System.out.println(objectNode.toPrettyString());
    }
}
