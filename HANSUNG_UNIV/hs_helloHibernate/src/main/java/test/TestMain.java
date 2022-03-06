package test;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Product;

public class TestMain {
	
	//세션 팩토리 
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		
		//설정 객체
		Configuration conf = new Configuration(); 	
		conf.configure("hibernate.cfg.xml");		// Default 이름
		
		//세션 팩토리 
		sessionFactory = conf.buildSessionFactory();	//conf클래스로 부터 세션 팩토리를 얻어온다. 
		
		// 위와 같음.(Chained Method)  =>  sessionFactory = new Configuration().configure().buildSessionFactory();
		
		//세션 팩토리에서 세션 만든다.
		Session session = sessionFactory.openSession();	
		
		//세션에서 트랜젝션을 시작한다. 
		Transaction tx = session.beginTransaction();
		
		// ---------------- start -----------------
		
		Product product1 = new Product();
		product1.setName("Notebook1");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook");
		
		session.save(product1);	
		
		/*
			1. sql 문이 전혀 없이 객체지향적으로 프로그래밍 할 수 있다는 점.
		
		*/
		// ---------------- end -----------------
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
