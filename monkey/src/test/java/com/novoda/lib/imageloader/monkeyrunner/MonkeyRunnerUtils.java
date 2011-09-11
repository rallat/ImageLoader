package com.novoda.lib.imageloader.monkeyrunner;

import java.io.IOException;

import junit.framework.Assert;

public class MonkeyRunnerUtils {
	
	public static void execute(String file) {
		try {
			//String androidHome = System.getenv("ANDROID_HOME");
			//need to set android home
			String androidHome = "/home/luigi/Library/android-sdk-linux_86";
			Runtime.getRuntime().exec(androidHome + "/tools/monkeyrunner " +  file);
		} catch (IOException e) {
			Assert.fail("problem executing runtime : " + e.getMessage());
		}
		
	}

}
