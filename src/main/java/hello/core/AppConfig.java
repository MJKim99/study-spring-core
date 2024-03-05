package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 애플리케이션 전체를 설정하고 구성
 * 애플리케이션의 실제 동작에 필요한 구현 객체 생성
 * 생성한 객체 인터페이스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) -> 생성자 주입
 *
 * `ApplicationContext` : 스프링 컨테이너
 * `@Configuration` 애노테이션을 사용하여 `AppConfig` 클래스가 애플리케이션의 `구성(설정)`을 정의하는 클래스임을 명시
 * `AppConfig` 내 `@Bean` 애노테이션이 명시된 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
 */

@Configuration
public class AppConfig {
    
    //기존 MemberServiceImpl에서 직접적으로 Repository를 넣어주지 않게 하기 위해 아래와 같이 작성
    @Bean // 스프링 컨테이너에 등록되며, `name`을 설정하지 않는다면 Bean 이름은 메서드명으로 등록된다.
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
