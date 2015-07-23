package goncharenkoVV.db.service;

import goncharenkoVV.model.Report;

import java.util.List;

public interface ReportService {

    public void saveOrUpdate(Report report);

    public void delete(Integer directoryId);

    public Report get(Integer directoryId);

    public List<Report> list();
}
