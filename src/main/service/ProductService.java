package service;

import java.util.List;
import java.util.Map;

import jakarta.ws.rs.core.Response;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Product;
import respository.ProductRespository;

@ApplicationScoped
public class ProductService {

	 @Inject
	    ProductRespository repository;

	 @WithTransaction
	    public Uni<List<Product>> getAllProducts() {
	        return repository.findAll().list()
	        		.onItem().ifNull().continueWith(List.of());
	        				//() -> new RuntimeException("No product found"));
	    }

	    public Uni<Product> getProductById(Long id) {
	        return repository.findById(id);
	    }
	    
	    public Uni<Product> updateProductById(Long id, Product up_product) {
	        return  repository.findById(id)
	        		.onItem().ifNotNull().transformToUni(existingProduct ->{
	        			existingProduct.name=up_product.name;
	        			existingProduct.price=up_product.price;
	        			existingProduct.description=up_product.description;
	        			existingProduct.quantity=up_product.quantity;
	        			//return repository.getEntityManager().merge(existingProduct);
	        					//persistAndFlush(existingProduct);
	        			return repository.persistAndFlush(existingProduct);
	        		});
	        		//.call(existingProduct -> persistAndFlush(existingProduct));
	    }
	    
	    public Uni<Product> createProduct(Product product) {
//	        return Panache.withSession(() -> repository.persistAndFlush(product).map(p -> p));
	    	 return Panache.withTransaction(() -> repository.persistAndFlush(product).replaceWith(product));
	    }

	    public Uni<Boolean> deleteProduct(Long id) {
	        return repository.deleteById(id);
	    }

		public Uni<Response> checkStock(Long id, int count) {
			// TODO Auto-generated method stubserv
			return repository.findById(id)
					.onItem().ifNotNull().transform(p ->{
						boolean availble=p.quantity>=count;
						return Response.ok(Map.of("available",availble)).build();
					})
					.onFailure().recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
					//.onItem().ifNull().transform(() -> Uni.createForm().item(Response.status(Response.Status.NOT_FOUND).build()));
		    }

		public Uni<List<Product>> getProductSortByPrice() {
			// TODO Auto-generated method stub
			return repository.listAll(Sort.by("price").ascending());
		}
		
}
