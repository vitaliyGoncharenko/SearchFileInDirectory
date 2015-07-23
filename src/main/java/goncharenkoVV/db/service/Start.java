package goncharenkoVV.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Start {
    private static Logger logger = LoggerFactory.getLogger(Start.class);
    public static String countFolders;
    public static String countFiles;
    static StartSearchService startSearchService;

    public static String start(String nameDirectory) throws InterruptedException {

        Thread.currentThread().setPriority(10);
        File file = new File(nameDirectory);
        logger.info("Start");
        startSearchService = new StartSearchService(file);
        try {
            Thread.currentThread().setPriority(10);
            FileTreadPoolScannerHelper.getThreadPoolExecutor();
            FileTreadPoolScannerHelper.getThreadPoolExecutor().execute(startSearchService);
            FileTreadPoolScannerHelper.getThreadPoolExecutor().awaitTermination(15, TimeUnit.SECONDS);
            FileTreadPoolScannerHelper.getThreadPoolExecutor().shutdown();
        } finally {
            countFolders = startSearchService.countDirectory.toString();
            countFiles = startSearchService.countFile.toString();
            logger.info("Report : number of folders " + countFolders + ",number of files " + countFiles);
        }
        return "Report : number of folders " + countFolders + ",number of files " + countFiles;
    }
}
