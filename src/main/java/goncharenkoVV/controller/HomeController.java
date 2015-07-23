package goncharenkoVV.controller;


import goncharenkoVV.db.service.ReportService;
import goncharenkoVV.db.service.Start;
import goncharenkoVV.db.service.StartSearchService;

import goncharenkoVV.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ReportService reportService;
    private String report;

    @RequestMapping(value = "/")
    public ModelAndView listDirectory(ModelAndView model) throws IOException {
        List<Report> listReport = reportService.list();
        model.addObject("listReport", listReport);
        model.addObject("report", report);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/newReport", method = RequestMethod.GET)
    public ModelAndView newDirectory(ModelAndView model) {
        Report newReport = new Report();
        model.addObject("report", newReport);
        model.setViewName("ReportForm");
        return model;
    }

    @RequestMapping(value = "/saveReport", method = RequestMethod.POST)
    public ModelAndView saveDirectory(@ModelAttribute Report report) {
        reportService.saveOrUpdate(report);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteReport", method = RequestMethod.GET)
    public ModelAndView deleteReport(HttpServletRequest request) {
        int reportId = Integer.parseInt(request.getParameter("id"));
        reportService.delete(reportId);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editReport", method = RequestMethod.GET)
    public ModelAndView editReport(HttpServletRequest request) {
        int reportId = Integer.parseInt(request.getParameter("id"));
        Report report = reportService.get(reportId);
        ModelAndView model = new ModelAndView("ReportForm");
        model.addObject("report", report);

        return model;
    }

    @RequestMapping(value = "/startSearch", method = RequestMethod.POST)
    public ModelAndView startSearch(HttpServletRequest request) throws InterruptedException {
        String nameDirectory = request.getParameter("nameDirectory");
        Start.start(nameDirectory);
        String folders = "" + StartSearchService.countDirectory.intValue();
        String files = "" + StartSearchService.countFile.intValue();
        report = "Report : number of folders " + folders
                + ",number of files " + files;

        Report report = new Report(nameDirectory, folders, files);
        reportService.saveOrUpdate(report);
        return new ModelAndView("redirect:/");
    }
}
