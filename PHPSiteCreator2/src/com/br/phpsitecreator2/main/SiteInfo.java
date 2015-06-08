/**
 * 
 */
package com.br.phpsitecreator2.main;

import java.io.File;

/**
 * @author VitorHugo
 *
 */
public class SiteInfo {

	public final static String SITE_NAME = "NIPP";
	public final static String PATH_NAME = System.getProperty("user.home")
			+ File.separator + "Desktop";
	public final static String SYSTEM_DIR = SiteInfo.PATH_NAME + File.separator
			+ SiteInfo.SITE_NAME;
}
