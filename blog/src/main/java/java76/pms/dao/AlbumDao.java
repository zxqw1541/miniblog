package java76.pms.dao;

import java.util.List;
import java.util.Map;

import java76.pms.domain.Album;

public interface AlbumDao {
  List<Album> selectList(Map<String,Object> paramMap);
  
  int insert(Album album);
  
  int delete(Map<String,Object> paramMap);
  
  int update(Album album);

  Album selectOne(int no);
}







