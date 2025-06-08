package model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
//import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
//import io.quarkus.hibernate.reactive.panache.PanacheEntity;
//import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import io.smallrye.mutiny.Uni;
@Entity
@Table(name="Product")
public class Product extends PanacheEntity {
	 	//@Id
//	 	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="product_seq")
//	 	@SequenceGenerator(name="product_seq", sequenceName = "product_seq", allocationSize = 1)
//	    private Long id;
	 	@Column(nullable = false)
	    public String name;
	 	@Column(nullable = false)
	    public String description;
	 	@Column(nullable = false)
	    public Double price;
	 	@Column(nullable = false)
	    public int quantity;
	
	 	public static Uni<Product> findByName(String name){
	 		return find("name",name).firstResult();
	 	}
}
