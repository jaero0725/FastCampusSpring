import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// market 값이 항상 달라질 수 있다.
@ExtendWith(MockitoExtension.class) //모킹 할 수 있는 환경 -> marketApi
public class DollarCalculatorTest {

    @Mock   //Mock 처리 -> 변경되는 거
    public MarketApi marketApi;

    @BeforeEach //test 실행 이전에 실행할것
    public void init(){
        //기존에 있는것이 아니라
        //market connect() 메서드가 실행될때 3000을 return할 것이다.
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }
    @Test
    public void testHello(){
        System.out.println("hello-");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(22000,calculator.sum(10,10));    //원하는 결과가 22000이라는 뜻
        Assertions.assertEquals(0,calculator.minus(10,10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);    //mock 처리된 marketApi를 가져온다.
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(60000,calculator.sum(10,10));    //원하는 결과가 22000이라는 뜻
        Assertions.assertEquals(0,calculator.minus(10,10));
    }
}
