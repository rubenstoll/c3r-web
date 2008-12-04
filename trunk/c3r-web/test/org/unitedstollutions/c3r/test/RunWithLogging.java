package org.unitedstollutions.c3r.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.unitedstollutions.c3r.model.C3REngineTest;

public class RunWithLogging {
	
	public static void main(String[] args) {
		JUnitCore core = new JUnitCore();
		core.addListener(new RunListener() {
			@Override
			public void testFailure(Failure failure) throws Exception {
				logSomehow(failure);
			}
		});
		core.run(suite());
	}

	private static Test suite() {
		return new TestSuite(C3REngineTest.class);
	}

	private static void logSomehow(Failure failure) {
		// TODO: Fill in log4j stuff here
	}
}
