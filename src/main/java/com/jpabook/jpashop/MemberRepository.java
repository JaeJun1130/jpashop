package com.jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    //SpringBoot 가 EntityManager 를 생성해서 주입해줌
    @PersistenceContext
    private EntityManager em;

    /**
     * 커맨드와 쿼리를 분리해라
     * 이 메서드를 호출 했을 때, 내부에서 변경(사이드 이펙트)가 일어나는 메서드인지, 아니면 내부에서 변경이 전혀 일어나지 않는 메서드인지 명확히 분리해라.
     */
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
