// Spring WebMVC 설정을 다루는 클래스 
package map.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 이 클래스가 IoC 컨테이너가 사용해야 할 설정 정보를 담고 있는 클래스임을
// 프론트 컨트롤러에게 알려주기 위해서는 다음 애노테이션을 반드시 붙여야 한다.
@Configuration

// 다음 애노테이션을 붙이면 <mvc:annotation=driven/> 이 설정과 같은 의미이다.
// mvc 관련 설정을 활성화 시킨다.
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    // 만약 MVC설정을 좀 더 세밀하게 제어하고 싶다면 다음 인터페이스를 구현하라
    
    public WebConfig() {
        System.out.println("WebConfig()호출됨");
    }

}
