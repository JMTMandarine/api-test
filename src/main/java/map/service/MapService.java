package map.service;

import map.domain.Apimap;

public interface MapService {
    // 서비스 인터페이스는 업무 로직을 규칙으로 정의한다.
    // 따라서 메서드 이름은 업무에 가깝게 명명해야 한다.
    void add(Apimap apimap);
    Apimap get(float x, float y);
    
}