package com.shao.file_service.other.dataBean;

import com.shao.file_service.utils.Function;

import lombok.Data;

/**
 *FuelData
 */
@Data
public class FuelData {
//储罐的
// 	上位温度
// 	下位温度
// 	内部压力
// 	液位
//槽车
// 	温度
//  压力
    private double timestamp;
    private double storageTankUpperTemperature;
    private double storageTankLowerTemperature;
    private double storageTankInternalPressure;
    private double storageTankLevel;
    private double tankerLevel;
    private double tankerPressure;

    public FuelData(double[] list){
        this.timestamp=list[0];
        this.storageTankUpperTemperature=list[1];
        this.storageTankLowerTemperature=list[2];
        this.storageTankInternalPressure=list[3];
        this.storageTankLevel=list[4];
        this.tankerLevel=list[5];
        this.tankerPressure=list[6];
    }
    public FuelData(int divisor){
        if (divisor==0) {
            this.timestamp=System.currentTimeMillis();
           this. storageTankUpperTemperature=0;
           this.storageTankInternalPressure=1;
           this.storageTankLowerTemperature=0.03;
           this.storageTankLevel=0.05;
           this.tankerPressure=0;
           this.tankerLevel=0.0;
        }else if(divisor==1){
            
            double random = 0;
            do {
                random=Function.gaussianDistribution(0.0, 9.0);
            } while (random<0  || random>0.5);
            System.out.println("random:"+random);
            this.timestamp=System.currentTimeMillis();
            this.storageTankUpperTemperature= Function.format2(0+random+this.random());
            this. storageTankInternalPressure= Function.format2(1-random+this.random());
            this.storageTankLowerTemperature= Function.format2(0.03+random+this.random());
            this.storageTankLevel= Function.format2(0.05+random+this.random());
            this.tankerPressure= Function.format2(0+random+this.random());
            this.tankerLevel= Function.format2(0+random+this.random());
        }

    }


    private Double random(){
         // (Math.random()-0.5)*0.2 -0.1---0.1的随机数
        Double a = Function.format2(Math.random() * 0.05);
        System.out.println(a);
        return a;
    } 
    

}