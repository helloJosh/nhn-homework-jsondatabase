package com.nhnacademy.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nhnacademy.JsonFileWorker;
import com.nhnacademy.domain.UpdateHistory;

public class UpdateHistoryRepository {
    JSONObject originalObject;
    JSONArray updateHistoryArray;
    // save
    public void save(UpdateHistory updateHistory){
        originalObject = JsonFileWorker.readJSON();
        try{
            updateHistoryArray = originalObject.getJSONArray("updateHistory");
        } catch(JSONException e){
            System.out.println("updateHistory 배열이 없어 새로 성성합니다");
            updateHistoryArray = new JSONArray();
        }
        JSONObject updateHistoryObject = new JSONObject();
        updateHistoryObject.put("history", updateHistory.getHistory());
        updateHistoryObject.put("updatedContext", updateHistory.getUpdatedContext());
        

        updateHistoryArray.put(updateHistoryObject);
        originalObject.put("updateHistory", updateHistoryArray);
        JsonFileWorker.saveJSON( originalObject);
    }
    
    //findAll
    public List<UpdateHistory> findAll(){
        List<UpdateHistory> updateHistoryList = new LinkedList<>();
        originalObject = JsonFileWorker.readJSON();
        try{
            updateHistoryArray = originalObject.getJSONArray("updateHistory");
        } catch(JSONException e){
            System.out.println("member 배열이 없어 목록표시가 불가능합니다.");
            return new LinkedList<>();
        }
        for (int i = 0; i < updateHistoryArray.length(); i++) {
            JSONObject updateHistoryObject = updateHistoryArray.getJSONObject(i);
            String history = updateHistoryObject.getString("history");
            LocalDateTime localDateTime = LocalDateTime.parse(history, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String updatedContext = updateHistoryObject.getString("updatedContext");
            System.out.println("History: " + history + ", Updated Context: " + updatedContext);
            updateHistoryList.add(new UpdateHistory(localDateTime, updatedContext));
        }
        return updateHistoryList;
    }
}
