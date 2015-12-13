package java76.pms.dao;

import java.util.List;

import java76.pms.domain.Image;

public interface ImageDao {
  int insert(Image image);

	List<Image> selectList(int no);

}







