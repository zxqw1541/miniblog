package java76.pms.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java76.pms.dao.AlbumDao;
import java76.pms.dao.ImageDao;
import java76.pms.dao.MemberDao;
import java76.pms.domain.Album;
import java76.pms.domain.Image;
import java76.pms.domain.Member;
import java76.pms.util.MultipartHelper;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/album/*")
public class AlbumController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired AlbumDao albumDao;
  @Autowired ImageDao imageDao;
  @Autowired MemberDao memberDao;
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
    
    return "album/AlbumList";
  }
  
  @RequestMapping(value="add", method=RequestMethod.GET)
  public String form(HttpSession session) {
  	if((Member)session.getAttribute("loginUser") == null) {
  		return "../auth/LoginEmpty";
  	}
  	
    return "album/AlbumForm";
  }
  
@RequestMapping(value="add", method=RequestMethod.POST)
public String add(
	Album album, 
	MultipartFile file,
	MultipartFile file2,
	MultipartFile file3,
	MultipartFile file4,
	MultipartFile file5,
	HttpSession session,
	HttpServletRequest request
	) throws Exception {

Image image = new Image();

if (file.getSize() > 0) {    	
  String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
  File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/" + newFileName);
  file.transferTo(attachfile); 
  
  album.setAttachFile(newFileName); 
  
  DiskFileItemFactory factory = new DiskFileItemFactory();
  ServletFileUpload upload = new ServletFileUpload(factory);
  List<FileItem> items = upload.parseRequest(request);
  
  File thumbnailFile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/s-" + newFileName);
  Thumbnails.of(attachfile)
  .size(60,44)
  .outputQuality(1.0)
  .toFile(thumbnailFile);
  
  image.setPath(newFileName);
}

int uniqKey = 0;
while(true) {    	
	if(albumDao.selectOne(uniqKey) == null)	break;
		++uniqKey;    	
}

album.setNo(uniqKey);
album.setMno(((Member)session.getAttribute("loginUser")).getMno());

albumDao.insert(album);
image.setAno(uniqKey);
imageDao.insert(image);

if (file2.getSize() > 0) {
  String newFileName = MultipartHelper.generateFilename(file2.getOriginalFilename());  
  File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/" + newFileName);
  file2.transferTo(attachfile);
  image.setPath(newFileName);
  imageDao.insert(image);
}

if (file3.getSize() > 0) {
  String newFileName = MultipartHelper.generateFilename(file3.getOriginalFilename());  
  File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/" + newFileName);
  file3.transferTo(attachfile);
  image.setPath(newFileName);
  imageDao.insert(image);
}

if (file4.getSize() > 0) {
  String newFileName = MultipartHelper.generateFilename(file4.getOriginalFilename());  
  File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/" + newFileName);
  file4.transferTo(attachfile);
  image.setPath(newFileName);
  imageDao.insert(image);
}

if (file5.getSize() > 0) {
  String newFileName = MultipartHelper.generateFilename(file5.getOriginalFilename());  
  File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                              + "/" + newFileName);
  file5.transferTo(attachfile);
  image.setPath(newFileName);
  imageDao.insert(image);
}


return "redirect:list.do";
}
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws Exception {
    
    Album album = albumDao.selectOne(no);
    model.addAttribute("album", album);
    
    return "album/AlbumDetail";
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
      return "album/AlbumAuthError";
    } 
    
    return "redirect:list.do";
  }
  
  @RequestMapping("delete")
  public String delete(
      int no,
      int mno,
      String password,
      Model model) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("mno", mno);
    paramMap.put("password", password);
    
    if (albumDao.delete(paramMap) <= 0) {
      model.addAttribute("errorCode", "401");
      return "album/AlbumAuthError";
    } 

    return "redirect:list.do";
  }
}
