package CarTest;

public class Acura implements Car{
    private int maxSpeed;
    private String type;
    private String model;

    public Acura(String model, int maxSpeed, String type) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.type = type;
    }
    public String model() {
        return null;
    }
    public int maxSpeed() {
        return maxSpeed;
    }
    public String type() {
        return type;
    }
    @Override
    public String toString() {
        return "Model: " + model + ", Max speed: " + maxSpeed + " km/h, Type: " + type;
    }
}