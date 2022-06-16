import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Loader {

    private static char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        AtomicInteger iteration = new AtomicInteger(0);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        //executor.
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                int iterationNumber = iteration.getAndIncrement();
                //System.out.println(iterationNumber);
                try {
                    PrintWriter writer = new PrintWriter("/Users/anton/IdeaProjects/java_basics/" +
                            "Performance/CarNumberGenerator/res/numbers" + iterationNumber + ".txt");

                    for (int regionCode = iterationNumber * 10;
                         regionCode < (iterationNumber + 1) * 10; regionCode++) {
                        if (regionCode == 0) {
                            continue;
                        }
                        StringBuilder builder = new StringBuilder();
                        for (int number = 1; number < 1000; number++) {
                            for (char firstLetter : letters) {
                                for (char secondLetter : letters) {
                                    for (char thirdLetter : letters) {
                                        builder.append(firstLetter);

                                        String numberStr = Integer.toString(number);
                                        int padSize = 3 - numberStr.length();
                                        for (int j = 0; j < padSize; j++) {
                                            builder.append('0');
                                        }
                                        builder.append(numberStr);

                                        //builder.append(padNumber(number, 3));
                                        builder.append(secondLetter);
                                        builder.append(thirdLetter);

                                        numberStr = Integer.toString(regionCode);
                                        padSize = 2 - numberStr.length();
                                        ;
                                        for (int j = 0; j < padSize; j++) {
                                            builder.append('0');
                                        }
                                        builder.append(numberStr);

                                        //builder.append(padNumber(regionCode, 2));
                                        builder.append(System.lineSeparator());
                                    }
                                }
                            }
                        }
                        writer.write(builder.toString());
                    }
                    writer.flush();
                    writer.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    /*private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }*/
    private static StringBuilder padNumber(int number, int numberLength) {
        StringBuilder numberStrBuf = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();;
        for (int i = 0; i < padSize; i++) {
            numberStrBuf.append('0');
        }
        numberStrBuf.append(numberStr);
        return numberStrBuf;
    }
}