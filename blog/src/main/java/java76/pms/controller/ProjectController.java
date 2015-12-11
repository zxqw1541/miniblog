package java76.pms.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java76.pms.dao.ProjectDao;
import java76.pms.domain.Project;

@Controller
public class ProjectController {  
  @Autowired ProjectDao projectDao;
  
  @RequestMapping("/project/list.do")
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
    
    List<Project> projects = projectDao.selectList(paramMap);
    
    request.setAttribute("projects", projects);
    
    return "/project/ProjectList.jsp";
  }
  
  @RequestMapping("/project/add.do")
  public String add(
      String title,
      String startDate,
      String endDate,
      String member,
      HttpServletRequest request, HttpServletResponse response) 
          throws Exception {
    
    Project project = new Project();
    project.setTitle(title);
    project.setStartDate(Date.valueOf(startDate));
    project.setEndDate(Date.valueOf(endDate));
    project.setMember(member);

    projectDao.insert(project); 

    return "redirect:list.do";
  }
  
  @RequestMapping("/project/detail.do")
  public String detail(
      int no,
      HttpServletRequest request) throws Exception {

    Project project = projectDao.selectOne(no);
    request.setAttribute("project", project);
    
    return "/project/ProjectDetail.jsp";
  }

  @RequestMapping("/project/update.do")
  public String update(
      int no,
      String title,
      String startDate,
      String endDate,
      String member,
      HttpServletRequest request) 
          throws Exception {
    Project project = new Project();
    project.setTitle(title);
    project.setStartDate(Date.valueOf(startDate));
    project.setEndDate(Date.valueOf(endDate));
    project.setMember(member);
    project.setNo(no);

    if (projectDao.update(project) <= 0) {
      request.setAttribute("errorCode", "401");
      return "/project/ProjectAuthError.jsp";
    } 

    return "redirect:list.do";

  }
  
  @RequestMapping("/project/delete.do")
  public String delete(
      int no,
      HttpServletRequest request) throws Exception {

    if (projectDao.delete(no) <= 0) {
      request.setAttribute("errorCode", "401");
      return "/project/ProjectAuthError.jsp";
    } 

    return "redirect:list.do";
  }
}
