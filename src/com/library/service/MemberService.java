package com.library.service;
import com.library.exception.MemberNotFoundException;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import com.library.util.IdGenerator;

import java.util.List;


public class MemberService {
    private MemberRepository memberRepository;
    private IdGenerator idGenerator;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.idGenerator = new IdGenerator("M");
    }

    public Member registerMember(String name, String contactInfo) {
        String id = idGenerator.generateId();
        Member member = new Member(id, name, contactInfo);
        memberRepository.addMember(member);
        return member;
    }

    public Member getMemberById(String id) throws MemberNotFoundException {
        return memberRepository.getMemberById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }

    public List<Member> searchMembersByName(String name) {
        return memberRepository.searchMembersByName(name);
    }

    public void updateMember(Member member) throws MemberNotFoundException {
        memberRepository.updateMember(member);
    }

    public void removeMember(String id) throws MemberNotFoundException {
        memberRepository.removeMember(id);
    }
}
