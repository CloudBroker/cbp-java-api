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

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Executable extends Base {
	private boolean active;
	
	@XStreamAlias("default-argument-string")
	private String defaultArgumentString;

	@XStreamAlias("multi-threaded")
	private String multiThreaded;

	@XStreamAlias("argument-string-template")
	private String argumentStringTemplate;

	@XStreamAlias("node-file-format-id")
	private String nodeFileFormatID;

	@XStreamAlias("node-file-name")
	private String nodeFileName;
	
	@XStreamAlias("produces-stderr")
	private boolean producesStderr;
	
	@XStreamAlias("argument-string-help")
	private String argumentStringHelp;
	
	private String binary = "";
	private String description = "";
	
	@XStreamAlias("software-id")
	private String softwareID = "";
	
	@XStreamAlias("includes-distribution")
	private boolean includesDistribution;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isProducesStderr() {
		return producesStderr;
	}

	public void setProducesStderr(boolean producesStderr) {
		this.producesStderr = producesStderr;
	}

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoftwareID() {
		return softwareID;
	}

	public void setSoftwareID(String softwareID) {
		this.softwareID = softwareID;
	}

	public static String getURL() {
		return "/executables";
	}
	
	public boolean getActive(){
		return isActive();
	}
	
	public boolean getIncludesDistribution() {
		return includesDistribution;
	}
}
