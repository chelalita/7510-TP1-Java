package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase( "rules.db" );
		initMocks(this);
	}

	@Test
	public void test() {

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)"));

	}


	@Test
	public void invalidQuery() {

		Assert.assertFalse(this.knowledgeBase.answer("varon "));

	}

	@Test
	public void tio() {

		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas,cecilia,roberto)"));

	}

}
