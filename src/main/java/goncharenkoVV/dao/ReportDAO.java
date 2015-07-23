package goncharenkoVV.dao;

import goncharenkoVV.model.Report;

import java.util.List;

public interface ReportDAO {
    public void saveOrUpdate(Report report);

    public void delete(int directoryId);

    public Report get(int directoryId);

    public List<Report> list();
}
