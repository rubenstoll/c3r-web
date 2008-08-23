/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author ruben.stoll
 *
 */
public class ThematicQueryGroupConfigurationTest extends TestCase {

	private ThematicQueryGroupConfiguration config;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {

		config = new ThematicQueryGroupConfiguration();
		
	}

	@Test 
	public void testSetAndGetDescription() {
		String defaultValue = "securite_incendie";
		String defaultTag = "security incendie";

		// check that value is not null
		assertNotNull(config.getValue());

		// check that the values match what we want (above)
		assertEquals(defaultValue, config.getValue());
		assertEquals(defaultTag, config.getLabel());
		
		// check that our hash map contains our default value
		HashMap<String,String> m = new HashMap<String,String>();
		m = config.getContents();
		assertEquals(defaultTag, m.get(defaultValue));
		
	}
	
	@Test 
	public void testSelectList() {

		// defautl value and label in thematic query group config class
		// Change this if defaults change.
		String defaultValue = "securite_incendie";
		String defaultLabel = "security incendie";

		// check that the default value for the thematic query group is
		// correctly set
		assertEquals(defaultValue, config.getValue());
		assertEquals(defaultLabel, config.getLabel());
		
		// check that the first value is the default value in the select list
//		HashMap<String,String> m = new HashMap<String,String>();
//		m = config.getSelectList();
//		Set<String> ms = m.keySet();
//		Object [] msa = ms.toArray();
//		assertEquals(defaultLabel, (String)msa[0]);
		
	}

}
