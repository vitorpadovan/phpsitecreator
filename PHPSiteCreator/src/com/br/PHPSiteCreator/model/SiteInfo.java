package com.br.PHPSiteCreator.model;

import java.io.File;

public class SiteInfo {

	private static String pastaBase;
	private static String pastaModel;
	private static String pastaView;
	private static String pastaControl;
	private static String pastaDatabase;
	private static String pastaDocs;
	private static String siteName;

	/**
	 * 
	 */
	public SiteInfo(String nome) {
		super();
		SiteInfo.siteName = nome;
		SiteInfo.pastaBase = System.getProperty("user.home") + File.separator
				+ "Desktop" + File.separator + SiteInfo.siteName;
	}

	public static String getPastaBase() {
		return pastaBase;
	}

	public static String getSiteName() {
		return siteName;
	}

	public static String getPastaModel() {
		return pastaModel;
	}

	public static String getPastaView() {
		return pastaView;
	}

	public static String getPastaControl() {
		return pastaControl;
	}

	public static String getPastaDatabase() {
		return pastaDatabase;
	}

	public static String getPastaDocs() {
		return pastaDocs;
	}

}
