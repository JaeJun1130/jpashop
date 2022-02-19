package com.jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    /**
     * Transactional 가 Test에 붙어있으면 마지막에 롤백처리 해버린다.
     * @Rollback(value = false) false 로하면 커밋해버림.
     */
    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("Member 를 생성하고 조회한다.")
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        //영속성컨텍스트에서 식별자가 같은면 같은 Context 로 인식한다. TRUE
        assertThat(findMember).isEqualTo(member);
    }
}