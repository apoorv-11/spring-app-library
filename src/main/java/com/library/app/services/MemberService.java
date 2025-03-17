package com.library.app.services;

import java.util.List;

import com.library.app.entities.Member;


public interface MemberService {
    void addMember(Member member);
    Member getMemberById(Long id);
    void updateMember(Member member);
    void deleteMember(Long id);
    List<Member> getAllMembers();

}
