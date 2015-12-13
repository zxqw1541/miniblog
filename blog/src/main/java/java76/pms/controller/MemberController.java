package java76.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java76.pms.dao.MemberDao;
import java76.pms.domain.Member;
import java76.pms.util.MultipartHelper;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/member/*")
public class MemberController {
  public static final String SAVED_DIR = "/file";
  public static final Logger log = Logger.getLogger(MemberController.class);
  @Autowired MemberDao memberDao;

  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="email") String keyword,
      @RequestParam(defaultValue="asc") String align,
        HttpServletRequest request) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    paramMap.put("keyword", keyword);
    paramMap.put("align", align);
    
    List<Member> members = memberDao.selectList(paramMap);

    request.setAttribute("members", members);

    return "member/MemberList";

  }
  
  @RequestMapping("reg")
  public String register(Member member) throws Exception {
    
    log.debug("member.register()=> " + member);
    memberDao.insert(member);

    return "redirect:../main/first.do";
  }
 
  
  @RequestMapping("regAdmin")
  public String registerAdmin(
  		Member member,
  		HttpServletRequest request) throws Exception {
    
    log.debug("member.register()=> " + member);
    
    memberDao.insertAdmin(member);

    return "redirect:../main/first.do";
  } 
  
  @RequestMapping("detail")
  public String detail(
      String email,
      HttpServletRequest request) 
          throws Exception {

    Member member = memberDao.selectOne(email);
    request.setAttribute("member", member);

    return "member/MemberDetail";
  }
/*
  @RequestMapping(value="update", method=RequestMethod.POST)
  public String post(
      String name,
      String email,
      String tel,
      String cid,
      String photo,
      MultipartFile photofile,
      HttpServletRequest request) throws Exception {

    String newFileName = null;
    
    if (photofile.getSize() > 0) {
      newFileName = MultipartHelper.generateFilename(photofile.getOriginalFilename());  
      ServletContext servletContext = request.getServletContext();
      File attachfile = new File(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      photofile.transferTo(attachfile);
      
      makeThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName + ".png");
    }
    
    Member member = new Member();
    member.setName(name);
    member.setEmail(email);
    member.setTel(tel);
    member.setCid(cid);
    
    if (newFileName != null) {
      member.setPhoto(newFileName);
    } else if (newFileName == null && photo.length() > 0) {
      member.setPhoto(photo);
    }
    
    if (memberDao.update(member) <= 0) {
      request.setAttribute("errorCode", "401");
      return "member/MemberAuthError";
    } 

    return "redirect:list.do";
  }
  */
  @RequestMapping("delete")
  public String delete(
      String email,
      HttpServletRequest request) throws Exception {

    if (memberDao.delete(email) <= 0) {
      request.setAttribute("errorCode", "401");
      return "/member/MemberAuthError.jsp";
    }
    return "redirect:list.do";
  }
  
  private void makeThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .size(60,44)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }
}
