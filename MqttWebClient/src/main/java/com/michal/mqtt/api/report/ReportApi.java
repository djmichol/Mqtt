package com.michal.mqtt.api.report;

import com.michal.mqtt.api.groovyrule.model.response.ActionResponse;
import com.michal.mqtt.api.utils.DateUtils;
import com.michal.mqtt.api.utils.FileUtils;
import com.michal.mqtt.engine.raport.ReportEngine;
import com.michal.mqtt.engine.raport.fileGenerator.ReportGenerator;
import com.michal.mqtt.engine.raport.model.ReportModel;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportApi {

    private ReportEngine reportEngine;
    private ReportGenerator reportGenerator;

    public ReportApi(ReportEngine reportEngine, ReportGenerator reportGenerator) {
        this.reportEngine = reportEngine;
        this.reportGenerator = reportGenerator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<byte[]> getXLSXReport(@RequestParam String dateFrom) throws IOException {
        Date date = DateUtils.getDateFromText(dateFrom);
        List<ReportModel> reportModels = reportEngine.generateReportsForAllRooms(date);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=report.xlsx");
        return new ResponseEntity<>(FileUtils.toByteArray(reportGenerator.generateFile(reportModels)), responseHeaders, HttpStatus.OK);
    }


}
