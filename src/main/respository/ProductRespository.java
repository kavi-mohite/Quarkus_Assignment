package respository;

//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import model.Product;

@ApplicationScoped
public class ProductRespository implements PanacheRepositoryBase<Product, Long> {

}



