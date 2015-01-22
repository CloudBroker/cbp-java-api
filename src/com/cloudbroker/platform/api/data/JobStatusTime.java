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

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("job-status-time")
public class JobStatusTime extends Base {

	
	@XStreamAlias("job-id")
	private String jobID;
	
	@XStreamAlias("status")
	private String status;

	@XStreamAlias("entertime")
	private String enterTime;
	
	@XStreamAlias("exittime")
	private String exitTime;
	
	public Date getEnterTime() {
		return getDateFromString(enterTime);
	}
	
	public Date getExitTime() {
		if (exitTime.isEmpty()) {
			return null;
		}
		return getDateFromString(exitTime);
	}
	
	public String getJobID() {
		return jobID;
	}
	
	public String getStatus() {
		return status;
	}
}
