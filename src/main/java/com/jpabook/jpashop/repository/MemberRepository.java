package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext 스프링부트(Spring Data Jpa)는 @Autowired 로 인젝션이 가능하게 지원을해줘서 이렇게 사용 가능
    private final EntityManager em;

    //@PersistenceUnit factory 를 직접 주입받을때 사용

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return  em.createQuery("select m from Member  m where m.name = : name",Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
