package com.nhnacademy.repository;
import com.nhnacademy.JsonFileWorker;
import com.nhnacademy.domain.Member;
import com.nhnacademy.domain.UpdateHistory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MemberRepository {
    public static final String MEMBER_KEY = "member";
    public static final String ZONE_ID = "Asia/Seoul";
    UpdateHistoryRepository updateHistoryRepository = new UpdateHistoryRepository();
    JSONObject originalObject;
    JSONArray memberArray;
    //save
    public int save(Member member){
        originalObject = JsonFileWorker.readJSON();
        try{
            memberArray = originalObject.getJSONArray(MEMBER_KEY);
        } catch(JSONException e){
            System.out.println("member 배열이 없어 새로 성성합니다");
            memberArray = new JSONArray();
        }
        JSONObject memberObject = new JSONObject();
        memberObject.put("id", member.getId());
        memberObject.put("name", member.getName());
        

        memberArray.put(memberObject);
        originalObject.put(MEMBER_KEY, memberArray);
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of(ZONE_ID)), "Member Saved"));

        return member.getId();
    }
    // update
    public void update(int memberId, String name){
        originalObject = JsonFileWorker.readJSON();
        try{
            memberArray = originalObject.getJSONArray(MEMBER_KEY);
        } catch(JSONException e){
            System.out.println("member 배열이 없어 찾을수 없습니다.");
        }
        for (int i = 0; i < memberArray.length(); i++) {
            JSONObject memberObject = memberArray.getJSONObject(i);
            int id = memberObject.getInt("id");
            if(id == memberId && (!name.equals(""))){
                memberObject.put("name", name);
            }
        }
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of(ZONE_ID)), "Member Updated"));
    }

    // remove
    public void remove(int memberId){
        originalObject = JsonFileWorker.readJSON();
        try{
            memberArray = originalObject.getJSONArray(MEMBER_KEY);
        } catch(JSONException e){
            System.out.println("member 배열이 없어 찾을수 없습니다.");
        }
        for (int i = 0; i < memberArray.length(); i++) {
            JSONObject itemObject = memberArray.getJSONObject(i);
            int id = itemObject.getInt("id");
            if(id == memberId){
                memberArray.remove(i);
            }
        }
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of(ZONE_ID)), "Member Removed"));

    }

    //findById
    public Optional<Member> findById(int memberId){
        Member member = null;
        originalObject = JsonFileWorker.readJSON();
        try{
            memberArray = originalObject.getJSONArray(MEMBER_KEY);
        } catch(NullPointerException e){
            System.out.println("member 배열이 없어 찾을 수 없습니다.");
            return Optional.ofNullable(member);
        }
        for (int i = 0; i < memberArray.length(); i++) {
            JSONObject memberObject = memberArray.getJSONObject(i);
            int id = memberObject.getInt("id");
            if(id == memberId){
                String name = memberObject.getString("name");
                System.out.println("Member ID: " + id + ", Name: " + name);
                member = new Member(id, name);
                return Optional.ofNullable(member);
            }
        }
        return Optional.ofNullable(member);
    }

    //findAll
    public List<Member> findAll(){
        List<Member> memberList = new LinkedList<>();
        originalObject = JsonFileWorker.readJSON();
        try{
            memberArray = originalObject.getJSONArray(MEMBER_KEY);
        } catch(JSONException e){
            System.out.println("member 배열이 없어 목록표시가 불가능합니다.");
            return new LinkedList<>();
        }
        for (int i = 0; i < memberArray.length(); i++) {
            JSONObject memberObject = memberArray.getJSONObject(i);
            int id = memberObject.getInt("id");
            String name = memberObject.getString("name");
            memberList.add(new Member(id, name));
        }
        return memberList;
    }
}
