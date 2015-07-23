package goncharenkoVV.dao;

import goncharenkoVV.model.Report;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {
    private JdbcTemplate jdbcTemplate;

    public ReportDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(Report report) {
        if (report.getId() != null) {
            // update
            String sql = "UPDATE report SET namePath=?, countFolders=?, countFiles=? "
                    + " WHERE report_id=?";
            jdbcTemplate.update(sql, report.getNamePath(), report.getCountFiles(),
                    report.getCountFolders(), report.getId());
        } else {
            // insert
            String sql = "INSERT INTO report (namePath, countFolders, countFiles)"
                    + " VALUES ( ?, ?, ?)";
            jdbcTemplate.update(sql, report.getNamePath(), report.getCountFolders(),
                    report.getCountFiles());
        }

    }

    @Override
    public void delete(int reportId) {
        String sql = "DELETE FROM report WHERE report_id=?";
        jdbcTemplate.update(sql, reportId);
    }

    @Override
    public List<Report> list() {
        String sql = "SELECT * FROM report";
        List<Report> listContact = jdbcTemplate.query(sql, new RowMapper<Report>() {

            @Override
            public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
                Report aReport = new Report();

                aReport.setId(rs.getInt("report_id"));
                aReport.setNamePath(rs.getString("namePath"));
                aReport.setCountFiles(rs.getString("countFiles"));
                aReport.setCountFolders(rs.getString("countFolders"));

                return aReport;
            }

        });

        return listContact;
    }

    @Override
    public Report get(int reportId) {
        String sql = "SELECT * FROM report WHERE report_id=" + reportId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Report>() {

            @Override
            public Report extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Report report = new Report();
                    report.setId(rs.getInt("report_id"));
                    report.setNamePath(rs.getString("namePath"));
                    report.setCountFiles(rs.getString("countFiles"));
                    report.setCountFolders(rs.getString("countFolders"));
                    return report;
                }

                return null;
            }

        });
    }

}