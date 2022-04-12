package kr.ac.ecommerce.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import kr.ac.ecommerce.entity.Product;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    private SessionFactory sessionFactory;

    public int createProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(product);
        session.flush();
        return id;
    }

    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product;
    }

    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Product";
        Query<Product> query = session.createQuery(hql, Product.class);

        List<Product> productList = query.getResultList();
        return productList;
    }

}
