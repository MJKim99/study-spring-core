package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/** 애플리케이션 전체를 설정하고 구성
 * 애플리케이션의 실제 동작에 필요한 구현 객체 생성
 * 생성한 객체 인터페이스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) -> 생성자 주입
 */
public class AppConfig {
    
    //기존 MemberServiceImpl에서 직접적으로 Repository를 넣어주지 않게 하기 위해 아래와 같이 작성
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
