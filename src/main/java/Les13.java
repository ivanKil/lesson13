import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Les13 {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        StageSynchronizer stageSynchronizer = new StageSynchronizer(CARS_COUNT);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), stageSynchronizer);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }

        executorService.shutdown();
    }
}