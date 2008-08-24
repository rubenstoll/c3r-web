/**
 * 
 */
package org.unitedstollutions.c3r.web;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author ruben.stoll
 * 
 */
public class HelloControllerTest extends TestCase {

	public void testHandleRequestView() throws Exception {
		HelloController controller = new HelloController();
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("jsp/hello.jsp", modelAndView.getViewName());
	}

}
