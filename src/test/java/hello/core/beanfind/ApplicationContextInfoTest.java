package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // Bean 하나하나에 대한 메타 정보

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { // 직접 등록한 애플리케이션 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("APPLICATION >> name = " + beanDefinitionName + ", object = " + bean);
            }
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) { // 스프링이 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("INFRASTRUCTURE >> name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }
}
