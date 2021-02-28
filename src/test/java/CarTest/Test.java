package CarTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iiichz.common.InterfaceAdapter;

public class Test {
    public static void main(String[] args) {

        Car cars[] = new Car[]{new Lexus("CarTest.Lexus Is", 260, "Sedan", 3), new Acura("CarTest.Acura Mdx", 193, "Suv")};
        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        InterfaceAdapter ia = new InterfaceAdapter();
        ia.setClassName("com.huawei.microservicedevtest.javaMethod.test.CarTest.Lexus");
        builder.registerTypeAdapter(Car.class, ia);
        Gson gson = builder.create();
        //Let's serialize our array
        String carsJsonFormat = "[{\"maxSpeed\":260,\"type\":\"Sedan\",\"rankingAmongSedans\":3,\"model\":\"CarTest.Lexus Is\"}]";
        //Let's Print our serialized arrya
        System.out.println("Cars in Json Format: " + carsJsonFormat);


        Car [] carJsonArray = gson.fromJson(carsJsonFormat, Car[].class);
        for(Car aCar : carJsonArray){
            System.out.println(aCar);
        }
        System.out.println("**********************************************************************");
    }
}
