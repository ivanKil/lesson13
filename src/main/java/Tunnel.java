import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore smpBandwidth;//Задание 2: семафор для ограничения пропускной способности тоннеля

    public Tunnel(int bandwidth) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        smpBandwidth = new Semaphore(bandwidth);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу (ждет): " + description);
                smpBandwidth.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smpBandwidth.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
