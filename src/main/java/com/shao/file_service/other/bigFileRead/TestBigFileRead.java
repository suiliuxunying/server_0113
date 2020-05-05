package com.shao.file_service.other.bigFileRead;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.PrintConversionEvent;

/**
 * Created by renhongqiang on 2019-07-02 14:23
 */
public class TestBigFileRead {

    public static void main(String[] args) throws InterruptedException {
        // AtomicLong counter = new AtomicLong(0);
        // String smallFilePath = "C:/Users/10703/Desktop/aa.txt";
        // String bigFilePath = "/Users/renhongqiang/Downloads/work-doc/2000W/test.csv";
        // BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, 
        // line -> System.out.println(String.format("total record: %s,line is: %s", 
        // counter.incrementAndGet(), line)));
        // // BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, line -> {});
        // BigFileReader bigFileReader = builder
        //         .threadPoolSize(1)
        //         .charset(StandardCharsets.UTF_8)
        //         .bufferSize(1024).build();
        // bigFileReader.start();
        List<Future>  futures = test();
        for (Future future : futures) {
              while(!future.isDone()){
                Thread.sleep(100);
                //   System.err.println("continue");
              }
        }
        System.err.println("Completion");
    }

    public static  List<Future> test() {
        AtomicLong counter = new AtomicLong(0);
        String smallFilePath = "C:/Users/10703/Desktop/aa.txt";
        String bigFilePath = "/Users/renhongqiang/Downloads/work-doc/2000W/test.csv";
        BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, line -> 
        System.out.println(String.format("total record: %s,line is: %s", counter.incrementAndGet(), line))
        );
        // BigFileReader.Builder builder = new BigFileReader.Builder(smallFilePath, line -> {});
        BigFileReader bigFileReader = builder
                .threadPoolSize(1)
                .charset(StandardCharsets.UTF_8)
                .bufferSize(1024).build();
                List<Future> futures =bigFileReader.start();
                return futures;
    }



    public static long getLineNumber(File file) {
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                lineNumberReader.skip(Long.MAX_VALUE);
                long lines = lineNumberReader.getLineNumber() + 1;
                fileReader.close();
                lineNumberReader.close();
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}