package map.service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import map.dao.MapDao;
import map.domain.Apimap;
import map.service.MapService;

@Service
public class MapServiceImpl implements MapService {

    MapDao mapDao;
    
    @Transactional(
            // 이 메서드를 호출하는 쪽에 이미 트랜잭션이 있으면 그 트랜잭션에 소속되게 하고,
            // 없으면 새 트랙잭션을 만들어 수행한다.
            propagation=Propagation.REQUIRED,
            // 메서드를 실행하는 중 Exception 예외가 발생하면 rollback을 수행한다.
            // 기본 값은 Exception.class 이다.
            rollbackFor=Exception.class)
    
            //AppConfig에 트랜잭션관리자 이름이 같기때문에 생략가능
            //transactionManager="transactionManager") 

    @Override
    public Apimap get(float x, float y) {
        return mapDao.findbyXY(x, y);
    }

    @Override
    public void add(Apimap apimap) {
         mapDao.insert(apimap);
    }
    

}
