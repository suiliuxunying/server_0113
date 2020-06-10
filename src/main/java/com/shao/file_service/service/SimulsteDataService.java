package com.shao.file_service.service;

import java.nio.file.Path;
import java.util.List;

import com.shao.file_service.other.analysis.MakeCurve;
import com.shao.file_service.other.dataBean.FuelData;
import com.shao.file_service.other.simulateData.Simulate;

import org.springframework.stereotype.Service;
@Service
public class SimulsteDataService implements SimulsteDataServiceInterface {
	@Override
	public FuelData sDataRealTime(int i) {

		return new FuelData(i);
	}

	@Override
	public FuelData sDataRealTime(FuelData fuelData) {

		return new Simulate().fuelData(fuelData);
	}

	@Override
	public List<FuelData> sDataMakeCurve(String Path, int step, String keyValue) throws InterruptedException {
		return new MakeCurve().makeCurve(Path, step, keyValue);
	}
	@Override
	public List<FuelData> sDataMakeCurve2(String Path, int step, String keyValue) throws InterruptedException {
		return new MakeCurve().makeCurve2(Path, step, keyValue);
	}

	@Override
	public void sDataContrast() {

	}

	@Override
	public void sDataSparkMl() {

	}



}