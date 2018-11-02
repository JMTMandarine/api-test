package map.dao;

import map.domain.Apimap;

public interface MapDao {
    Apimap findbyXY(float x, float y);
    int insert(Apimap apimap);
}
