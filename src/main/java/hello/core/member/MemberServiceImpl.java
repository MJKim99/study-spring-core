package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /**
     * MemberService가 역할 부분인 MemberRepository, 그리고 구현 부분인 MemoryMemberRepository 모두 의존하고 있다.
     * 이는 DIP 위반이고, 이후 변경이 있을 때도 문제가 생길 수 있다.
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
