package goncharenkoVV.db.service;

import goncharenkoVV.cache.ReportCache;
import goncharenkoVV.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vitaliy on 05.07.2015.
 */
@Component
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportCache reportCache;

    @Override
    public void saveOrUpdate(Report report) {
        reportCache.saveOrUpdate(report);
    }

    @Override
    public void delete(Integer directoryId) {
        reportCache.delete(directoryId);
    }

    @Override
    public Report get(Integer directoryId) {
        return reportCache.findId(directoryId);

    }

    @Override
    public List<Report> list() {
        return reportCache.list();
    }
}
