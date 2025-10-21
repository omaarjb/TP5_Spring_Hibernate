package test;


import dao.IDao;
import entities.Category;
import entities.Product;
import metier.CategoryDaoImpl;
import metier.ProductDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        IDao<Product> productDao = (IDao<Product>) context.getBean("productDaoImpl");
        IDao<Category> categoryDao = (IDao<Category>) context.getBean("categoryDaoImpl");

        Category category = new Category();
        category.setName("Électronique");
        categoryDao.create(category);


        Product product = new Product();
        product.setName("Produit 1");
        product.setPrice(100.0);
        product.setCategory(category);

        productDao.create(product);

        System.out.println("Produit sauvegardé : " + product.getName() + " dans la catégorie " + product.getCategory().getName());
    }
}