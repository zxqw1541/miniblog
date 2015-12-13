package java76.pms.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java76.pms.dao.AlbumDao;
import java76.pms.dao.MemberDao;
import java76.pms.domain.Album;
import java76.pms.domain.Member;

@Controller
@RequestMapping("/album/*")
public class MainThemeController { 
  
  public static final String SAVED_DIR = "/attachfile";
  public static final int PERMISSION_ADMIN = 0;
  
  public static final Logger log = Logger.getLogger(MainThemeController.class);
  @Autowired AlbumDao albumDao;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("first")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
        HttpServletRequest request,
        HttpSession session) throws Exception {
    
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    List<Album> albums = albumDao.selectList(paramMap);

    Member admin = memberDao.selectAdmin(PERMISSION_ADMIN);
    
    session.setAttribute("admin", admin); 
    
    request.setAttribute("albums", albums);
    
    return "album/AlbumList";
  }
/*  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }
      
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String add(Board board, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    }

    boardDao.insert(board);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws Exception {
    
    Board board = boardDao.selectOne(no);
    model.addAttribute("board", board);
    
    return "board/BoardDetail";
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public String update(
      Board board, 
      MultipartFile file, 
      Model model) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }
    
    if (boardDao.update(board) <= 0) {
      model.addAttribute("errorCode", "401");
      return "board/BoardAuthError";
    } 
    
    return "redirect:list.do";
  }
  
  @RequestMapping("delete.do")
  public String delete(
      int no, 
      String password,
      Model model) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    if (boardDao.delete(paramMap) <= 0) {
      model.addAttribute("errorCode", "401");
      return "board/BoardAuthError";
    } 

    return "redirect:list.do";
  }*/
}
