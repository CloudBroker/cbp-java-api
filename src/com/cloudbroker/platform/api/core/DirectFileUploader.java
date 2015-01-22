/**
 * Copyright 2015 CloudBroker GmbH, Zurich, Switzerland
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

package com.cloudbroker.platform.api.core;

import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cloudbroker.platform.api.connector.HttpMethodExecutor;
import com.cloudbroker.platform.api.data.DataFile;

public class DirectFileUploader {
	public static boolean uploadFile(DataFile dataFile, String xml) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList data = doc.getElementsByTagName("result");
			if (data == null || data.getLength() == 0
					|| data.item(0).getTextContent().equals("Not supported")) {
				return false;
			}
			String url = "";
			HashMap<String, String> headers = new HashMap<String, String>();
			NodeList nodes = data.item(0).getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeName().equals("url")) {
					url = nodes.item(i).getTextContent();
					continue;
				}
				if (nodes.item(i).getNodeName().equals("header_params")) {
					NodeList header_params = nodes.item(i).getChildNodes();
					for (int j = 0; j < header_params.getLength(); j++) {
						headers.put(header_params.item(j).getFirstChild()
								.getTextContent(), header_params.item(j)
								.getLastChild().getTextContent());
					}
				}
			}
			URL myUrl = new URL(url);
			HttpMethodExecutor httpMethodExecutor = new HttpMethodExecutor("",
					"", myUrl.getHost(), myUrl.getPort());
			httpMethodExecutor.filePut(headers, dataFile.getPathToFile(),
					myUrl.getPath());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
