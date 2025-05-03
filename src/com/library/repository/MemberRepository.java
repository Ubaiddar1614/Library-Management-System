package com.library.repository;

import com.library.model.Member;
import com.library.exception.BookNotFoundException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository {
    private Map<String , Member>members;
    public MemberRepository()
    {
        this.members=new HashMap<>();

    }
    public void addMember(Member member)
    {
        members.put(member.getId() , member);
    }
}
