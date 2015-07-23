package goncharenkoVV.cache;


import goncharenkoVV.dao.ReportDAO;
import goncharenkoVV.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ReportCacheImpl implements ReportCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportCacheImpl.class);
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ReportDAO reportDAO;


    @PostConstruct
    public void init() {
        LOGGER.info("Loading data into PlayerDetailedDeviceInfo cache");
        update();
    }


    /**
     * It loads information from DB into the cache.
     */
    public void update() {
        for (Report report : reportDAO.list()) {
            cacheManager.getCache(CACHE_DIRECTORY).put(report.getId(), report);
        }
    }


    @Override
    @Cacheable(CACHE_DIRECTORY)
    public Report findId(final Integer id) {
        LOGGER.debug("Loading device id by device information={}", id);
        Report result = reportDAO.get(id);
        LOGGER.info("Loaded device id = {}", result);
        return result;
    }

    @Override
    @CacheEvict(value = CACHE_DIRECTORY, allEntries = true)
    public void evictAll() {}


    @Override
    @CacheEvict(value = CACHE_DIRECTORY, key = "#reportId")
    public void evict(final Integer reportId) {
        LOGGER.debug("delete from cache by directoryId: {}", reportId);
    }

    @Override
    public void saveOrUpdate(Report report) {
        if (report != null && report.getId() != null)
            evict(report.getId());
        reportDAO.saveOrUpdate(report);
    }

    @Override
    public void delete(int reportId) {
        evict(reportId);
        reportDAO.delete(reportId);
    }

    @Override
    @Cacheable(CACHE_DIRECTORY)
    public List<Report> list() {
        List<Report> list = reportDAO.list();
        LOGGER.debug("report list in cache");
        return list;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
