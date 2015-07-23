package goncharenkoVV.db.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileTreadPoolScannerHelper {
    private static ThreadPoolExecutor threadPoolExecutor = null;
    private static int maxSize = 5;
    private static int size = 3;
    private static long keepAliveTime = 2;


    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPoolExecutor == null) {
            synchronized (FileTreadPoolScannerHelper.class) {
                if (threadPoolExecutor == null)
                    /*
                        public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
                     */
                    threadPoolExecutor = new ThreadPoolExecutor(size, maxSize, keepAliveTime, TimeUnit.MINUTES
                            , new ArrayBlockingQueue<Runnable>(30, true)
                            , Executors.defaultThreadFactory()
                            , new ThreadPoolExecutor.CallerRunsPolicy()
                    );
            }
        }
        return threadPoolExecutor;
    }

}