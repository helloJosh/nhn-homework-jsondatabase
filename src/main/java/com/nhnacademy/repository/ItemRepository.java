package com.nhnacademy.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nhnacademy.JsonFileWorker;
import com.nhnacademy.domain.Item;
import com.nhnacademy.domain.UpdateHistory;

public class ItemRepository {
    UpdateHistoryRepository updateHistoryRepository = new UpdateHistoryRepository();
    JSONObject originalObject;
    JSONArray itemArray;
    // save
    public int save(Item item){
        originalObject = JsonFileWorker.readJSON();
        try{
            itemArray = originalObject.getJSONArray("item");
        } catch(JSONException e){
            System.out.println("item 배열이 없어 새로 성성합니다");
            itemArray = new JSONArray();
        }

        JSONObject itemJson = new JSONObject();
        itemJson.put("id", item.getId());
        itemJson.put("model", item.getModel());
        itemJson.put("energy", item.getEnergy());
        itemJson.put("attack", item.getAttack());
        itemJson.put("defense", item.getDefense());
        itemJson.put("movingSpeed", item.getMoveingSpeed());
        itemJson.put("attackSpeed", item.getAttackSpeed());
        itemArray.put(itemJson);

        originalObject.put("item", itemArray);
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of("Asia/Seoul")), "Item Saved"));
        return item.getId();
    }

    // update
    public void update(int itemId, ItemUpdateDto updateParam){
        originalObject = JsonFileWorker.readJSON();
        try{
            itemArray = originalObject.getJSONArray("item");
        } catch(JSONException e){
            System.out.println("item 배열이 없어 찾을수 없습니다.");
        }
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);
            int id = itemObject.getInt("id");
            if(id == itemId){
                if(updateParam.getModel()!= null)
                    itemObject.put("model",updateParam.getModel());
                if(updateParam.getEnergy()!=null)
                    itemObject.put("energy", (int)updateParam.getEnergy());
                if(updateParam.getAttack()!=null)
                    itemObject.put("attack", (int)updateParam.getAttack());
                if(updateParam.getDefense()!=null)
                    itemObject.put("defense", (int)updateParam.getDefense());
                if(updateParam.getMovingSpeed()!=null)
                    itemObject.put("movingSpeed", (int)updateParam.getMovingSpeed());
                if(updateParam.getAttackSpeed()!=null)
                    itemObject.put("attackSpeed", (int)updateParam.getAttackSpeed());
            }
        }
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of("Asia/Seoul")), "Item Updated"));
    }

    // remove
    public void remove(int itemId){
        originalObject = JsonFileWorker.readJSON();
        try{
            itemArray = originalObject.getJSONArray("item");
        } catch(JSONException e){
            System.out.println("item 배열이 없어 찾을수 없습니다.");
        }
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);
            int id = itemObject.getInt("id");
            if(id == itemId){
                itemArray.remove(i);
            }
        }
        JsonFileWorker.saveJSON(originalObject);
        updateHistoryRepository.save(new UpdateHistory(LocalDateTime.now(ZoneId.of("Asia/Seoul")), "Item Removed"));

    }

    public Optional<Item> findById(int itemId){
        Item item = null;
        originalObject = JsonFileWorker.readJSON();
        try{
            itemArray = originalObject.getJSONArray("item");
        } catch(JSONException e){
            System.out.println("item 배열이 없어 찾을수 없습니다.");
            return Optional.ofNullable(item);
        }
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);
            int id = itemObject.getInt("id");
            if(id == itemId){
                String model = itemObject.getString("model");
                int energy = itemObject.getInt("energy");
                int attack = itemObject.getInt("attack");
                int defense = itemObject.getInt("defense");
                int movingSpeed = itemObject.getInt("movingSpeed");
                int attackSpeed = itemObject.getInt("attackSpeed");
                System.out.println("found Item ID: " + id + ", Model: " + model + ", Energy: " + energy +
                        ", Attack: " + attack + ", Defense: " + defense + ", Moving Speed: " + movingSpeed +
                        ", Attack Speed: " + attackSpeed);
                item = new Item(id, model, energy, attack, defense, movingSpeed, attackSpeed);

                return Optional.ofNullable(item);
            }
        }
        return Optional.ofNullable(item);
    }

    // findAll
    public List<Item> findAll(){
        List<Item> itemList = new LinkedList<>();
        originalObject = JsonFileWorker.readJSON();
        try{
            itemArray = originalObject.getJSONArray("item");
        } catch(JSONException e){
            System.out.println("item 배열이 없어 목록표시가 불가능합니다.");
            return new LinkedList<>();
        }
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject itemObject = itemArray.getJSONObject(i);
            int id = itemObject.getInt("id");
            String model = itemObject.getString("model");
            int energy = itemObject.getInt("energy");
            int attack = itemObject.getInt("attack");
            int defense = itemObject.getInt("defense");
            int movingSpeed = itemObject.getInt("movingSpeed");
            int attackSpeed = itemObject.getInt("attackSpeed");
            System.out.println("Item ID: " + id + ", Model: " + model + ", Energy: " + energy +
                    ", Attack: " + attack + ", Defense: " + defense + ", Moving Speed: " + movingSpeed +
                    ", Attack Speed: " + attackSpeed);
            itemList.add(new Item(id, model, energy, attack, defense, movingSpeed, attackSpeed));
        }
        return itemList;
    }
    
}
