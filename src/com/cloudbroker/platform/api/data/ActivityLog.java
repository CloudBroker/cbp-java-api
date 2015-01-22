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

@XStreamAlias("activity-log")
public class ActivityLog extends Base {
	private String action = "";
	
	private boolean active;
	
	@XStreamAlias("activity-code")
	private int activityCode;

	@XStreamAlias("current-status")
	private String currentStatus = "";

	@XStreamAlias("previous-status")
	private String previousStatus = "";

	private String description = "";
	private String message = "";
	
	@XStreamAlias("model-identifier")
	private String modelIdentifier = "";

	@XStreamAlias("model-name")
	private String modelName = "";

	private String severity = "";

	@XStreamAlias("user-id")
	private String userId = "";

	@XStreamAlias("error-explanation")
	private String errorExplanation = "";

	private String solutions = "";

	public String getAction() {
		return action;
	}

	public boolean isActive() {
		return active;
	}

	public int getActivityCode() {
		return activityCode;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public String getDescription() {
		return description;
	}

	public String getMessage() {
		return message;
	}

	public String getModelIdentifier() {
		return modelIdentifier;
	}

	public String getModelName() {
		return modelName;
	}

	public String getSeverity() {
		return severity;
	}

	public String getUserId() {
		return userId;
	}

	public String getErrorExplanation() {
		return errorExplanation;
	}

	public String getSolutions() {
		return solutions;
	}

	public static String getURL() {
		return "/activity_logs";
	}



}
