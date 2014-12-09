package com.br.phpSiteCreator.model;

import java.io.File;

public class SiteInfo {

	private static String pathName;
	private static String siteName;

	public SiteInfo(String pathName, String siteName) {
		SiteInfo.pathName = pathName;
		SiteInfo.siteName = siteName;
	}

	public static String getPathName() {
		return SiteInfo.pathName;
	}

	public static String getSiteName() {
		return SiteInfo.siteName;
	}
	
	public static String getFullSitePath()
	{
		return SiteInfo.pathName+File.separator+SiteInfo.siteName;
	}
}
