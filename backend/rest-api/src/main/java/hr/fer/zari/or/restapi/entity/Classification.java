package hr.fer.zari.or.restapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="classification")
public class Classification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8797272601007833999L;
	@Id
	@Column(name="classname")
	private String className;
	private String description;
	
	@OneToMany(mappedBy = "classification")
	private List<Breed> breeds;
	
	protected Classification() {
		
	}
	
	public Classification(String className, String description) {
		super();
		this.className = className;
		this.description = description;
	}
	
	public String getClassName() {
		return className;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String toString() {
		return "Classification [className=" + className + ", description=" + description + "]";
	}
	
}
