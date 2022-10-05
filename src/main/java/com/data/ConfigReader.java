package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties p;

	public ConfigReader() throws IOException {
		File f = new File(
				System.getProperty("user.dir")+"\\propertyFile\\Login.properties");
		FileInputStream fis = new FileInputStream(f);
		p = new Properties();
		p.load(fis);

	}

	public String getUrl() {
		String url = p.getProperty("url");
		return url;

	}

	public String getEmail() {
		String email = p.getProperty("email");
		return email;

	}

	public String getpass() {
		String pass = p.getProperty("password");
		return pass;

	}
	
	public String patientkpid() {
		String patientkpid = p.getProperty("patient");
		return patientkpid;

	}
	
	public String doctorKpid() {
		String doctorKpid = p.getProperty("Doctor");
		
		return doctorKpid;
		
		

	}

}