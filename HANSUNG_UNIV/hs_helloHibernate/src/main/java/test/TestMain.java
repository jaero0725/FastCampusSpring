package test;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Product;

public class TestMain {

    //세션 팩토리
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        //설정 객체
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");        // Default 이름

        //세션 팩토리
        sessionFactory = conf.buildSessionFactory();    //conf클래스로 부터 세션 팩토리를 얻어온다.

        // 위와 같음.(Chained Method)  =>  sessionFactory = new Configuration().configure().buildSessionFactory();

        //세션 팩토리에서 세션 만든다.
        Session session = sessionFactory.openSession();

        //세션에서 트랜젝션을 시작한다.
        Transaction tx = session.beginTransaction();

        // ---------------- start -----------------

        // ## 2번
//		Product product1 = new Product();
//		product1.setName("Notebook1");
//		product1.setPrice(2000);
//		product1.setDescription("Awesome notebook");
//		
//		Serializable id1 = session.save(product1);	
//		
//		Product savedProduct = session.get(Product.class, id1);	// Cache에서 읽어옴.
//		
//		System.out.println("saved product : " + savedProduct);

        // ## 3번
        Product product1 = new Product();
        product1.setName("Notebook2");
        product1.setPrice(2000);
        product1.setDescription("Awesome notebook");

        Product product2 = new Product();
        product2.setName("Notebook1");
        product2.setPrice(3000);
        product2.setDescription("Powerful notebook");

        session.save(product1);
        session.save(product2);

        Query<Product> aQuery = session.createQuery("from Product order by name", Product.class);        // HQL
        List<Product> products = aQuery.getResultList();
        System.out.println("products : " + products);

        // ---------------- end -----------------
        tx.commit();
        session.close();
        sessionFactory.close();
		
		/*
		 		### 요약
		 		
				1. sql 문이 전혀 없이 객체지향적으로 프로그래밍 할 수 있다는 점.
				2. 저장하고 읽을때 console에서 읽은것이 먼저나옴 (차이가 있음)
				-> 실제 데이터베이스에서 읽은게 아니고, cache에 남아 있어서 바로 출력하기 때문에, commit 했을때 데이터베이스에 반영이 됨.
				=> 클래스 state -> DB 성능 향상 
				3. 복잡한 작업 (쿼리를 실제로 만들때) : createQuery(), HQL을 사용한다.
				4. hibernate_sequence 라는 테이블의 next_val이라는 컬럼 
				
		 */

    }
}
