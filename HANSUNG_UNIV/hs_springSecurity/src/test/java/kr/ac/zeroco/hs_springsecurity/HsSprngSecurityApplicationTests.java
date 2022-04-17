package kr.ac.zeroco.hs_springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class HsSpringSecurityApplicationTests {

	@Autowired
	private PasswordEncoder encoder;

	@Test
	void generateHashedPassword() {
		String pwd = encoder.encode("alicepw");
		System.out.println(pwd);
	}

}
