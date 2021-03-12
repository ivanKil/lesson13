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

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            stageSynchronizer.getAllReadyLatch().await();//ожидание пока все будут готовы
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            stageSynchronizer.getStartMessageLatch().countDown();//после объявления о старте все стартуют
            stageSynchronizer.getFinishersLatch().await();//ожидание пока все приедут
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}