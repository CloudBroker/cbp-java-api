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

@XStreamAlias("storage")
public class Storage extends Base{

	@XStreamAlias("storage-type-id")
	private String storageTypeID;
	
	@XStreamAlias("organization-id")
	private String organizationID;
	
	public String getStorageTypeID() {
		return storageTypeID;
	}
	
	public String getOrganizationID(){
		return organizationID;
	}
	
	public static String getURL() {
		return "/storages";
	}

}
