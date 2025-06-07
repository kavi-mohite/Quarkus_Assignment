package org.acme;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
//import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Product;
import service.ProductService;

@Path("/hello")
public class GreetingResource  {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
    
    
    @Inject
    ProductService service;

    @GET
    @Path("/products")
    public Uni<List<Product>> getAllProducts() {
        return service.getAllProducts();
    }

    @GET
    @Path("/{id}")
    public Uni<Product> getProductById(@PathParam("id") Long id) {
        return service.getProductById(id);
    }

	
	  @POST 
	  public Uni<Product> addProduct(Product product) { return
	  service.createProduct(product); }
	  
	  @DELETE
	  @Path("/{id}") public Uni<Boolean> deleteProduct(@PathParam("id") Long id) {
	  return service.deleteProduct(id); }
	  
	  @WithTransaction
	  @PUT
	  @Path("/{id}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Uni<Product> updateProduct(@PathParam("id") Long id,
	  Product product){ return service.updateProductById(id, product); }
	  
	  @GET
	    @Path("/{id}/{count}")
	    public Uni<Response> checkStock(@PathParam("id") Long id, @PathParam("count") int count) {
		  return service.checkStock(id,count);}
	  
	  @GET
	  @Path("/sorted-by-price")
	  public Uni<List<Product>> getProductsSortedByPrice() {
	      return service.getProductSortByPrice();}
	  }

	        
	 
