package com.michal.mqtt.engine.raport.fileGenerator;

import com.michal.mqtt.engine.raport.model.ReportModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReportGenerator {

    File generateFile(List<ReportModel> reportData) throws IOException;
}
