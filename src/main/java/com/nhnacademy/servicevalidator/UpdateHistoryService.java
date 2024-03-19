package com.nhnacademy.servicevalidator;

import java.util.List;

import com.nhnacademy.domain.UpdateHistory;
import com.nhnacademy.repository.UpdateHistoryRepository;

public class UpdateHistoryService {
    UpdateHistoryRepository updateHistoryRepository = new UpdateHistoryRepository();
    public void findAll(){
        List<UpdateHistory> list  = updateHistoryRepository.findAll();
        for(UpdateHistory u : list){
            System.out.println(u);
        }
    }
}
