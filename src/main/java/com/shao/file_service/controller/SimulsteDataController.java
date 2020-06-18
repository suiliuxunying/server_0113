package com.shao.file_service.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.shao.file_service.service.SimulsteDataService;
import com.shao.file_service.utils.Constant;
import com.shao.file_service.utils.Function;
import com.shao.file_service.other.dataBean.FuelData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin // 解决跨域问题！
@Controller
@ResponseBody
public class SimulsteDataController {
	@Autowired
	SimulsteDataService simulsteDataService;
	@Value("${basePath}")
	String basePath;
	@PostMapping(value="/sDataRealTime")
	public Object sDataRealTime(@RequestBody JSONObject data) throws Exception {
		// interval FuelData 
		// int keyValue = data.getIntValue("keyValue");
		int interval = data.getIntValue("interval");
		// interval=1;
		FuelData fuelData =JSONObject.toJavaObject(data.getJSONObject("item"), FuelData.class);
		// 存疑 是否可以筛选
		 if(fuelData==null){
			fuelData = simulsteDataService.sDataRealTime(0);
			System.err.println(111);
        }
        for (int i = 0; i < interval; i++) {
            fuelData = simulsteDataService.sDataRealTime(fuelData);
			String a=Function.reflect(fuelData,false);
			System.out.println(a);
		}
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("code", Constant.CODE_Correct);
		jsonObject.put("item", fuelData);
		return jsonObject;
    }
    
	@PostMapping(value="/sDataMakeCurve")
	public Object sDataMakeCurve(@RequestBody JSONObject data) throws InterruptedException {
			  System.err.println(data);
        String path=data.getString("path");
        String keyValue=data.getString("keyValue");
        int step = data.getIntValue("step");
				// path="C:/Users/10703/Desktop/aa.txt";
				System.err.println(basePath + path);
        // List<FuelData> list = simulsteDataService.sDataMakeCurve(basePath + path, step, keyValue);
        List<FuelData> list = simulsteDataService.sDataMakeCurve(path, step, keyValue);
        JSONObject jsonObject =new JSONObject();
				jsonObject.put("code", Constant.CODE_Correct);
				jsonObject.put("list", list);
				return jsonObject;
	}

	@PostMapping(value="/sDataMakeCurve2")
	public Object sDataMakeCurve2(@RequestBody JSONObject data) throws InterruptedException {
			  System.err.println(data);
        String path=data.getString("path");
        String keyValue=data.getString("keyValue");
        int step = data.getIntValue("step");
        // path="C:/Users/10703/Desktop/aa.txt";
        List<FuelData> list = simulsteDataService.sDataMakeCurve2( path, step, keyValue);
        // List<FuelData> list = simulsteDataService.sDataMakeCurve2(basePath + path, step, keyValue);
        JSONObject jsonObject =new JSONObject();
				jsonObject.put("code", Constant.CODE_Correct);
				jsonObject.put("list", list);
				return jsonObject;
	}

    // void sDataMakeCurve(String fileDri,int fileType);
    // void sDataContrast();
    // void sDataSparkMl();

	@PostMapping(value="/getFileName")
	public Object getFileName(@RequestBody JSONObject data)  {
		String[] fileNameList={"aa.txt","bb.txt","cc.txt","mm.txt"};
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("code", Constant.CODE_Correct);
		jsonObject.put("fileNameList", fileNameList);
		return jsonObject;
	}
	@PostMapping(value="/getKeyValue")
	public Object getKeyValue(@RequestBody JSONObject data)  {
		List<String> list =new ArrayList<>();
		Class cls = new FuelData(0).getClass();  
		Field[] fields = cls.getDeclaredFields();
		for(Field f : fields){
			f.setAccessible(true); 
			list.add(f.getName());
		}

		JSONObject keyValueList =new JSONObject();
		keyValueList.put("储罐的上位温度", list.get(1));
		keyValueList.put("储罐的下位温度", list.get(2));		
		keyValueList.put("储罐的内部压力", list.get(3));	
		keyValueList.put("储罐的液位", list.get(4));
		keyValueList.put("槽车温度", list.get(5));
		keyValueList.put("槽车压力", list.get(6));
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("code", Constant.CODE_Correct);
		jsonObject.put("keyValueList", keyValueList);
		return jsonObject;
	}
}