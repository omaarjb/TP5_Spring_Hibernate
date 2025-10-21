package controllers;

import entities.Product;
import metier.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDaoImpl productDao;

    @PostMapping
    public Product create(@RequestBody Product product) {
        productDao.create(product);
        return product;
    }

    @GetMapping
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productDao.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        Product existing = productDao.findById(id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());
            productDao.update(existing);
        }
        return existing;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        Product existing = productDao.findById(id);
        if (existing != null) {
            productDao.delete(existing);
            return "Produit supprimé!";
        } else {
            return "Produit introuvable!";
        }
    }
}
