package com.shao.file_service.other.analysis;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;

import com.shao.file_service.other.bigFileRead.BigFileReader;
import com.shao.file_service.other.dataBean.FuelData;

import org.hibernate.validator.constraints.Length;

public class MakeCurve {
    private  List<FuelData> list= new ArrayList();
    private int length=7;
    private  int dataIndex;
    // 存读入的每一行的值  
    private double[] blist=new double[length];
    // 存每一个步长周期的最值
    private double[] maxlist=new double[length];
    private double[] minlist=new double[length];
    // 步长 计数     
    private int count;
    public   List<FuelData> makeCurve(String path,int step,String keyValue) throws InterruptedException {
        if(step<3){
            return null;
        }
        // 得到关键值序号
        Class cls = new FuelData(0).getClass();  
        Field[] fields = cls.getDeclaredFields();
        for(Field f : fields){
            f.setAccessible(true); 
            if(keyValue==f.getName())
                this.dataIndex = f.getModifiers();
        }
       this.count=0;
        BigFileReader.Builder builder = new BigFileReader.Builder(path, line -> {
            // System.err.println(line);
            if(line!=null){
                String[] slist = line.split(" ");
                for (int i=0 ;i<length;i++) {
                    blist[i] = Double.parseDouble(slist[i]);
                }
                // 定步长+加极值
                if(count==0){
                    this.list.add(new FuelData(blist));
                }else if (count==1){
                    for(int i=0;i<length;i++) minlist[i]=maxlist[i]=blist[i];
                }
                else{
                    if (maxlist[dataIndex]<blist[dataIndex]) maxlist=blist;
                    if (minlist[dataIndex]>=blist[dataIndex]) minlist=blist;
                }
                this.count++;
                if(count==step){
                    count=0;
                    this.list.add(new FuelData(maxlist));
                    this.list.add(new FuelData(minlist));
                }
            }
        });
        BigFileReader bigFileReader = builder
                .threadPoolSize(1) //分片数
                .charset(StandardCharsets.UTF_8)
                .bufferSize(1024).build();
        List<Future>  futures= bigFileReader.start();
        for (Future future : futures) {
                while(!future.isDone()){
                Thread.sleep(100);
                //   System.err.println("continue");
                }
        }
        System.err.println("Completion");
        // System.err.print(list);
        return this.list;

   } 
   public   List<FuelData> makeCurve2(String path,int step,String keyValue) throws InterruptedException {
    if(step<3){
        return null;
    }
    // 得到关键值序号
    Class cls = new FuelData(0).getClass();  
    Field[] fields = cls.getDeclaredFields();
    for(Field f : fields){
        f.setAccessible(true); 
        if(keyValue==f.getName())
            this.dataIndex = f.getModifiers();
    }
   this.count=0;
    BigFileReader.Builder builder = new BigFileReader.Builder(path, line -> {
        // System.err.println(line);
        if(line!=null){
            String[] slist = line.split(" ");
            for (int i=0 ;i<length;i++) {
                blist[i] = Double.parseDouble(slist[i]);
            }
            //定步长截取

            //第一个进去
            if(count==0){
                this.list.add(new FuelData(blist));
            }else if (count==(step/3)){
                for(int i=0;i<length;i++) minlist[i]=maxlist[i]=blist[i];
            }
            else if (count==(step/3*2)){
                for(int i=0;i<length;i++) minlist[i]=maxlist[i]=blist[i];
            }
            this.count++;
            if(count==step){
                count=0;
                this.list.add(new FuelData(maxlist));
                this.list.add(new FuelData(minlist));
            }
        }
    });
    BigFileReader bigFileReader = builder
            .threadPoolSize(1) //分片数
            .charset(StandardCharsets.UTF_8)
            .bufferSize(1024).build();
    List<Future>  futures= bigFileReader.start();
    for (Future future : futures) {
            while(!future.isDone()){
            Thread.sleep(100);
            //   System.err.println("continue");
            }
    }
    System.err.println("Completion");
    // System.err.print(list);
    return this.list;

} 

}