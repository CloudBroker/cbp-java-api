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
 * platform@cloudbroker.com
 *
 */

package com.cloudbroker.platform.api.core;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cloudbroker.platform.api.connector.HttpMethodExecutor;
import com.cloudbroker.platform.api.converter.XMLConverter;
import com.cloudbroker.platform.api.data.ActivityLog;
import com.cloudbroker.platform.api.data.Base;
import com.cloudbroker.platform.api.data.DataFile;
import com.cloudbroker.platform.api.data.DataType;
import com.cloudbroker.platform.api.data.Executable;
import com.cloudbroker.platform.api.data.Fee;
import com.cloudbroker.platform.api.data.InstanceType;
import com.cloudbroker.platform.api.data.Job;
import com.cloudbroker.platform.api.data.JobStatusTime;
import com.cloudbroker.platform.api.data.Region;
import com.cloudbroker.platform.api.data.Resource;
import com.cloudbroker.platform.api.data.Tag;
import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAPIException;

public class Core {

	public static Base create(Base model, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		if (model.getClass() == DataFile.class
				&& !((DataFile) model).getPathToFile().isEmpty()) {
			String xml = httpMethodExecutor.stringGet(getUrl(model.getClass())
					+ "/new/direct_file_upload.xml?job_id="
					+ ((DataFile) model).getJobID() + "&data_file_name="
					+ ((DataFile) model).getDataFileName());
			if (DirectFileUploader.uploadFile((DataFile) model, xml)) {
				// direct file upload
				return XMLConverter.deserialize(model.getClass(),
						httpMethodExecutor.put(XMLConverter.serialize(model),
								getUrl(model.getClass())
										+ "/new/link_upload.xml", 201));
			} else {
				// non-direct file upload
				return XMLConverter.deserialize(model.getClass(),
						httpMethodExecutor.multipartPost(
								((DataFile) model).getHash(),
								((DataFile) model).getPathToFile(),
								getUrl(model.getClass()) + ".xml", 201));

			}
		} else {
			return XMLConverter.deserialize(model.getClass(),
					httpMethodExecutor.post(XMLConverter.serialize(model),
							getUrl(model.getClass()) + ".xml", 201));
		}
	}

	public static Base update(Base model, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		httpMethodExecutor.put(XMLConverter.serialize(model),
				getUrl(model.getClass()) + "/" + model.getID() + ".xml", 200);
		return model;
	}

	public static <T extends Base> List<T> list(Class<T> T,
			HttpMethodExecutor httpMethodExecutor) throws IOException,
			ClassNotFoundException {
		byte[] xml = httpMethodExecutor.get(getUrl(T) + ".xml");
		return XMLConverter.deserializeList(T, xml);
	}

	public static <T extends Base> T get(Class<T> T, String id,
			HttpMethodExecutor httpMethodExecutor) throws IOException,
			ClassNotFoundException {
		return XMLConverter.deserialize(
				T,
				new String(httpMethodExecutor
						.get(getUrl(T) + "/" + id + ".xml")));
	}

	public static List<DataFile> getJobDataFiles(String jodID,
			HttpMethodExecutor httpMethodExecutor) throws IOException,
			ClassNotFoundException {
		byte[] xml = httpMethodExecutor.get("/jobs/" + jodID
				+ "/data_files.xml");
		return XMLConverter.deserializeList(DataFile.class, xml);
	}

	public static List<ActivityLog> getJobLogs(String jodID,
			HttpMethodExecutor httpMethodExecutor) throws IOException,
			ClassNotFoundException {
		byte[] xml = httpMethodExecutor.get("/jobs/" + jodID
				+ "/activity_logs.xml");
		return XMLConverter.deserializeList(ActivityLog.class, xml);
	}

	public static List<JobStatusTime> getJobStatusTimes(String jobID,
			HttpMethodExecutor httpMethodExecutor) throws IOException,
			ClassNotFoundException {
		byte[] xml = httpMethodExecutor.get("/jobs/" + jobID + "/timeline.xml");
		return XMLConverter.deserializeList(JobStatusTime.class, xml);
	}

