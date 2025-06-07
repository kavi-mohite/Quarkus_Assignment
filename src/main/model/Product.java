package model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
//import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
//import io.quarkus.hibernate.reactive.panache.PanacheEntity;
//import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Product extends PanacheEntityBase {
	 	@Id
	 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    public Long id;
	    public String name;
	    public String description;
	    public Double price;
	    public int quantity;
	
}
