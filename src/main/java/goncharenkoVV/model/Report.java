package goncharenkoVV.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Vitaliy on 05.07.2015.
 */
@Component
public class Report {
    private Integer id;
    private String namePath;
    private String countFolders;
    private String countFiles;


    public Report() {
    }

    public Report(String namePath, String countFolders, String countFiles) {
        this.namePath = namePath;
        this.countFolders = countFolders;
        this.countFiles = countFiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getCountFolders() {
        return countFolders;
    }

    public void setCountFolders(String countFolders) {
        this.countFolders = countFolders;
    }

    public String getCountFiles() {
        return countFiles;
    }

    public void setCountFiles(String countFiles) {
        this.countFiles = countFiles;
    }
}
