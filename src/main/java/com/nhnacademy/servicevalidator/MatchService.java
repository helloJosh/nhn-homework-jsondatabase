package com.nhnacademy.servicevalidator;

import java.util.List;

import com.nhnacademy.domain.Match;
import com.nhnacademy.repository.MatchRepository;

public class MatchService {
    MatchRepository matchRepository;
    public void save(int count, int win){
        matchRepository.save(new Match(count, win));
    }
    public void findAll(){
        List<Match> list = matchRepository.findAll();
        for(Match m : list){
            System.out.println(m);
        }
    }
}
