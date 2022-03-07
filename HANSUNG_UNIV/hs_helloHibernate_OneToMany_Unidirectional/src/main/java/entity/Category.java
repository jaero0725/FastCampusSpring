package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name="catgory_id")
	private int id;
	
	private String name;
}
