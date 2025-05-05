
package com.library.repository;

import com.library.exception.MemberNotFoundException;
import com.library.model.Member;
import com.library.util.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository {
    private Map<String, Member> members;

    public MemberRepository() {
        this.members = new HashMap<>();
        loadMembersFromFile();
    }

    private void loadMembersFromFile() {
        List<Member> loadedMembers = FileHandler.loadMembers();
        for (Member member : loadedMembers) {
            members.put(member.getId(), member);
        }
    }

    public void saveToFile() {
        FileHandler.saveMembers(getAllMembers());
    }

    public void addMember(Member member) {
        members.put(member.getId(), member);
        saveToFile();
    }

    public Member getMemberById(String id) throws MemberNotFoundException {
        Member member = members.get(id);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + id + " not found");
        }
        return member;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public List<Member> searchMembersByName(String name) {
        List<Member> matchingMembers = new ArrayList<>();
        for (Member member : members.values()) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingMembers.add(member);
            }
        }
        return matchingMembers;
    }

    public void updateMember(Member member) throws MemberNotFoundException {
        if (!members.containsKey(member.getId())) {
            throw new MemberNotFoundException("Member with ID " + member.getId() + " not found");
        }
        members.put(member.getId(), member);
        saveToFile();
    }

    public void removeMember(String id) throws MemberNotFoundException {
        if (!members.containsKey(id)) {
            throw new MemberNotFoundException("Member with ID " + id + " not found");
        }
        members.remove(id);
        saveToFile();
    }
}
