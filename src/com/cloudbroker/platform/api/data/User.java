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

public class User extends Base {
	private String department = "";
	private String email = "";
	private String fax = "";
	private String password = "";
	
	@XStreamAlias("password-confirmation")
	private String passwordConfirmation = "";
	
	@XStreamAlias("first-name")
	private String firstName = "";

	@XStreamAlias("last-name")
	private String lastName = "";

	private String function = "";
	private String mobile = "";

	@XStreamAlias("organization-id")
	private String organizationID = "";
	
	private String phone = "";
	private String status = "";
	private String title = "";
	
	@XStreamAlias("user-role-name")
	private String userRoleName = "";
	
	@XStreamAlias("salutation-name")
	private String salutationName = "";
	
	public String getSalutationName() {
		return salutationName;
	}

	public void setSalutationName(String salutationName) {
		this.salutationName = salutationName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordConfirmation = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrganizationID() {
		return organizationID;
	}
	
	public String getOrganizationId() {
		return getOrganizationID();
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	
	public void setOrganizationId(String organizationId) {
		setOrganizationID(organizationId);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public static String getURL() {
		return "/users";
	}

}
