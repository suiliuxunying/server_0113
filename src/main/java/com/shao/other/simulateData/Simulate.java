package com.shao.other.simulateData;

import com.shao.file_service.utils.Function;
import com.shao.other.dataBean.FuelData;

/**
 * Simulate
 */
public class Simulate {

  
//储罐的
// 	上位温度
// 	下位温度
// 	内部压力
// 	液位
//槽车
// 	温度
//  压力
    public static FuelData fuelData(FuelData fuelData){
        Double n = 0.03;
        Double m = 0.05;
        FuelData newFuelData = new FuelData(0);
        // 当温度小于阀值，就一点点增加 不减少 
        if (fuelData.getStorageTankLowerTemperature()<0.5) {
          
           newFuelData.setStorageTankInternalPressure(Function.format2( fuelData.getStorageTankInternalPressure()-Math.random()*n));
           
           newFuelData.setStorageTankLevel(Function.format2( fuelData.getStorageTankLevel()+Math.random()*n));

           newFuelData.setStorageTankLowerTemperature(Function.format2( fuelData.getStorageTankLowerTemperature()+Math.random()*n));

           newFuelData.setStorageTankUpperTemperature(Function.format2( fuelData.getStorageTankUpperTemperature()+Math.random()*n));

           newFuelData.setTankerLevel(Function.format2( fuelData.getStorageTankLevel()+Math.random()*n));

           newFuelData.setTankerPressure(Function.format2( fuelData.getTankerPressure()+Math.random()*n));

        } else {
            // 超过阀值 有降低风险的措施 所以加入减低的概率
            Double random =0.0;
            do {
                random= Function.gaussianDistribution(-0.2,10.0);
            } while (random>0.5||random<-0.5);
            // 3/4 的概率缓慢增加
            if (Math.random()>0.75) {

                newFuelData.setStorageTankInternalPressure(Function.format2( fuelData.getStorageTankInternalPressure()-Math.random()*n));
           
                newFuelData.setStorageTankLevel(Function.format2( fuelData.getStorageTankLevel()+Math.random()*n));
     
                newFuelData.setStorageTankLowerTemperature(Function.format2( fuelData.getStorageTankLowerTemperature()+Math.random()*n));
     
                newFuelData.setStorageTankUpperTemperature(Function.format2( fuelData.getStorageTankUpperTemperature()+Math.random()*n));
     
                newFuelData.setTankerLevel(Function.format2( fuelData.getStorageTankLevel()+Math.random()*n));
     
                newFuelData.setTankerPressure(Function.format2( fuelData.getTankerPressure()+Math.random()*n));

            } else {

                newFuelData.setStorageTankInternalPressure(Function.format2( fuelData.getStorageTankInternalPressure()-random*Math.random()*m));
           
                newFuelData.setStorageTankLevel(Function.format2( fuelData.getStorageTankLevel()+random*Math.random()*m));
     
                newFuelData.setStorageTankLowerTemperature(Function.format2( fuelData.getStorageTankLowerTemperature()+random*Math.random()*m));
     
                newFuelData.setStorageTankUpperTemperature(Function.format2( fuelData.getStorageTankUpperTemperature()+random*Math.random()*m));
     
                newFuelData.setTankerLevel(Function.format2( fuelData.getStorageTankLevel()+random*Math.random()*m));
     
                newFuelData.setTankerPressure(Function.format2( fuelData.getTankerPressure()+random*Math.random()*m));

            }
        }
        return newFuelData;
    }
    public static void main(String[] args) {
        FuelData fuelData = new FuelData(0);
        for (int i = 0; i < 100; i++) {
            fuelData=fuelData(fuelData);
            System.out.println(i+"  "+fuelData.toString());
        }
    }
}