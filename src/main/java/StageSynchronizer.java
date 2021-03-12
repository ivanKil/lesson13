import java.util.concurrent.CountDownLatch;

public class StageSynchronizer {
    private static int MESSAGERS_COUNT = 1;
    private final CountDownLatch allReadyLatch;
    private final CountDownLatch startMessageLatch;
    private final CountDownLatch finishersLatch;

    public StageSynchronizer(int carsCount) {
        allReadyLatch = new CountDownLatch(carsCount);
        startMessageLatch = new CountDownLatch(MESSAGERS_COUNT);
        finishersLatch = new CountDownLatch(carsCount);
    }

    public CountDownLatch getAllReadyLatch() {
        return allReadyLatch;
    }

    public CountDownLatch getStartMessageLatch() {
        return startMessageLatch;
    }

    public CountDownLatch getFinishersLatch() {
        return finishersLatch;
    }
}
