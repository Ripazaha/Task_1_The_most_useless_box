public class Main {
    public static volatile boolean toggleSwitch = false;
    public static int userLatency = 1000;
    public static int numberOfIterations = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread user = new Thread(() -> {
            for (int i = 0; i < numberOfIterations; i++) {
                try {
                    Thread.sleep(userLatency);
                } catch (InterruptedException e) {
                    return;
                }
                if (!toggleSwitch) {
                    System.out.println("- тумблер включен");
                    toggleSwitch = true;
                }
            }
        });
        user.start();


        Thread toy = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (toggleSwitch) {
                    System.out.println("- тумблер выключен\n");
                    toggleSwitch = false;
                }
            }

        });
        toy.start();

        user.join();
        toy.interrupt();
    }
}
