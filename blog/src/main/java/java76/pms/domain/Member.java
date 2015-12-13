package java76.pms.domain;

import java.io.Serializable;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String name;
  protected String email;
  protected String password;
  protected int	flag;
  protected int mno;
  protected String mfile;
  
  public int getFlag() {
		return flag;
	}
	

	public void setFlag(int flag) {
		this.flag = flag;
	}
	

	public int getMno() {
		return mno;
	}
	

	public void setMno(int mno) {
		this.mno = mno;
	}
	

	public String getMfile() {
		return mfile;
	}
	

	public void setMfile(String mfile) {
		this.mfile = mfile;
	}
	

	public Member() {}
  
  public Member(String name, String email) {
    this.email = email;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
	public String toString() {
	return "Member [name=" + name + ", email=" + email + ", password=" + password + ", flag=" + flag + ", mno=" + mno
	    + ", mfile=" + mfile + "]";
	}
  
 
  
}









