package entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet <Product>();
	
	// OneToMany => FetchType.LAZY
	// bidrectional은 parent에서 주가 된다. 
}
