package com.library.app.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.app.entities.Member;
import com.library.app.repositories.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao memberDao;

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberDao.getMemberById(id);
    }

    @Override
    public void updateMember(Member member) {
        memberDao.updateMember(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberDao.deleteMember(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }
}
