import java.util.concurrent.CountDownLatch;

public class StageSynchronizer {
    private final int carsCount;
    private final CountDownLatch allReadyLatch;
    private Integer countReadyCars = 0;
    private Integer countFinishedCars = 0;

    public StageSynchronizer(int carsCount) {
        this.carsCount = carsCount;
        allReadyLatch = new CountDownLatch(carsCount);
    }

    public CountDownLatch getAllReadyLatch() {
        return allReadyLatch;
    }

    public void incrementCountReadyCars() {
        synchronized (countReadyCars) {
            countReadyCars++;
            if (countReadyCars == carsCount) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            }
        }
    }

    public void addFinishedCar(Car car) {
        synchronized (countFinishedCars) {
            countFinishedCars++;
            if (countFinishedCars == 1) {//если прибыл первый, то победитель
                System.out.println("Победил " + car.getName());
            } else {
                System.out.println("Финишировал " + car.getName());
            }
            if (countFinishedCars == carsCount) {//если все прибыли
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            }
        }
    }
}