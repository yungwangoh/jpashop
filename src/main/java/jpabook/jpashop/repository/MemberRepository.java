package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // select m from Member m where m.name = ? 로 스프링 데이터 jpa가 자동적으로 구현체를 생성해줌.
    List<Member> findByName(String name);
}
