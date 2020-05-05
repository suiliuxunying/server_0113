package com.shao.file_service.service;

import java.util.List;

import com.shao.file_service.other.dataBean.FuelData;

public interface SimulsteDataServiceInterface {
// 实现了一个实时处理显示
// 一个曲线绘制
// 一多个数据项对比分析的图
// 一个ml
    FuelData sDataRealTime(int i);
    FuelData sDataRealTime(FuelData fuelData);
    List<FuelData> sDataMakeCurve(String Path, int step, String keyValue) throws InterruptedException;
    void sDataContrast();
    void sDataSparkMl();

}