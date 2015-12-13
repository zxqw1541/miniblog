package java76.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Image  implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int       ano;
  protected String    path;
  
	@Override
	public String toString() {
	return "Image [ano=" + ano + ", path=" + path + "]";
	}

	public int getAno() {
		return ano;
	}
	

	public void setAno(int ano) {
		this.ano = ano;
	}
	

	public String getPath() {
		return path;
	}
	

	public void setPath(String path) {
		this.path = path;
	}
	
	
  
  
  
}