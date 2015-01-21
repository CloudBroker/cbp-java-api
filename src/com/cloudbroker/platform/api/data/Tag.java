/**
 * Copyright 2013 CloudBroker GmbH, Zurich, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information please contact us via the following email
 * support@cloudbroker.com
 *
 */

package com.cloudbroker.platform.api.data;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Tag extends Base {

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("content")
	private String content = "";

	@XStreamAlias("user-id")
	private String userID;

	@XStreamAlias("month")
	private int month;

	@XStreamAlias("year")
	private int year;

	private HashMap<String, Double> fees = new HashMap<String, Double>();

	public String getContent() {
		return content;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public static String getURL() {
		return "/tags";
	}

	public HashMap<String, Double> getFees() {
		return fees;
	}

	@SuppressWarnings("unchecked")
	public <T extends Base> List<T> desirializeList(T model, byte[] xml)
			throws ClassNotFoundException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(new String(xml)));
		Document doc;
		List<T> result = new ArrayList<T>();
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(is);
			NodeList tagData = doc.getElementsByTagName("tag");
			for (int i = 0; i < tagData.getLength(); i++) {
				Tag tag = new Tag();
				Element element = (Element) tagData.item(i);
				tag.content = element.getElementsByTagName("content").item(0)
						.getTextContent();
				tag.name = element.getElementsByTagName("name").item(0)
						.getTextContent();
				tag.month = Integer
						.parseInt(element.getElementsByTagName("month").item(0)
								.getTextContent());
				tag.year = Integer.parseInt(element
						.getElementsByTagName("year").item(0).getTextContent());
				tag.userID = element.getElementsByTagName("user-id").item(0)
						.getTextContent();
				tag.id = element.getElementsByTagName("id").item(0)
						.getTextContent();
				NodeList fees = element.getElementsByTagName("fee");
				HashMap<String, Double> feesMap = new HashMap<String, Double>();
				for (int k = 0; k < fees.getLength(); k++) {
					Element feeElement = (Element) fees.item(k);
					feesMap.put(
							feeElement.getElementsByTagName("name").item(0)
									.getTextContent().trim(),
							Double.parseDouble(feeElement
									.getElementsByTagName("value").item(0)
									.getTextContent()));
				}
				tag.fees = feesMap;
				result.add((T) tag);
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T extends Base> T deserialize(T model, String xml)
			throws ClassNotFoundException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(new String(xml)));
		Document doc;
		Tag tag = new Tag();
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(is);
			NodeList tagData = doc.getElementsByTagName("tag");
			for (int i = 0; i < tagData.getLength(); i++) {

				Element element = (Element) tagData.item(i);
				tag.content = element.getElementsByTagName("content").item(0)
						.getTextContent();
				tag.name = element.getElementsByTagName("name").item(0)
						.getTextContent();
				tag.month = Integer
						.parseInt(element.getElementsByTagName("month").item(0)
								.getTextContent());
				tag.year = Integer.parseInt(element
						.getElementsByTagName("year").item(0).getTextContent());
				tag.userID = element.getElementsByTagName("user-id").item(0)
						.getTextContent();
				tag.id = element.getElementsByTagName("id").item(0)
						.getTextContent();
				NodeList fees = element.getElementsByTagName("fee");
				HashMap<String, Double> feesMap = new HashMap<String, Double>();
				for (int k = 0; k < fees.getLength(); k++) {
					Element feeElement = (Element) fees.item(k);
					feesMap.put(
							feeElement.getElementsByTagName("name").item(0)
									.getTextContent().trim(),
							Double.parseDouble(feeElement
									.getElementsByTagName("value").item(0)
									.getTextContent()));
				}
				tag.fees = feesMap;
			}

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return (T) tag;
	}
}
