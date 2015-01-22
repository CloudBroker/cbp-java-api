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

package com.cloudbroker.platform.api.data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Resource extends Base {
	private boolean active;
	private String description;

	@XStreamAlias("external-hostname")
	private String externalHostname = "";

	@XStreamAlias("external-ip-address")
	private String externalIPAddress = "";

	@XStreamAlias("maximum-nodes")
	private String maximumNodes;

	@XStreamAlias("organization-id")
	private String organizationID = "";

	private String website = "";

	@XStreamAlias("pricing-link")
	private String pricingLink = "";

	@XStreamAlias("resource-type-id")
	private String resourceTypeID = "";

	@XStreamAlias("api-url")
	private String apiUrl = "";

	@XStreamAlias("specifier")
	private String specifier = "";

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaximumNodes() {
		return (maximumNodes.isEmpty()) ? -1 : Integer.parseInt(maximumNodes);
	}

	public void setMaximumNodes(int maximumNodes) {
		this.maximumNodes = maximumNodes + "";
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPricingLink() {
		return pricingLink;
	}

	public void setPricingLink(String pricingLink) {
		this.pricingLink = pricingLink;
	}

	public String getResourceTypeID() {
		return resourceTypeID;
	}

	public void setResourceTypeID(String resourceTypeID) {
		this.resourceTypeID = resourceTypeID;
	}

	public static String getURL() {
		return "/resources";
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getSpecifier() {
		return specifier;
	}

	public void setSpecifier(String specifier) {
		this.specifier = specifier;
	}

	public String getOrganizationID() {
		return organizationID;
	}
}
