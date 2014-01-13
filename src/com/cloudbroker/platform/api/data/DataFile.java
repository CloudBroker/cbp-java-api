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

package com.cloudbroker.platform.api.data;

import java.io.File;
import java.util.HashMap;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("data-file")
public class DataFile extends Base {

	@XStreamAlias("data-content-type")
	private String dataContentType;

	@XStreamAlias("data-file-name")
	private String dataFileName;

	@XStreamAlias("scp-filename")
	private String scpFilename;

	@XStreamAlias("data-file-size")
	private String dataFileSize;

	private String pathToFile = "";
	private String description = "";
	private boolean archive;

	@XStreamAlias("job-id")
	private String jobID = "";
	
	@XStreamAlias("data-type-name")
	private String dataTypeName = "";
	
	@XStreamAlias("data-type-id")
	private String dataTypeID = "";

	@XStreamAlias("data-updated-at")
	private String dataUpdatedAt = "";
	
	private boolean uploaded = false;
	
	public boolean isUploaded() {
		return uploaded;
	}

	public String getScpFilename() {
		return scpFilename;
	}

	public void setScpFilename(String scpFilename) {
		this.scpFilename = scpFilename;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
		// trying to set data file name as well
		try {
			setDataFileName((new File(pathToFile)).getName());
		} catch (Exception e) {
			throw new RuntimeException("File " + pathToFile + " was not found");
		}
	}
	
	public void setDataType(DataType dataType) {
		this.dataTypeID = dataType.getID();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getDataTypeID() {
		return dataTypeID;
	}

	public void setDataTypeID(String dataTypeID) {
		this.dataTypeID = dataTypeID;
	}
	
	public File getFile() {
		if (pathToFile.isEmpty()) {
			throw new RuntimeException("File should be first downloaded. Use cloudbrokerClient.downloadFileToLocalFolder");
		}
		return new File(pathToFile);
	}
	
	public String getDataTypeName() {
		return dataTypeName;
	}
	
	public static String getURL() {
		return "/data_files";
	}
	
	public String getDataFileSize() {
		return dataFileSize;
	}

	public HashMap<String, String> getHash() {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("job_id", getJobID());
		hash.put("archive", isArchive() ? "true" : "false");
		hash.put("data_type_id", getDataTypeID());
		hash.put("description", getDescription());
		return hash;
	}
}
