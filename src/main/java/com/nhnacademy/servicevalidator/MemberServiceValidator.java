package com.nhnacademy.servicevalidator;

import java.util.List;
import com.nhnacademy.domain.Member;
import com.nhnacademy.repository.MemberRepository;

public class MemberServiceValidator {
    MemberRepository memberRepository;

    public void save(int id, String name){
        if(!memberRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("중복된 아이디입니다.");
        memberRepository.save(new Member(id, name));
    }
    public void update(int id, String name){
        memberRepository.update(id, name);
    }
    public void remove(int id){
        memberRepository.remove(id);
    }

    public void findById(int id){
        System.out.println(memberRepository.findById(id).orElse(null));
    }

    public void findAll(){
        List<Member> list = memberRepository.findAll();
        for(Member m : list){
            System.out.println(m);
        }
    }
}
