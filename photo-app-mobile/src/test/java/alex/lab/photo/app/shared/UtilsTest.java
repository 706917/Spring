package alex.lab.photo.app.shared;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest {
	
	@Autowired
	Utils utils;

	@BeforeEach
	void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGenerateUserId() {	
	
	String userId = utils.generateUserId(20);
	String userId2 = utils.generateUserId(20);
	
	assertNotNull(userId);
	assertNotNull(userId2);
	assertTrue(userId.length() == 20);
	assertTrue(!userId.equalsIgnoreCase(userId2));
	}
	
	

	@Test
	@Disabled
	final void testHastokenExpired() {
		fail("Not yet implemented");
	}

}
