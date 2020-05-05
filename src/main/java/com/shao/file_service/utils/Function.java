package com.shao.file_service.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.ibatis.javassist.compiler.ast.FieldDecl;

import ch.qos.logback.core.joran.conditional.ElseAction;

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


    public static void writeMethod( String fileName, String content,int writeType)
    {
        String fileDir=Constant.BasicDir+fileName;
        try
        {
            if(writeType==0){
                //使用这个构造函数时，如果存在kuka.txt文件，
                //则先把这个文件给删除掉，然后创建新的kuka.txt
                FileWriter writer=new FileWriter(fileDir);
                writer.write(content);
                writer.close();
            }else if(writeType==1){
                //使用这个构造函数时，如果存在kuka.txt文件，
                //则直接往kuka.txt中追加字符串
                FileWriter writer=new FileWriter(fileDir,true);
                // SimpleDateFormat format=new SimpleDateFormat();
                // String time=format.format(new Date());
                writer.write(content+'\n');
                writer.close();
            }else if(writeType==2){
                //注意：上面的例子由于写入的文本很少，使用FileWrite类就可以了。但如果需要写入的
                //内容很多，就应该使用更为高效的缓冲器流类BufferedWriter。
                /**
                 * 使用BufferedWriter类写文本文件
                 */
                BufferedWriter out=new BufferedWriter(new FileWriter(fileDir));
                out.write(content);
                out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
                // out.write(content+'\n');
                out.close();
            }
            
            
                
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 使用FileReader类读文本文件
     */
    public static void readMethod1()
    {
            String fileName="C:/kuka.txt";
            int c=0;
            try
            {
                    FileReader reader=new FileReader(fileName);
                    c=reader.read();
                    while(c!=-1)
                    {
                            System.out.print((char)c);
                            c=reader.read();
                    }
                    reader.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    /**
     * 使用BufferedReader类读文本文件
     */
    public static void readMethod2()
    {
            String fileName="c:/kuka.txt";
            String line="";
            try
            {
                    BufferedReader in=new BufferedReader(new FileReader(fileName));
                    line=in.readLine();
                    while (line!=null)
                    {
                            System.out.println(line);
                            line=in.readLine();
                    }
                    in.close();
            } catch (IOException e)
            {
                    e.printStackTrace();
            }
    } 
    // 用于bean中数据一行打印
    public static String reflect(Object e,Boolean isHader) throws Exception{  
        String result="";
        // 获取一个类的属性名和值
        Class cls = e.getClass();  
        Field[] fields = cls.getDeclaredFields();
        if(isHader){
            for(Field f : fields)
                {f.setAccessible(true); 
                result+=f.getName()+" ";}
        }
        else {
            for(Field f : fields)
                { f.setAccessible(true); 
                    result+=f.get(e)+" ";}
        }
        return result;  
    } 
}