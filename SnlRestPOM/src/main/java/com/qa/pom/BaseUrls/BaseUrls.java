package com.qa.pom.BaseUrls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class BaseUrls {
	int x = 0, y = 0;
	String data, baseUrl = "";

	YamlReader yr;
	Object ob;
	Map m;

	SAXReader sr = new SAXReader();
	Document d;

	CSVReader cr;
	List<String[]> l;
	int p = 0, q=0;

	public String getBaseUrl(String type, String filePath, String name, String fileType)
			throws DocumentException, IOException {
		if ((fileType.equalsIgnoreCase(".yaml") || fileType.equalsIgnoreCase(".yml"))) {
			yr = new YamlReader(new FileReader(filePath));
			ob = yr.read();
			m = (Map) ob;
			baseUrl = (String) m.get(name);
		} else {
			if (fileType.equalsIgnoreCase(".xml")) {
				d = sr.read(new File(filePath));
				baseUrl = d.selectSingleNode("//" + type + "/" + name).getText();
			} 
			else 
			{
				if (fileType.equalsIgnoreCase(".txt")) 
				{
					if (x == 0 && type.equalsIgnoreCase("baseUrls")) 
					{
						System.out.println("\nSearching in : " + filePath);
						data = read(filePath);
						x++;
					} 
					else 
					{
						if (y == 0 && type.equalsIgnoreCase("credentials"))
						{
							System.out.println("\nSearching in : " + filePath);
							data = read(filePath);
							y++;
						}
					}
				} 
				else 
				{
					if (fileType.equalsIgnoreCase(".csv")) 
					{
						if (p == 0 && type.equalsIgnoreCase("baseUrls")) 
						{
							System.out.println("\nSearching in : " + filePath);
							data = read(filePath);
							p++;
						} 
						else 
						{
							if (q == 0 && type.equalsIgnoreCase("credentials"))
							{
								System.out.println("\nSearching in : " + filePath);
								data = read(filePath);
								q++;
							}
						}
					}
				}
				String[] str = data.split("->");
				System.out.println("Searched for : " + name);
				for (int i = 0; i < str.length; i++) {
					if (str[i].split(": ")[0].equalsIgnoreCase(name)) {
						baseUrl = str[i].split(": ")[1];
						break;
					}
				}
			}
		}

		return baseUrl;
	}

	public String read(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		if (filePath.contains(".txt")) {
			// File f = new File(filePath);
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String s;

				while ((s = br.readLine()) != null) {
					sb.append(s);
					sb.append("->");
				}

			} catch (IOException e) {
				System.out.println(e);
			}
		}
		if (filePath.contains(".csv")) {
			FileReader r = new FileReader(filePath);
			cr = new CSVReaderBuilder(r).withSkipLines(1).build();
			l = cr.readAll();
			for (String[] sr : l) {
				for (String s : sr) {
					sb.append(s);
					sb.append("->");
				}
			}
		}
		return sb.toString();
	}
}