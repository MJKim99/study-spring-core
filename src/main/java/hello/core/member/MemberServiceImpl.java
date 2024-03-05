package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    /**
     * MemberService가 역할 부분인 MemberRepository, 그리고 구현 부분인 MemoryMemberRepository 모두 의존하고 있다.
     * 이는 DIP 위반이고, 이후 변경이 있을 때도 문제가 생길 수 있다.
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //DIP를 지켜 추상화에만 의존하도록 한다.

    @Autowired // ac.getBean(MemberRepository.class)와 동일하게 동작
    // AppConfig 생성 후 MemberRepository에 무엇이 들어갈지 생성자를 통해서 주입하는 걸로 변경.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
