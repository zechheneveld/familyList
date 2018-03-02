package com.zech.familyList.data;

import com.zech.familyList.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberRepository {
    private List<Member> memberList = new ArrayList<>();

    public List<Member> getMemberList() {
        return memberList;
    }

    public void addMember(Member member){
        memberList.add(member);
    }

    public Member findMemberById(String id){
        for (Member zech : memberList){
            if (zech.getId().equals(id)){
                return zech;
            }
        }
        return null;
    }
}
