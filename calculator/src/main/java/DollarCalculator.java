public class DollarCalculator implements  ICalculator{

    private int price = 1;

    private MarketApi marketApi;

    public DollarCalculator(MarketApi marketApi){
        this.marketApi = marketApi;
    }

    //달러 시세 가져오기
    public void init(){
        this.price = marketApi.connect();   //marketApi를 가져옴.
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x - y;
    }
}
