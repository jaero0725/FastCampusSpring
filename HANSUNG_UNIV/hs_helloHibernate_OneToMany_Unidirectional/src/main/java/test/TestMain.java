package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Category;
import entity.Product;

public class TestMain {
	
	//세션 팩토리 
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Category category1 = new Category();
		category1.setName("컴퓨터");
		
		Category category2 = new Category();
		category2.setName("자동차");
		
		Product product1 = new Product();
		product1.setName("Notebook1");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		product1.setCategory(category1);
		
		Product product2 = new Product();
		product2.setName("Notebook2");
		product2.setPrice(3000);
		product2.setDescription("Powerful notebook");
		product2.setCategory(category1);

		Product product3 = new Product();
		product3.setName("Sonata");
		product3.setPrice(3000000);
		product3.setDescription("Powerful car");
		product3.setCategory(category2);
		
		//Transient 상태
		Session session = sessionFactory.openSession();	
		Transaction tx = session.beginTransaction();
		
		session.save(product1);	//CascateType.ALL => 따라서, 카테고리도 같이 저장된다. 별도로 저장하지 않아도 자동으로 저장됨. 
		session.save(product2);
		session.save(product3);
		
		//category2인 product3을 삭제하면? category2도 삭제된다.
		session.delete(product3);
		
		/*  ## 결과 :
			Hibernate: delete from product where product_id=?
			.12:04:14.036 [main] TRACE o.h.type.descriptor.sql.BasicBinder - binding parameter [1] as [INTEGER] - [4] 
			Hibernate: delete from category where catgory_id=?
			.12:04:14.040 [main] TRACE o.h.type.descriptor.sql.BasicBinder - binding parameter [1] as [INTEGER] - [5] 
		 */
		
		//product1, product2 -> category1, product2 는 없는 category1를 가리키게된다. (심각한 문제) 
		product1.setCategory(null);  //product1 -> category를 끊고 삭제함.  <= 해결책 
		session.delete(product1);
		/*
			## 결과 : Excetpion 발생
			Exception in thread "main" javax.persistence.EntityNotFoundException: deleted object would be re-saved by cascade (remove deleted object from associations): [entity.Category#2]
	
			=> delete 전에,  product1.setCategory(null);  //product1 -> category를 끊고 삭제함.  <= 해결책 
		 */
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
