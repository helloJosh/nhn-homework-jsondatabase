package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonFileWorker {
    private String filePath;
    public static void saveJSON(JSONObject json){
        String filePath = "./record.json";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public static JSONObject readJSON() {
        String filePath = "./record.json";
        File file = new File(filePath);
        
        StringBuilder jsonString = new StringBuilder();
        if(file.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
            if(jsonString.toString().equals(""))
                return new JSONObject();
            return new JSONObject(new JSONTokener(jsonString.toString()));
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("새로운 파일을 생성했습니다.");
                } else {
                    System.out.println("파일을 생성하는데 실패했습니다.");
                }
            } catch (IOException e) {
                System.out.println("파일 생성 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
            return null;
        }
    }

    public void setfilePath(String filePath){
        this.filePath = filePath;
    }
    public String getfilePath(){
        return this.filePath;
    }
    
}
