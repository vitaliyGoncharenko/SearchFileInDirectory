package goncharenkoVV.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class StartSearchService implements Runnable {
    private File dir;
    public static final AtomicInteger countDirectory = new AtomicInteger(-1);
    public static final AtomicInteger countFile = new AtomicInteger();
    private static Logger logger = LoggerFactory.getLogger(StartSearchService.class);
    PersistenceService persistenceService = new PersistenceServiceImpl();

    public StartSearchService(File dir) {
        this.dir = dir;
    }

    @Override
    public void run() {
        if (dir.isDirectory()) {
            countDirectory.addAndGet(1);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    FileTreadPoolScannerHelper.getThreadPoolExecutor().execute(new StartSearchService(file));
                    logger.info("It is processing directory.. " + file.getName());
                    persistenceService.write("Directory " + file.getName());
                }
            }
        } else {
            logger.info("It is processing file.. " + dir.getName());
            persistenceService.write("File " + dir.getName());
            countFile.addAndGet(1);
        }
    }
}
