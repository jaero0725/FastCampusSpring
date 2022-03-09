package kr.ac.hansung.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="offer")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  //Hibernate 5 selects the GenerationType.TABLE -> hibernate_sequence 
	@Column(name="offer_id", nullable=false, updatable=false) //hibernate에게 추가적인 속성을 준다. 
	private int id;	//Primary key
	
	/** updatable 
     * (Optional) The SQL fragment that is used when 
     * generating the DDL for the column.
     * <p> Defaults to the generated SQL to create a
     * column of the inferred type.
     */
	
	@Size(min=2, max=100, message="Name must be between 2 and 100 chars")
	private String name;
	
	@Email(message="Please provide a valid email address")
	@NotEmpty(message="The email address cannot be empty")
	private String email;
	
	@Size(min=5, max=100, message="Text must be between 5 and 100 chars")
	private String text;
}