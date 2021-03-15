public class Car implements Runnable {
    private static int CARS_COUNT;
    StageSynchronizer stageSynchronizer;
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed, StageSynchronizer stageSynchronizer) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.stageSynchronizer = stageSynchronizer;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            stageSynchronizer.getAllReadyLatch().countDown();
            stageSynchronizer.incrementCountReadyCars();
            stageSynchronizer.getAllReadyLatch().await();//ожидание пока все приготовятся
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Задание 4. Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
        stageSynchronizer.addFinishedCar(this);
    }
}
