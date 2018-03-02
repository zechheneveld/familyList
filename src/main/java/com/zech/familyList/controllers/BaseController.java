package com.zech.familyList.controllers;

import com.zech.familyList.data.MemberRepository;
import com.zech.familyList.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping("/getAllMembers")
    public @ResponseBody List<Member> getAllMembers(){
        return memberRepository.getMemberList();
    }

    @RequestMapping("/get/{id}")
    public @ResponseBody     List<Member> findMemberById(@PathVariable String id){
        Member foundMember = memberRepository.findMemberById(id);

        List<Member> responseArray = new ArrayList<>();
        responseArray.add(foundMember);
        return responseArray;
    }

    @RequestMapping(value = "/add/member", method = RequestMethod.POST)
    public @ResponseBody Member postMember(@RequestBody Map<String, Object> payload){
        String newId = String.valueOf(payload.get("id"));
        String newFName = String.valueOf(payload.get("firstName"));
        String newLName = String.valueOf(payload.get("lastName"));

        Member newMember = new Member(newId, newFName, newLName);
        memberRepository.addMember(newMember);
        return newMember;
    }
    @RequestMapping("/")
    public String baseRoute(){
        return "index";
    }
}
