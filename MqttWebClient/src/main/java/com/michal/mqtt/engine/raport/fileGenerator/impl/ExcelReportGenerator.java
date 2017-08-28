package com.michal.mqtt.engine.raport.fileGenerator.impl;

import com.michal.mqtt.engine.raport.fileGenerator.ReportGenerator;
import com.michal.mqtt.engine.raport.model.ReportModel;
import com.michal.mqtt.engine.raport.model.SensorDataModel;
import com.michal.mqtt.engine.raport.model.SensorTypeDataModel;
import com.michal.mqtt.engine.raport.model.SensorModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReportGenerator implements ReportGenerator {

    private static final int sensorHeaderRow = 0;
    private static final int typeHeaderRow = 1;
    private static final int dataHeaderRow = 2;
    private static final int firstDataRow = 3;

    private static final String FILE_NAME = "D:/Mqtt/test.xlsx";

    @Override
    public File generateFile(List<ReportModel> reportData) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        System.out.println("Creating excel");
        for (ReportModel reportModel : reportData) {
            XSSFSheet sheet = workbook.createSheet("Report data for room " + reportModel.getRoomName());

            Row sensorHeader = sheet.createRow(sensorHeaderRow);
            Row typeHeader = sheet.createRow(typeHeaderRow);
            Row dataTypeRow = sheet.createRow(dataHeaderRow);

            int colNum = 0;
            for (SensorModel sensorModel : reportModel.getSensors()) {
                setSensorHeader(colNum, sensorHeader, sensorModel);
                for (SensorTypeDataModel sensorTypeDataModel : sensorModel.getSensorTypeDatumModels()) {
                    int initColumnNum = colNum;
                    int rowNum = firstDataRow;

                    colNum = setDataHeaders(colNum, typeHeader, dataTypeRow, sensorTypeDataModel);
                    for (SensorDataModel sensorDataModel : sensorTypeDataModel.getData()) {
                        Row row = getRow(sheet, rowNum);
                        setData(initColumnNum, initColumnNum + 1, sensorDataModel, row);
                        rowNum++;
                    }
                }
                colNum = addEmptyColumn(sheet, colNum);
            }
        }

        File file = prepareFile();
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        workbook.close();
        return file;
    }

    private File prepareFile() throws IOException {
        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

    private Row getRow(XSSFSheet sheet, int rowNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        return row;
    }

    private void setData(int sensorDateColumn, int sensorDataColumn, SensorDataModel sensorDataModel, Row row) {
        Cell cell = row.createCell(sensorDateColumn);
        cell.setCellValue(sensorDataModel.getDate().toString());
        cell = row.createCell(sensorDataColumn);
        cell.setCellValue(sensorDataModel.getData());
    }

    private int setDataHeaders(int colNum, Row typeHeader, Row dataTypeRow, SensorTypeDataModel sensorTypeDataModel) {
        Cell cell = typeHeader.createCell(colNum);
        cell.setCellValue(sensorTypeDataModel.getType());
        cell = dataTypeRow.createCell(colNum++);
        cell.setCellValue("Date");
        cell = dataTypeRow.createCell(colNum++);
        cell.setCellValue("Data");
        return colNum;
    }

    private void setSensorHeader(int colNum, Row sensorHeader, SensorModel sensorModel) {
        Cell cell = sensorHeader.createCell(colNum);
        cell.setCellValue(sensorModel.getSensorName() + " " + sensorModel.getNodeName());
    }

    private int addEmptyColumn(XSSFSheet sheet, int columnNum) {
        Row row = sheet.getRow(0);
        row.createCell(columnNum++);
        return columnNum;
    }
}
