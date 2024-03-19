package com.nhnacademy.servicevalidator;

import java.util.List;

import com.nhnacademy.domain.Item;
import com.nhnacademy.repository.ItemRepository;
import com.nhnacademy.repository.ItemUpdateDto;

public class ItemServiceValidator {
    ItemRepository itemRepository;
    public void save(int id, String model, int energy, int attack, int defense, int movingSpeed, int attackSpeed){
        if(!itemRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("중복된 아이디입니다.");
        if(!(0 <= energy && energy <= 10000))
            throw new IllegalArgumentException("체력은 0~10000까지만 설정할 수 있습니다.");
        if(!(0 <= attack && attack <= 100))
            throw new IllegalArgumentException("공격력은 0~100까지만 설정할 수 있습니다.");
        if(!(0 <= defense && defense <= 50))
            throw new IllegalArgumentException("방어력은 0~50까지만 설정할 수 있습니다.");
        if(!(0 <= movingSpeed && movingSpeed <= 100))
            throw new IllegalArgumentException("이동속도는 0~100까지만 설정할 수 있습니다.");
        if(!(0 <= attackSpeed && attackSpeed <= 100))
            throw new IllegalArgumentException("공격속도는 0~100지만 설정할 수 있습니다.");

        Item item = new Item(id, model, energy, attack, defense, movingSpeed, attackSpeed);
        itemRepository.save(item);
    }
    public void update(int id, String model, int energy, int attack, int defense, int movingSpeed, int attackSpeed){
        if(!(0 <= energy && energy <= 10000))
            throw new IllegalArgumentException("체력은 0~10000까지만 설정할 수 있습니다.");
        if(!(0 <= attack && attack <= 100))
            throw new IllegalArgumentException("공격력은 0~100까지만 설정할 수 있습니다.");
        if(!(0 <= defense && defense <= 50))
            throw new IllegalArgumentException("방어력은 0~50까지만 설정할 수 있습니다.");
        if(!(0 <= movingSpeed && movingSpeed <= 100))
            throw new IllegalArgumentException("이동속도는 0~100까지만 설정할 수 있습니다.");
        if(!(0 <= attackSpeed && attackSpeed <= 100))
            throw new IllegalArgumentException("공격속도는 0~100지만 설정할 수 있습니다.");
        ItemUpdateDto updateParam = new ItemUpdateDto(model, energy, attack, defense, movingSpeed, attackSpeed);
        itemRepository.update(id, updateParam);
    }
    public void remove(int id){
        itemRepository.remove(id);
    }

    public void findById(int id){
        System.out.println(itemRepository.findById(id).orElse(null));
    }

    public void findAll(){
        List<Item> list = itemRepository.findAll();
        for(Item i : list){
            System.out.println(i);
        }
    }
}
