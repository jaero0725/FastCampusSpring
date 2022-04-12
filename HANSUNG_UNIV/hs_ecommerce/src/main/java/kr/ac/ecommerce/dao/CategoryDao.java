package kr.ac.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import kr.ac.ecommerce.entity.Category;

@Repository
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    //Create
    public int addCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(category);
        session.flush();
        return id;
    }

}
