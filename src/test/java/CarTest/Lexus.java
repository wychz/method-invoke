package CarTest;

public class Lexus implements Car {

    private int maxSpeed;
    private String type;
    private int rankingAmongSedans;
    private String model;

    public Lexus(String model, int maxSpeed, String type, int rankingAmongSedans) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.type = type;
        this.rankingAmongSedans = rankingAmongSedans;
    }
    public String model() {
        return model;
    }
    public int maxSpeed() {
        return maxSpeed;
    }
    public String type() {
        return type;
    }
    public int getRankingAmongSedans() {
        return rankingAmongSedans;
    }
    @Override
    public String toString(){
        return "Model: " + model + ", Max speed: " + maxSpeed + " km/h, Type: " + type + ", Ranking: " + rankingAmongSedans ;
    }
}