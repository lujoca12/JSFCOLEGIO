package Clases;

import java.io.Serializable;
 
public class Document implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
        private String type;
        private int id;
    
    public Document(String name, int id, String type) {
    	this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return name;
    }
}  