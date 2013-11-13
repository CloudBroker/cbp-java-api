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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAPIException;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public abstract class Base {
	protected String id = "";
	
	@XStreamAlias("created-at")
	protected String createdAt;
	
	@XStreamAlias("updated-at")
	protected String updatedAt;
	
	private String name = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return id;
	}
	
	public String getId() {
		return getID();
	}
	
	public static String getURL() {
		return "/";
	}
	
	protected Date getDateFromString(String inputDate) {
		Date result = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
		try {
			result = formatter.parse(inputDate);
		} catch (ParseException e) {
			throw new CloudbrokerPlatformAPIException(e.getMessage());
		}
		return result;
	}
	
	public String getStatus() {
		throw new CloudbrokerPlatformAPIException("Status field is absent for this model");
	}
	
	public boolean getActive() {
		throw new CloudbrokerPlatformAPIException("Active field is absent for this model");
	}
	
	public boolean isActive() {
		throw new CloudbrokerPlatformAPIException("Active field is absent for this model");
	}
	
}
