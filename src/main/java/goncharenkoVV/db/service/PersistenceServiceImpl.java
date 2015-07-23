package goncharenkoVV.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenceServiceImpl implements PersistenceService {
    private static FileWriter fileWriter = null;
    private static Logger logger = LoggerFactory.getLogger(PersistenceServiceImpl.class);
    File file = new File("C:/report.txt");

    private static FileWriter getFileWriter(File file) {
        if (fileWriter == null) {
            synchronized (PersistenceService.class) {
                try {
                    return fileWriter = new FileWriter(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileWriter;
    }


    public synchronized void write(String str) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = getFileWriter(file);

            fileWriter.append(str).append(System.getProperty("line.separator"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
