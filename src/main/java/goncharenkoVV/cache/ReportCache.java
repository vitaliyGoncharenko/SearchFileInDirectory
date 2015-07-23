package goncharenkoVV.cache;


import goncharenkoVV.model.Report;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ReportCache {
    String CACHE_DIRECTORY = "directory.cache";

    @Cacheable(CACHE_DIRECTORY)
    Report findId(Integer id);

    @CacheEvict(value = CACHE_DIRECTORY, allEntries = true)
    void evictAll();

    @CacheEvict(value = CACHE_DIRECTORY, key = "#directoryId")
    void evict(Integer contactId);

    void saveOrUpdate(Report report);

    @CacheEvict(value = CACHE_DIRECTORY, key = "#directoryId")
    void delete(int directoryId);

    @Cacheable(CACHE_DIRECTORY)
    List<Report> list();
}
