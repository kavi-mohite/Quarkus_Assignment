package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import model.Product;
import respository.ProductRespository;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
//@TestHTTPEndpoint(GreetingResource.class)
public class ProductResourceTest {
	
	@Inject
	Vertx vertx;
	
	WebClient client;
	
	@BeforeEach
	void setup(){
	client=WebClient.create(vertx,new WebClientOptions().setDefaultPort(8080));}
	
	
	 @Test
	    public void testCreateProduct() {
	        JsonObject product = new JsonObject()
	        		.put("name", "Mobile")
	        		.put("price", 500000.99)
	        		.put("quantity", 10);
	        
	        client.post("/products")
	        .sendJson(product, p ->{
	        	if(p.failed()) {
	        		p.cause().printStackTrace();
	        	}
	        	assertTrue(p.succeeded(),"Fail:"+(p.cause()!=null?p.cause().getMessage(): "Error"));
	        	assertEquals(201, p.result().statusCode());
	        	JsonObject obj=p.result().bodyAsJsonObject();
	        	assertNotNull(obj.getInteger("id"));
	        });
	 }
	        
	  @Test
	    public void testGetAllProducts() {
		  client.post("/products")
	        .send( p ->{
	        	if(p.failed()) {
	        		p.cause().printStackTrace();
	        	}
	        	assertTrue(p.succeeded(),"Fail prduct:"+(p.cause()!=null?p.cause().getMessage(): "Error"));
	        	assertEquals(201, p.result().statusCode());
	        	JsonObject obj=p.result().bodyAsJsonObject();
	        	assertNotNull(obj.getInteger("id"));
	        });
	    }

	        

    //private Product testProduct;

	
	/*
	 * @BeforeEach public void setup() { testProduct = new Product();
	 * testProduct.name = "Test Laptop"; testProduct.price = 999.99;
	 * testProduct.quantity = 100;
	 * repository.persist(testProduct).subscribe().with(p -> testProduct = p); }
	 */ 
    
	/*
	 * @BeforeEach public void setup() { testProduct=new Product();
	 * testProduct.name="Mobile"; testProduct.price=10000.99;
	 * testProduct.quantity=1; repository.persist(testProduct);
	 * 
	 * }
	 */

    // 🟢 Test GET /products (Retrieve All)
	/*
	 * @Test public void testGetAllProducts() { given() .when().get("/products")
	 * .then() .statusCode(200) .body("$.size()", greaterThan(0)); }
	 */
    

	// 🟢 Test GET /products/{id} (Retrieve by ID)
	/*
	 * @Test public void testGetProductById() { given() .pathParam("id",
	 * testProduct.id) .when().get("/{id}") .then() .statusCode(200) .body("name",
	 * equalTo("Test Laptop")); }
	 */
    // 🟢 Test POST /products (Create Product)
	/*
	 * @Test public void testCreateProduct() { Product newProduct = new Product();
	 * newProduct.name = "New Phone"; newProduct.price = 799.99;
	 * testProduct.quantity = 200;
	 * 
	 * given() .body(newProduct) .contentType(ContentType.JSON) .when().post()
	 * .then() .statusCode(201) .body("name", equalTo("New Phone")); }
	 */
    // 🟢 Test PUT /products/{id} (Update Product)
	/*
	 * @Test public void testUpdateProduct() { testProduct.price = 1099.99;
	 * 
	 * given() .pathParam("id", testProduct.id) .body(testProduct)
	 * .contentType(ContentType.JSON) .when().put("/{id}") .then() .statusCode(200)
	 * .body("price", equalTo(1099.99)); }
	 * 
	 * // 🟢 Test DELETE /products/{id} (Remove Product)
	 */
	/*
	 * @Test public void testDeleteProduct() { given()
	 * 
	 * .pathParam("id", testProduct.id) .when().delete("/{id}") .then()
	 * .statusCode(204); }
	 */    
    
    
}
