package respository;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import model.Product;

@ApplicationScoped
public class ProductRespository /* extends PanacheEntity */  implements PanacheRepositoryBase<Product, Long>   {

	public Uni<List<Product>> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}



