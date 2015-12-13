package java76.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Album  implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int       no;
  protected String    title;
  protected String    content;
  protected int       views;
  protected Date      createdDate;
  protected String    attachFile; // 컬럼명 = afile
  protected int       mno;
  protected String    site;
  
  
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getAttachFile() {
		return attachFile;
	}
	
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	
	public int getMno() {
		return mno;
	}
	
	public void setMno(int mno) {
		this.mno = mno;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
	return "Album [no=" + no + ", title=" + title + ", content=" + content + ", views=" + views + ", createdDate="
	    + createdDate + ", attachFile=" + attachFile + ", mno=" + mno + ", site=" + site + "]";
	}
	
  
  
  
  
  
}