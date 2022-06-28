package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //할인 부분을 여기로 넘겨주어서 주문에서는 할인에 신경쓰지 않아도 됨
    /**
     * 할인 정책을 변경하려면 OrderServiceImpl를 수정해야 한다.
     * 역할과 구현을 분리하였고, 다형성을 활용하였으며 인터페이스와 구현 객체를 분리하였다.
     * 그러나 OCP, DIP 같은 객체 지향 설계 원칙을 충실하게 준수했다고 보긴 어렵다.
     * DIP -> 현재 클래스에서는 추상 인터페이스인 DiscountPolicy 말고도 구현 클래스인 Fix~Policy와 Rate~Policy도 의존하고 있다.
     * OCP -> 기능을 확장하니 클라이언트 코드에 영향이 가게 되었다. (Fix -> Rate)
     */
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy; //<- 구현 객체가 아닌 인터페이스에만 의존하게 하기 위해. DIP 지킴

    /**
     * 단순 위처럼 선언해버리면 NullPointerException 발생
     * 따라서 외부에서 주입해주어야 한다.
     */

    // AppConfig 생성 후. final로 선언되어 있으면 무조건 생성자를 통해야 한다.
    // 구체 클래스가 아닌, 인터페이스에만 의존 중
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