	public static void downloadDataFile(DataFile dataFile,
			HttpMethodExecutor httpMethodExecutor, String pathToFolder)
			throws IOException, ClassNotFoundException {
		String linkToFile = httpMethodExecutor.put("",
				getUrl(dataFile.getClass()) + "/" + dataFile.getID()
						+ "/download.xml", 200);
		// Create folder if not exists
		File dir = new File(pathToFolder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String pathToFile = pathToFolder + "/" + dataFile.getDataFileName();
		downloadFile(linkToFile, pathToFile);
		dataFile.setPathToFile(pathToFile);
	}

	public static boolean delete(Base model,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		return httpMethodExecutor.delete(getUrl(model.getClass()) + "/"
				+ model.getID() + ".xml");
	}

	public static Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Resource resource, Region region,
			InstanceType instanceType, Job previousJob, int nodes,
			String argumentString, HttpMethodExecutor httpMethodExecutor,
			String stdinFileName, String jobtagContent,
			boolean startImmediately, boolean noInstanceShutdown)
			throws IOException, ClassNotFoundException {
		// Check if specified stdin file exists
		if (!stdinFileName.isEmpty()
				&& !new File(pathToDataFilesDirectory + "/" + stdinFileName)
						.exists()) {
			throw new IOException("Stdin file " + stdinFileName
					+ " was not found");
		}
		Job job = new Job();
		job.setName(jobName);
		job.setDescription("Created with API Wizard");
		job.setExecutable(executable);
		if (previousJob == null) {
			job.setRegionID(region.getID());
			job.setResourceID(resource.getID());
			job.setInstanceTypeID(instanceType.getID());
		} else {
			job.setPreviousJobID(previousJob.getID());
		}
		job.setNodes(nodes);
		job.setArgumentString(argumentString);
		job.setStartImmediately(true);
		job.setTagContent(jobtagContent);
		job.setStartImmediately(startImmediately);
		job.setNoInstanceShutdown(noInstanceShutdown);
		job = (Job) create(job, httpMethodExecutor);
		// Input data type search
		DataType inputDataType = null;
		DataType stdinDataType = null;
		List<DataType> dataTypes = list(DataType.class, httpMethodExecutor);
		for (DataType dataType : dataTypes) {
			if (dataType.getName().equals("input")) {
				inputDataType = dataType;
			}
			if (dataType.getName().equals("stdin")) {
				stdinDataType = dataType;
			}
		}
		// Data files creation
		File dir = new File(pathToDataFilesDirectory);
		String[] children = dir.list();
		if (children == null) {
			throw new IOException(
					"Either dir does not exist or is not a directory");
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = pathToDataFilesDirectory + "/" + children[i];
				if (new File(filename).isDirectory()) {
					continue;
				}
				DataFile dataFile = new DataFile();
				dataFile.setPathToFile(filename);
				dataFile.setArchive(inputCompressed);
				if (stdinFileName.equals(children[i])) {
					dataFile.setDataType(stdinDataType);
				} else {
					dataFile.setDataType(inputDataType);
				}
				dataFile.setDescription("Uploaded via API Wizard");
				dataFile.setJobID(job.getID());
				create(dataFile, httpMethodExecutor);
			}
		}
		// Submitting a job if not a pipeline
		if (previousJob == null) {
			submitJob(job, httpMethodExecutor);
		}
		return job;
	}

	public static void submitJob(Job job, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		httpMethodExecutor.put("", getUrl(job.getClass()) + "/" + job.getID()
				+ "/submit.xml", 200);
	}

	public static void stopJob(Job job, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		httpMethodExecutor.put("", getUrl(job.getClass()) + "/" + job.getID()
				+ "/stop.xml", 200);
	}

	public static void deleteJobFiles(Job job,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		httpMethodExecutor.put("", getUrl(job.getClass()) + "/" + job.getID()
				+ "/delete_all_data_files.xml", 200);
	}

	public static void restartJob(Job job, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		httpMethodExecutor.put("", getUrl(job.getClass()) + "/" + job.getID()
				+ "/restart.xml", 201);
	}

	private static void downloadFile(String from, String to) throws IOException {
		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int byteRead = 0;
			Url = new URL(from);
			outStream = new BufferedOutputStream(new FileOutputStream(to));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[1024];
			while ((byteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, byteRead);
			}
		} catch (Exception e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				throw new CloudbrokerPlatformAPIException(e.getMessage());
			}
		}
	}

	public static Job copyJob(Job job, String newName,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		String xml = httpMethodExecutor
				.put("<copy_params><new_job_name>"
						+ newName
						+ "</new_job_name><reuse_input>true</reuse_input></copy_params>",
						getUrl(job.getClass()) + "/" + job.getID()
								+ "/copy.xml", 201);
		return XMLConverter.deserialize(Job.class, xml);
	}

	public static void downloadJobFiles(Job job,
			HttpMethodExecutor httpMethodExecutor, String pathTo,
			boolean outputsOnly) throws IOException, ClassNotFoundException {
		List<DataFile> dataFiles = getJobDataFiles(job.getId(),
				httpMethodExecutor);
		for (DataFile dataFile : dataFiles) {
			if (outputsOnly
					&& !(dataFile.getDataTypeName().equals("output")
							|| dataFile.getDataTypeName().equals("stderr")
							|| dataFile.getDataTypeName().equals("stdout") || dataFile
							.getDataTypeName().equals("tgzout"))) {
				continue;
			}
			downloadDataFile(dataFile, httpMethodExecutor, pathTo);
		}
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}

	public static HashMap<Integer, Double> estimatesFor(Base base,
			HashMap<String, String> params,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		String xml = httpMethodExecutor.post(
				formParamsXMLStringFromHash(params), getUrl(base.getClass())
						+ "/get_estimated_cost.xml", 200);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList data = doc.getElementsByTagName("estimated-cost");
			for (int i = 0; i < data.getLength(); i++) {
				Element element = (Element) data.item(i);
				result.put(
						Integer.parseInt(element
								.getElementsByTagName("price-id").item(0)
								.getTextContent()),
						Double.parseDouble(element.getElementsByTagName("cost")
								.item(0).getTextContent()));
			}
		} catch (Exception e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		}
		return result;
	}

	private static String formParamsXMLStringFromHash(
			HashMap<String, String> params) {
		String result = "<data>";
		for (String key : params.keySet()) {
			result = result + "<" + key + ">" + params.get(key) + "</" + key
					+ ">";
		}
		return result + "</data>";
	}

	public static List<Fee> costsFor(Base base,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		List<Fee> result = new LinkedList<Fee>();
		String xml = httpMethodExecutor.stringGet(getUrl(base.getClass()) + "/"
				+ base.getID() + "/costs.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList data = doc.getElementsByTagName("fee");
			for (int i = 0; i < data.getLength(); i++) {
				Fee fee = new Fee();
				Element element = (Element) data.item(i);
				fee.setName(element.getElementsByTagName("name").item(0)
						.getTextContent());
				String monthQuantity = element
						.getElementsByTagName("month_quantity").item(0)
						.getTextContent();
				if (!monthQuantity.isEmpty()) {
					fee.setMonthQuantity(Double.parseDouble(monthQuantity));
				}
				fee.setMonthCost(Double.parseDouble(element
						.getElementsByTagName("month_cost").item(0)
						.getTextContent()));
				String totalQuantity = element
						.getElementsByTagName("total_quantity").item(0)
						.getTextContent();
				if (!totalQuantity.isEmpty()) {
					fee.setTotalQuantity(Double.parseDouble(totalQuantity));
				}
				fee.setTotalCost(Double.parseDouble(element
						.getElementsByTagName("total_cost").item(0)
						.getTextContent()));
				result.add(fee);
			}
		} catch (Exception e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		}
		return result;
	}

	public static void deactivate(Base base,
			HttpMethodExecutor httpMethodExecutor) throws IOException {
		httpMethodExecutor.put("", getUrl(base.getClass()) + "/" + base.getID()
				+ "/deactivate.xml", 200);
	}

	public static HashMap<String, Double> getTagFee(Tag tag,
			HttpMethodExecutor httpMethodExecutor) throws IOException {

		String xml = httpMethodExecutor.stringGet(getUrl(tag.getClass()) + "/"
				+ tag.getID() + "/get_tag_fees.xml");
		HashMap<String, Double> feesMap = new HashMap<String, Double>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList tagData = doc.getElementsByTagName("fee");
			for (int i = 0; i < tagData.getLength(); i++) {
				Element element = (Element) tagData.item(i);
				feesMap.put(
						element.getElementsByTagName("name").item(0)
								.getTextContent().trim(),
						Double.parseDouble(element
								.getElementsByTagName("value").item(0)
								.getTextContent()));
			}
		} catch (Exception e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		}
		return feesMap;

	}

	public static void activate(Base base, HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		httpMethodExecutor.put("", getUrl(base.getClass()) + "/" + base.getID()
				+ "/activate.xml", 200);
	}

	public static String getServerVersion(HttpMethodExecutor httpMethodExecutor)
			throws IOException {
		String xml = httpMethodExecutor.post("", "/server_version.xml", 200);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			Document doc = db.parse(is);
			NodeList data = doc.getElementsByTagName("server_version");

			return (data.getLength() == 0) ? "Unknown" : data.item(0)
					.getTextContent();
		} catch (Exception e) {
			return "Unknown";
		}
	}

	private static <T extends Base> String getUrl(Class<T> T) {
		try {
			Method m = T.getMethod("getURL");
			return (String) m.invoke(null);
		} catch (Exception e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		}
	}

}
