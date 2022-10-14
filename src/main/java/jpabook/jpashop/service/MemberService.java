package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 이 트렌젝션을 권장. javax 트렌잭션 x

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 전용. (최적화)  // 데이터의 변경은 트렌젝션이 존재하여야한다.
@RequiredArgsConstructor // 생성자 인젝션 애노테이션 final이 붙어있는 인스턴스 변수로 생성자 주입을 해줌.
public class MemberService {

    private final MemberRepository memberRepository; // final 넣자.

/*
    @Autowired // Constructor injection <- 요즘 권장하는 기법. (생성자가 하나인 경우 스프링이 알아서 AutoWire를 해줌)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    //  회원 가입
    @Transactional // 얘가 우선순위 높음. ()속성이 없으면..
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
