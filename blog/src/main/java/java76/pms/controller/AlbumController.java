package java76.pms.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java76.pms.dao.AlbumDao;
import java76.pms.domain.Album;
import java76.pms.util.MultipartHelper;

@Controller
@RequestMapping("/album/*")
public class AlbumController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired AlbumDao albumDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
        HttpServletRequest request) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    List<Album> albums = albumDao.selectList(paramMap);
    
    request.setAttribute("albums", albums);
    
    return "album/albumList";
  }
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "album/albumForm";
  }
      
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String add(Album album, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      album.setAttachFile(newFileName);
    }

    albumDao.insert(album);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws Exception {
    
    Album album = albumDao.selectOne(no);
    model.addAttribute("album", album);
    
    return "album/albumDetail";
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public String update(
      Album album, 
      MultipartFile file, 
      Model model) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      album.setAttachFile(newFileName);
    } else if (album.getAttachFile().length() == 0) {
      album.setAttachFile(null);
    }
    
    if (albumDao.update(album) <= 0) {
      model.addAttribute("errorCode", "401");
      return "album/albumAuthError";
    } 
    
    return "redirect:list.do";
  }
  
  @RequestMapping("delete")
  public String delete(
      int no, 
      String password,
      Model model) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    if (albumDao.delete(paramMap) <= 0) {
      model.addAttribute("errorCode", "401");
      return "album/albumAuthError";
    } 

    return "redirect:list.do";
  }
}
