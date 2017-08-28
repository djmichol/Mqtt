package com.michal.mqtt.engine.raport;

import com.michal.dao.api.SensorDataDao;
import com.michal.mqtt.engine.raport.model.ReportModel;
import com.michal.mqtt.engine.raport.model.SensorDataModel;
import com.michal.mqtt.engine.raport.model.SensorTypeDataModel;
import com.michal.mqtt.engine.raport.model.SensorModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReportEngine {

    private SensorDataDao sensorDataDao;

    public ReportEngine(SensorDataDao sensorDataDao) {
        this.sensorDataDao = sensorDataDao;
    }

    public List<ReportModel> generateReportsForAllRooms(Date date) {
        List<com.michal.dao.model.mqttdata.SensorData> sensorsData = sensorDataDao.getAllDataFrom(date);
        List<ReportModel> reportsForRooms = new ArrayList<>();

        sensorsData.forEach(sensorData -> {
            String sensorName = sensorData.getSensor().getName();
            String roomName = sensorData.getSensor().getRoom().getName();
            String nodeName = sensorData.getSensor().getNode().getName();

            if (isRoomExistInReport(reportsForRooms, roomName)) {
                ReportModel reportModel = getReportModel(reportsForRooms, sensorData);
                if (isSensorExistInRoom(reportsForRooms, sensorName, nodeName)) {
                    SensorModel sensorModel = getSensorModel(reportModel, sensorData);
                    if (isTypeExist(sensorModel, sensorData.getType())) {
                        SensorTypeDataModel sensorTypeDataModelInReport = getSensorData(sensorModel, sensorData.getType());
                        sensorTypeDataModelInReport.getData().add(SensorDataModel.of(sensorData.getTimestamp(), sensorData.getData()));
                    } else {
                        SensorTypeDataModel sensorTypeDataModelInReport = getNewSensorData(sensorData);
                        sensorModel.getSensorTypeDatumModels().add(sensorTypeDataModelInReport);
                    }
                } else {
                    reportModel.getSensors().add(getNewSensorModel(sensorData));
                }
            } else {
                reportsForRooms.add(getNewReportModel(sensorData));
            }
        });

        return reportsForRooms;
    }

    private ReportModel getReportModel(List<ReportModel> reportsForRooms, com.michal.dao.model.mqttdata.SensorData sensorData) {
        for (ReportModel reportModel : reportsForRooms) {
            if (reportModel.getRoomName().equals(sensorData.getSensor().getRoom().getName())) {
                return reportModel;
            }
        }
        return null;
    }

    private SensorTypeDataModel getSensorData(SensorModel sensorModel, String type) {
        for (SensorTypeDataModel sensorTypeDataModel : sensorModel.getSensorTypeDatumModels()) {
            if (sensorTypeDataModel.getType().equals(type)) {
                return sensorTypeDataModel;
            }
        }
        return null;
    }

    private SensorModel getSensorModel(ReportModel reportsForRooms, com.michal.dao.model.mqttdata.SensorData sensorData) {
        for (SensorModel sensorModel : reportsForRooms.getSensors()) {
            if (sensorModel.getNodeName().equals(sensorData.getSensor().getNode().getName()) && sensorModel.getSensorName().equals(sensorData.getSensor().getName())) {
                return sensorModel;
            }
        }
        return null;
    }

    private ReportModel getNewReportModel(com.michal.dao.model.mqttdata.SensorData inSensorData) {
        ReportModel reportModel = new ReportModel();
        reportModel.setRoomName(inSensorData.getSensor().getRoom().getName());
        reportModel.setSensors(new ArrayList<>(Arrays.asList(getNewSensorModel(inSensorData))));
        return reportModel;
    }

    private SensorModel getNewSensorModel(com.michal.dao.model.mqttdata.SensorData inSensorData) {
        SensorModel sensorModel = new SensorModel();
        sensorModel.setNodeName(inSensorData.getSensor().getNode().getName());
        sensorModel.setSensorName(inSensorData.getSensor().getName());
        sensorModel.setSensorTypeDatumModels(new ArrayList<>(Arrays.asList(getNewSensorData(inSensorData))));
        return sensorModel;
    }

    private SensorTypeDataModel getNewSensorData(com.michal.dao.model.mqttdata.SensorData inSensorData) {
        SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel();
        sensorTypeDataModel.setType(inSensorData.getType());
        sensorTypeDataModel.setData(new ArrayList<>(Arrays.asList(SensorDataModel.of(inSensorData.getTimestamp(), inSensorData.getData()))));
        return sensorTypeDataModel;
    }

    private boolean isRoomExistInReport(List<ReportModel> reportsForRooms, String roomName) {
        if (reportsForRooms.stream().map(reportModel -> reportModel.getRoomName()).collect(Collectors.toList()).contains(roomName)) {
            return true;
        }
        return false;
    }

    private boolean isSensorExistInRoom(List<ReportModel> reportsForRooms, String sensorName, String nodeName) {
        for (ReportModel reportModel : reportsForRooms) {
            for (SensorModel sensorModel : reportModel.getSensors()) {
                if (sensorModel.getSensorName().equals(sensorName) && sensorModel.getNodeName().equals(nodeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isTypeExist(SensorModel sensorModel, String type) {
        for (SensorTypeDataModel sensorTypeDataModel : sensorModel.getSensorTypeDatumModels()) {
            if (sensorTypeDataModel.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
