package map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@ComponentScan(basePackages="bitcamp.java110.cms",
               excludeFilters=@Filter( // 필터제외
                       type=FilterType.REGEX, //정규표현식으로 무엇을 제외할지 설정
                       pattern="bitcamp.java110.cms.web.*" // 이경로의 모든것 제외
                       ))*/

// Spring IoC 컨테이너에게 이 클래스가 컨테이너를 위한 설정정보를 담고 있는
// 클래스라는 것을 알려주기 위해 다음 애노테이션을 추가한다.
//@Configuration 
//@PropertySource("classpath:/map/conf/jdbc.properties")
//@MapperScan("map.dao") //Mybatis에서 자동으로 DAO를 생성할 때 사용할 인터페이스가 들어 있는 패키지 설정
// 트랜잭션 관리자를 활성화 하려면 다음 애노테이션을 붙여야 한다.
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    Environment env;
    
    public AppConfig() {
        System.out.println("AppConfig() 호출됨!");
    }

    @Bean(destroyMethod="close")
    public DataSource datasource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("jdbc.driver"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));
        ds.setDefaultAutoCommit(false);

        return ds;

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory
            (DataSource dataSource,
             ApplicationContext appCtx) {

        try {
            SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

            // DB 커넥션풀을 관리해주는 객체를 꼽는다.
            factory.setDataSource(dataSource);

            // SQL맵퍼 파일에서 도메인 객체의 별명을 사용하려면  
            // 도메인 객체가 들어 있는 패키지를 지정해야 한다.
            // 그러면 Mybatis가 해당 패키지의 모든 클래스에 대해 별명을 자동으로 생성할 것이다.
            factory.setTypeAliasesPackage("map.domain");

            // SQL 맵퍼 파일 경로를 등록한다.
            factory.setMapperLocations(appCtx.getResources(
                    "classpath:/map/mapper/**/*.xml"));// **를 지정해주는 이유는 하위폴더까지 지정해줌

            return factory.getObject();
        }catch(Exception e) {
            throw new RuntimeException(e); 
        }

    }
    
    // 트랜잭션 관리자의 이름은 반드시 "transactionManager"이어야 한다.
    // 그래서 메서드 이름을 다음과 같이 지은 것이다.
    // Spring에서 트랜잭션 관리자를 찾을 때 이 이름으로 찾는다.
    // 만약, 트랜잭션 이름을 다른 이름으로 지었다면  
    // 트랙잭션 관리 설정에서 그 이름을 알려줘야 한다.
    
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        // 트랜잭션 관리자가 하는 일은 DB 커넥션의 commit과 rollback을 다루는 것이다.
        // 따라서 트랜잭션 관리자는 DB 커넥션을 제공해주는 
        // DataSource(DB커넥션풀)가 필요하다.
        // 그래서 트랜잭션 관리자를 만들 때 반드시 DataSource 객체를 넘겨줘야 한다.
        // 물론, 관리자 객체를 만든 후에 세터를 호출해서 넘겨줘도 된다.
        return new DataSourceTransactionManager(dataSource);
        
    }

    /*public static void main(String[] args) {

        ApplicationContext iocContainer = 
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("===========================================");

        int count = iocContainer.getBeanDefinitionCount();
        System.out.printf("bean 개수 = %d\n",count);

        String[] names = iocContainer.getBeanDefinitionNames();
        for(String name : names) {
            System.out.printf("=> %s:%s\n",
                    name,
                    iocContainer.getType(name).getName() );
        }
        System.out.println("===========================================");



        ManagerService s = (ManagerService) iocContainer.getBean(ManagerService.class);
        System.out.println(s.list(1,5));


    }*/
}
