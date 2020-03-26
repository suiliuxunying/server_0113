package com.shao.file_service.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Function
 */
public class Function {
    
    public static double gaussianDistribution(Double a ,Double b){
        Random random = new Random();
        return Math.sqrt(b)* random.nextGaussian()+a;
    }
    // 两位小数
    public static double format2(double value) {
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value));
        }
}