package com.nhnacademy.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nhnacademy.JsonFileWorker;
import com.nhnacademy.domain.Match;
import com.nhnacademy.domain.UpdateHistory;

public class MatchRepository {
    public static final String MATCH_KEY = "match";
    UpdateHistoryRepository updateHistoryRepository = new UpdateHistoryRepository();
    JSONObject originalObject;
    JSONArray matchArray;
    // save
    public void save(Match match){
        originalObject = JsonFileWorker.readJSON();
        try{
            matchArray = originalObject.getJSONArray(MATCH_KEY);
        } catch(JSONException e){
            System.out.println("match 배열이 없어 새로 성성합니다");
            matchArray = new JSONArray();
        }
        JSONObject matchObject = new JSONObject();
        matchObject.put("count", match.getCount());
        matchObject.put("win", match.getWin());

        matchArray.put(matchObject);
        originalObject.put(MATCH_KEY, matchArray);
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of("Asia/Seoul")), "Match Saved"));
    }
    
    //findAll
    public List<Match> findAll(){
        List<Match> matchList = new LinkedList<>();
        originalObject = JsonFileWorker.readJSON();
        try{
            matchArray = originalObject.getJSONArray(MATCH_KEY);
        } catch(JSONException e){
            System.out.println("item 배열이 없어 목록표시가 불가능합니다.");
            return new LinkedList<>();
        }
        for (int i = 0; i < matchArray.length(); i++) {
            JSONObject matchObject = matchArray.getJSONObject(i);
            int count = matchObject.getInt("count");
            int win = matchObject.getInt("win");
            matchList.add(new Match(count, win));
        }
        return matchList;
    }
}
