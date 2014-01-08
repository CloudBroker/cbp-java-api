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

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cloudbroker.platform.api.connector.HttpMethodExecutor;
import com.cloudbroker.platform.api.data.ActivityLog;
import com.cloudbroker.platform.api.data.Base;
import com.cloudbroker.platform.api.data.BillingItem;
import com.cloudbroker.platform.api.data.DataFile;
import com.cloudbroker.platform.api.data.DataType;
import com.cloudbroker.platform.api.data.Executable;
import com.cloudbroker.platform.api.data.Fee;
import com.cloudbroker.platform.api.data.InstanceType;
import com.cloudbroker.platform.api.data.InstanceTypePrice;
import com.cloudbroker.platform.api.data.Invoice;
import com.cloudbroker.platform.api.data.Job;
import com.cloudbroker.platform.api.data.JobStatusTime;
import com.cloudbroker.platform.api.data.Organization;
import com.cloudbroker.platform.api.data.Payment;
import com.cloudbroker.platform.api.data.PlatformPrice;
import com.cloudbroker.platform.api.data.Region;
import com.cloudbroker.platform.api.data.Resource;
import com.cloudbroker.platform.api.data.ResourcePrice;
import com.cloudbroker.platform.api.data.Software;
import com.cloudbroker.platform.api.data.SoftwarePrice;
import com.cloudbroker.platform.api.data.StoragePrice;
import com.cloudbroker.platform.api.data.Tag;
import com.cloudbroker.platform.api.data.User;
import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAPIException;

/**
 * Client class for working with the CloudBroker Platform through REST API
 * 
 */

public class CloudbrokerClient {
	private String username;
	private String password;
	private String host;
	private int port;
	private HttpMethodExecutor httpMethodExecutor = null;
	public static final int SUBMISSION_COST = 0;
	public static final int DATA_IN_COST = 1;
	public static final int DATA_OUT_COST = 2;
	public static final int JOB_RUNNING_HOUR_COST = 3;
	public static final int VAT_COST = 4;
	public static final int TOTAL_COST = 5;
	public static final int UPLOAD_PER_GB_COST = 6;
	public static final int DOWNLOAD_PER_GB_COST = 7;
	public static final int GB_PER_MONTH_COST = 8;
	public static final int GB_PER_JOB = 9;

	private HttpMethodExecutor getHttpMethodExecutor() {
		if (httpMethodExecutor == null) {
			try {
				httpMethodExecutor = new HttpMethodExecutor(username, password,
						host, port);
			} catch (Exception e) {
				throw new CloudbrokerPlatformAPIException(e.getMessage());
			}
		}
		return httpMethodExecutor;
	}

	private CloudbrokerClient(String username, String password, String host,
			int port) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	/**
	 * Used to get an instance of the CloudBroker Client
	 * 
	 * @param username
	 *            - your email for CB
	 * @param password
	 *            - your password for CB
	 * @param host
	 *            - base address where requests should be sent, e.g.
	 *            platform.cloudbroker.com
	 * @param port
	 *            - port to be used. Defaults for http(80) and https(443) are
	 *            also to be specified
	 * @return Cloudbroker Client object
	 * @throws org.apache.http.auth.AuthenticationException
	 * @throws Exception
	 */
	public static CloudbrokerClient getInstance(String username,
			String password, String host, int port) {
		return new CloudbrokerClient(username, password, host, port);
	}

	/**
	 * Used to get an object by ID
	 * 
	 * @param model
	 *            - empty model, like new Job() or new DataFile() etc
	 * @param id
	 *            - ID of the model
	 * @return requested object or null if not found
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 * @deprecated Use get(Class T, String id) instead
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T extends Base> T get(T model, String id) throws IOException,
			ClassNotFoundException {
		return (T) get(model.getClass(), id);
	}

	/**
	 * Used to get an object by ID
	 * 
	 * @param T
	 *            - model class, e.g. DataFile.class
	 * @param id
	 *            - ID of the model
	 * @return requested object or null if not found
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public <T extends Base> T get(Class<T> T, String id) throws IOException,
			ClassNotFoundException {
		return Core.get(T, id, getHttpMethodExecutor());
	}

	/**
	 * Used to get all objects
	 * 
	 * @param model
	 *            - empty model, like new Job() or new DataFile() etc
	 * @return list of requested objects or empty list if not found
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @deprecated Use list(Class T) instead
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T extends Base> List<T> list(T model) throws IOException,
			ClassNotFoundException {
		return (List<T>) list(model.getClass());
	}

	/**
	 * Used to get all objects
	 * 
	 * @param T
	 *            - model class, e.g. DataFile.class
	 * @return list of requested objects or empty list if not found
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Base> List<T> list(Class<T> T) throws IOException,
			ClassNotFoundException {
		return Core.list(T, getHttpMethodExecutor());
	}

	/**
	 * Lists all available software
	 * 
	 * @return list of software
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Software> listSoftware() throws IOException,
			ClassNotFoundException {
		return list(Software.class);
	}

	/**
	 * Lists all available executables
	 * 
	 * @return list of executables
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Executable> listExecutables() throws IOException,
			ClassNotFoundException {
		return list(Executable.class);
	}

	/**
	 * Lists all available platform prices
	 * 
	 * @return list of platform prices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<PlatformPrice> listPlatformPrices() throws IOException,
			ClassNotFoundException {
		return list(PlatformPrice.class);
	}

	/**
	 * Lists all available software prices
	 * 
	 * @return list of software prices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<SoftwarePrice> listSoftwarePrices() throws IOException,
			ClassNotFoundException {
		return list(SoftwarePrice.class);
	}

	/**
	 * Lists all available resource prices
	 * 
	 * @return list of resource prices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ResourcePrice> listResourcePrices() throws IOException,
			ClassNotFoundException {
		return list(ResourcePrice.class);
	}

	/**
	 * Lists all available instance type prices
	 * 
	 * @return list of instance type prices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<InstanceTypePrice> listInstanceTypePrices() throws IOException,
			ClassNotFoundException {
		return list(InstanceTypePrice.class);
	}

	/**
	 * Lists all available users
	 * 
	 * @return list of users
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<User> listUsers() throws IOException, ClassNotFoundException {
		return list(User.class);
	}

	/**
	 * Lists all available billing items
	 * 
	 * @return list of billing items
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<BillingItem> listBillingItems() throws IOException,
			ClassNotFoundException {
		return list(BillingItem.class);
	}

	/**
	 * Lists all available invoices
	 * 
	 * @return list of invoices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Invoice> listInvoices() throws IOException,
			ClassNotFoundException {
		return list(Invoice.class);
	}

	/**
	 * Lists all available payments
	 * 
	 * @return list of payments
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Payment> listPayments() throws IOException,
			ClassNotFoundException {
		return list(Payment.class);
	}

	/**
	 * Lists all available tags
	 * 
	 * @return list of tags
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Tag> listTags() throws IOException, ClassNotFoundException {
		return list(Tag.class);
	}

	/**
	 * Lists all active executables of the software
	 * 
	 * @param software
	 * @return list of active executables per software
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Executable> listActiveExecutablesPerSoftware(Software software)
			throws IOException, ClassNotFoundException {
		List<Executable> result = new LinkedList<Executable>();
		List<Executable> initialList = list(Executable.class);
		for (Executable executable : initialList) {
			if (executable.isActive()
					&& executable.getSoftwareID().equals(software.getID())) {
				result.add(executable);
			}
		}
		return result;
	}

	/**
	 * Lists all available resources
	 * 
	 * @return list of resources
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Resource> listResources() throws IOException,
			ClassNotFoundException {
		return list(Resource.class);
	}

	/**
	 * Lists all available regions
	 * 
	 * @return list of regions
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Region> listRegions() throws IOException,
			ClassNotFoundException {
		return list(Region.class);
	}

	/**
	 * Lists all available instance types
	 * 
	 * @return list of instance types
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<InstanceType> listInstanceTypes() throws IOException,
			ClassNotFoundException {
		return list(InstanceType.class);
	}

	/**
	 * Lists all available data types
	 * 
	 * @return list of data types, that are needed for data file creation
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<DataType> listDataTypes() throws IOException,
			ClassNotFoundException {
		return list(DataType.class);
	}

	/**
	 * Lists all available storage prices
	 * 
	 * @return list of storage prices
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<StoragePrice> listStoragePrices() throws IOException,
			ClassNotFoundException {
		return list(StoragePrice.class);
	}

	/**
	 * Lists all available organizations
	 * 
	 * @return list of organizations
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<Organization> listOrganizations() throws IOException,
			ClassNotFoundException {
		return list(Organization.class);
	}

	/**
	 * Saves new or updates an existing model.
	 * 
	 * @param model
	 * @return true if everything went fine, false otherwise
	 * @throws IOException
	 */
	public Base save(Base model) throws IOException {
		if (model.getID().equals("")) {
			return Core.create(model, getHttpMethodExecutor());
		} else {
			return Core.update(model, getHttpMethodExecutor());
		}

	}

	/**
	 * Deletes an existing model
	 * 
	 * @param model
	 * @return true if everything went fine, false otherwise
	 * @throws IOException
	 */
	public boolean delete(Base model) throws IOException {
		return Core.delete(model, getHttpMethodExecutor());
	}

	/**
	 * Downloads file from the platform to the local folder. The file can be
	 * accessed by calling getFile() method.
	 * 
	 * @param dataFile
	 *            - data file to be downloaded
	 * @param pathToFolder
	 *            - path to the local folder, where the file should be stored
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void downloadFileToLocalFolder(DataFile dataFile, String pathToFolder)
			throws IOException, ClassNotFoundException {
		Core.downloadDataFile(dataFile, getHttpMethodExecutor(), pathToFolder);
	}

	/**
	 * Wizard used for easy job creation. Creates and submits a job.
	 * 
	 * @param jobName
	 *            - name of the job on the platform
	 * @param pathToDataFilesDirectory
	 *            - path to the directory where the data files are located.
	 *            Wizard will create and upload files to platform automatically.
	 *            Folders are not supported for now, use archive inputs for
	 *            folders
	 * @param inputCompressed
	 *            - true if data files are archives
	 * @param nodes
	 *            - number of nodes (instances) to be run
	 * @param argumentString
	 *            - argument string for job execution
	 * @return submitted job with an ID set into it
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Resource resource, Region region,
			InstanceType instanceType, int nodes, String argumentString)
			throws IOException, ClassNotFoundException {
		return createAndSubmitJob(jobName, pathToDataFilesDirectory,
				inputCompressed, executable, resource, region, instanceType,
				nodes, argumentString, "");
	}

	/**
	 * Wizard used for easy job creation. Creates and submits a job.
	 * 
	 * @param jobName
	 *            - name of the job on the platform
	 * @param pathToDataFilesDirectory
	 *            - path to the directory where the data files are located.
	 *            Wizard will create and upload files to platform automatically.
	 *            Folders are not supported for now, use archive inputs for
	 *            folders
	 * @param inputCompressed
	 *            - true if data files are archives
	 * @param executable
	 *            - executable used for the job
	 * @param resource
	 *            - resource used for the job
	 * @param region
	 *            - region used for the job
	 * @param instanceType
	 *            - instance type used for the job
	 * @param nodes
	 *            - number of nodes (instances) to be run
	 * @param argumentString
	 *            - argument string for job execution
	 * @param stdinFileName
	 *            - filename of a file with stdin data type. Leave empty string
	 *            for none
	 * @return submitted job with an ID set into it
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Resource resource, Region region,
			InstanceType instanceType, int nodes, String argumentString,
			String stdinFileName) throws IOException, ClassNotFoundException {
		return createAndSubmitJob(jobName, pathToDataFilesDirectory,
				inputCompressed, executable, resource, region, instanceType,
				nodes, argumentString, stdinFileName, "", true, false);
	}

	/**
	 * Wizard used for easy job creation. Creates and submits a job. You can
	 * specify noInstanceShutdown and startImmediately parameters
	 * 
	 * @param jobName
	 *            - name of the job on the platform
	 * @param pathToDataFilesDirectory
	 *            - path to the directory where the data files are located.
	 *            Wizard will create and upload files to platform automatically.
	 *            Folders are not supported for now, use archive inputs for
	 *            folders
	 * @param inputCompressed
	 *            - true if data files are archives
	 * @param executable
	 *            - executable used for the job
	 * @param resource
	 *            - resource used for the job
	 * @param region
	 *            - region used for the job
	 * @param instanceType
	 *            - instance type used for the job
	 * @param nodes
	 *            - number of nodes (instances) to be run
	 * @param argumentString
	 *            - argument string for job execution
	 * @param stdinFileName
	 *            - filename of a file with stdin data type. Leave empty string
	 *            for none
	 * @return submitted job with an set into it
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Resource resource, Region region,
			InstanceType instanceType, int nodes, String argumentString,
			String stdinFileName, String jobTagContent,
			boolean startImmediately, boolean noInstanceShutdown)
			throws IOException, ClassNotFoundException {
		return Core.createAndSubmitJob(jobName, pathToDataFilesDirectory,
				inputCompressed, executable, resource, region, instanceType,
				null, nodes, argumentString, getHttpMethodExecutor(),
				stdinFileName, jobTagContent, startImmediately,
				noInstanceShutdown);
	}

	/**
	 * Wizard used for easy job creation. Creates and submits a job with tag
	 * content.
	 * 
	 * @param jobName
	 *            - name of the job on the platform
	 * @param pathToDataFilesDirectory
	 *            - path to the directory where the data files are located.
	 *            Wizard will create and upload files to platform automatically.
	 *            Folders are not supported for now, use archive inputs for
	 *            folders
	 * @param inputCompressed
	 *            - true if data files are archives
	 * @param executable
	 *            - executable used for the job
	 * @param resource
	 *            - resource used for the job
	 * @param region
	 *            - region used for the job
	 * @param instanceType
	 *            - instance type used for the job
	 * @param nodes
	 *            - number of nodes (instances) to be run
	 * @param argumentString
	 *            - argument string for job execution
	 * @param stdinFileName
	 *            - filename of a file with stdin data type. Leave empty string
	 *            for none
	 * @param jobTagContent
	 *            - content of the tag for the job
	 * @return submitted job with an ID and tag content set into it
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Resource resource, Region region,
			InstanceType instanceType, int nodes, String argumentString,
			String stdinFileName, String jobTagContent) throws IOException,
			ClassNotFoundException {
		return createAndSubmitJob(jobName, pathToDataFilesDirectory,
				inputCompressed, executable, resource, region, instanceType,
				nodes, argumentString, stdinFileName, jobTagContent, true,
				false);
	}

	/**
	 * Wizard used for easy job creation. Creates and submits a job in pipeline.
	 * 
	 * @param jobName
	 *            - name of the job on the platform
	 * @param pathToDataFilesDirectory
	 *            - path to the directory where the data files are located.
	 *            Wizard will create and upload files to platform automatically.
	 *            Folders are not supported for now, use archive inputs for
	 *            folders
	 * @param inputCompressed
	 *            - true if data files are archives
	 * @param executable
	 *            - executable used for the job
	 * @param previousJob
	 *            - the preceding job, after which, once it is completed, this
	 *            job will start automatically.
	 * @param nodes
	 *            - number of nodes (instances) to be run
	 * @param argumentString
	 *            - argument string for job execution
	 * @param stdinFileName
	 *            - filename of a file with stdin data type. Leave empty string
	 *            for none
	 * @return submitted job with an ID set into it, which will run
	 *         automatically once the preceding job is completed
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Job createAndSubmitJob(String jobName,
			String pathToDataFilesDirectory, boolean inputCompressed,
			Executable executable, Job previousJob, int nodes,
			String argumentString, String stdinFileName) throws IOException,
			ClassNotFoundException {
		return Core.createAndSubmitJob(jobName, pathToDataFilesDirectory,
				inputCompressed, executable, null, null, null, previousJob,
				nodes, argumentString, getHttpMethodExecutor(), stdinFileName,
				"", true, false);
	}

	/**
	 * Searches a model by name
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param model
	 *            - empty model, like new Job() or new DataFile() etc
	 * @param name
	 *            - name of model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @deprecated Use findByName(Class T, String name) instead
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T extends Base> T findByName(T model, String name)
			throws IOException, ClassNotFoundException {
		return (T) findByName(model.getClass(), name);
	}

	/**
	 * Searches a model by name
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param T
	 *            - model class, e.g. DataFile.class
	 * @param name
	 *            - name of model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Base> T findByName(Class<T> T, String name)
			throws IOException, ClassNotFoundException {
		T result = null;
		for (T t : list(T)) {
			if (t.getName().equals(name)) {
				result = t;
				break;
			}
		}
		return result;
	}

	/**
	 * Searches a model by name and status
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param model
	 *            - empty model, like new Job() or new DataFile() etc
	 * @param name
	 *            - name of model to be searched for
	 * @param status
	 *            - status of model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @deprecated Use findByNameAndStatus(Class T, String name, String status)
	 *             instead
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T extends Base> T findByNameAndStatus(T model, String name,
			String status) throws IOException, Exception,
			ClassNotFoundException {
		return (T) findByNameAndStatus(model.getClass(), name, status);
	}

	/**
	 * Searches a model by name and status
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param T
	 *            - - model class, e.g. DataFile.class
	 * @param name
	 *            - name of model to be searched for
	 * @param status
	 *            - status of model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Base> T findByNameAndStatus(Class<T> T, String name,
			String status) throws IOException, Exception,
			ClassNotFoundException {
		T result = null;
		for (T t : list(T)) {
			if (t.getName().equals(name)
					&& t.getStatus().toString().equalsIgnoreCase(status)) {
				result = t;
				break;
			}
		}
		return result;
	}

	/**
	 * Searches an active model by name
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param model
	 *            - empty model, like new Job() or new DataFile() etc
	 * @param name
	 *            - name of active model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 * @deprecated Use findByActiveName(Class T, String name)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T extends Base> T findByActiveName(T model, String name)
			throws Exception {
		return (T) findByActiveName(model.getClass(), name);
	}

	/**
	 * Searches an active model by name
	 * 
	 * @param <T>
	 *            - Model, that should extend Base
	 * @param T
	 *            - - model class, e.g. DataFile.class
	 * @param name
	 *            - name of active model to be searched for
	 * @return found model or null
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Base> T findByActiveName(Class<T> T, String name)
			throws Exception {
		T result = null;
		for (T t : list(T)) {
			if (t.getName().equals(name) && t.isActive()) {
				result = t;
				break;
			}
		}
		return result;
	}

	/**
	 * Submits the given job to the cloud
	 * 
	 * @param job
	 *            - job to be submitted
	 * @throws IOException
	 */
	public void submitJob(Job job) throws IOException {
		Core.submitJob(job, getHttpMethodExecutor());
	}
	
	/**
	 * Requests file loading, applicable to a running job only. Files are loaded asynchronously, please check Job#lastLoadFiles field to see when files were loaded
	 *
	 * 
	 * @param job
	 *            - job which files should be loaded
	 * @param mask
	 * 			  - defines which files to be loaded (e.g. '*.log', 'job.*' etc)             
	 * @throws IOException
	 */
	public void loadJobFiles(Job job, String mask) throws IOException {
		if(mask == null || mask.trim().isEmpty()) {
			mask = "*";
		}
		Core.loadJobFiles(job, mask, getHttpMethodExecutor());
	}

	/**
	 * Stops the given job in the cloud
	 * 
	 * @param job
	 *            - job to be stopped
	 * @throws IOException
	 */
	public void stopJob(Job job) throws IOException {
		Core.stopJob(job, getHttpMethodExecutor());

	}

	/**
	 * Restarts the given job in the cloud. To be restarted, job's status should
	 * be "completed", and job outcome - "interrupted"
	 * 
	 * @param job
	 *            - job to be restarted
	 * @throws IOException
	 */
	public void restartJob(Job job) throws IOException {
		Core.restartJob(job, getHttpMethodExecutor());
	}

	/**
	 * Copies the given job in the cloud
	 * 
	 * @param job
	 *            - job to to be copied
	 * @throws IOException
	 */
	public Job copyJob(Job job, String newName) throws IOException {
		return Core.copyJob(job, newName, getHttpMethodExecutor());
	}

	/**
	 * Asks the platform for the actual status of a job
	 * 
	 * @param job
	 *            - job, which status should be defined
	 * @return status of a job
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String getJobStatus(Job job) throws IOException,
			ClassNotFoundException {
		job = get(Job.class, job.getID());
		return job.getStatus();
	}

	/**
	 * Downloads the job's data files to the specified folder
	 * 
	 * @param job
	 *            - job for which data files should be downloaded
	 * @param path
	 *            - path to the folder, where data files should be downloaded
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void downloadJobFilesTo(Job job, String path) throws IOException,
			ClassNotFoundException {
		Core.downloadJobFiles(job, getHttpMethodExecutor(), path, false);
	}

	/**
	 * Deletes job's data files
	 * 
	 * @param job
	 *            - job, which data files should be deleted
	 * @throws IOException
	 */
	public void deleteJobDataFiles(Job job) throws IOException {
		Core.deleteJobFiles(job, httpMethodExecutor);
	}

	/**
	 * Downloads the job's output data files to the specified folder
	 * 
	 * @param job
	 *            - job, for which data files should be downloaded
	 * @param path
	 *            - path to the folder, where data files should be downloaded
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void downloadJobOutputFilesTo(Job job, String path)
			throws IOException, ClassNotFoundException {
		Core.downloadJobFiles(job, getHttpMethodExecutor(), path, true);
	}

	/**
	 * Returns a list of data files for a job
	 * 
	 * @param jobID
	 *            - job ID, which data files should be shown
	 * @return List of data files
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<DataFile> getJobDataFiles(String jobID) throws IOException,
			ClassNotFoundException {
		return Core.getJobDataFiles(jobID, getHttpMethodExecutor());
	}

	/**
	 * Lists activity logs for the given job
	 * 
	 * @param jobID
	 *            - ID of the job to list the activity logs for
	 * @return List of activity logs
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ActivityLog> getJobLogs(String jobID) throws IOException,
			ClassNotFoundException {
		return Core.getJobLogs(jobID, getHttpMethodExecutor());
	}

	/**
	 * Lists activity logs for the given job
	 * 
	 * @param job
	 *            - job to list the activity logs for
	 * @return List of activity logs
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ActivityLog> getJobLogs(Job job) throws IOException,
			ClassNotFoundException {
		return getJobLogs(job.getId());
	}

	/**
	 * Lists job errors for the given job
	 * 
	 * @param jobID
	 *            - ID of the job to list the errors for
	 * @return List of errors
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ActivityLog> getJobErrors(String jobID) throws IOException,
			ClassNotFoundException {
		List<ActivityLog> result = new LinkedList<ActivityLog>();
		List<ActivityLog> activityLogs = Core.getJobLogs(jobID,
				getHttpMethodExecutor());
		for (ActivityLog al : activityLogs) {
			String severity = al.getSeverity().toLowerCase();
			if (severity.equals("critical") || severity.equals("warning")
					|| severity.equals("error")) {
				result.add(al);
			}
		}
		return result;
	}

	/**
	 * Lists job errors for the given job
	 * 
	 * @param job
	 *            - job to list the errors for
	 * @return List of errors
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ActivityLog> getJobErrors(Job job) throws IOException,
			ClassNotFoundException {
		return getJobErrors(job.getId());
	}

	/**
	 * Returns a list of data files for a job
	 * 
	 * @param job
	 *            - job, which files should be shown
	 * @return List of data files
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<DataFile> getJobDataFiles(Job job) throws IOException,
			ClassNotFoundException {
		return getJobDataFiles(job.getID());
	}

	/**
	 * Shows the estimated job cost to the user
	 * 
	 * @param software
	 *            - software used for the job
	 * @param resource
	 *            - resource used for the job
	 * @param region
	 *            - region used for the job
	 * @param instanceType
	 *            - instance type used for the job
	 * @param filesSize
	 *            - size of all input data files in GB. If no files yet - pass 0
	 *            here
	 * @param nodes
	 *            - number of instances on which the job will run
	 * @return hash, where key (String) is cost description and value (double)
	 *         is the cost itself
	 * @throws IOException
	 */
	public HashMap<Integer, Double> getEstimatedCostForJob(Software software,
			Resource resource, Region region, InstanceType instanceType,
			double filesSize, int nodes) throws IOException {
		return getEstimatedCostForJob(software.getID(), resource.getID(),
				region.getID(), instanceType.getID(), filesSize, nodes);
	}

	/**
	 * Shows the estimated job cost to the user
	 * 
	 * @param softwareId
	 *            - software ID used for the job
	 * @param resourceId
	 *            - resource ID used for the job
	 * @param regionId
	 *            - region ID used for the job
	 * @param instanceTypeId
	 *            - instance type ID used for the job
	 * @param filesSize
	 *            - size of all input data files in GB. If no files yet - pass 0
	 *            here
	 * @param nodes
	 *            - number of instances on which the job will run
	 * @return hash, where key (String) is cost description and value (double)
	 *         is the cost itself
	 * @throws IOException
	 */
	public HashMap<Integer, Double> getEstimatedCostForJob(String softwareId,
			String resourceId, String regionId, String instanceTypeId,
			double filesSize, int nodes) throws IOException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("software", softwareId);
		params.put("resource", resourceId);
		params.put("region", regionId);
		params.put("instance_type", instanceTypeId);
		params.put("data_file_size", String.valueOf(filesSize));
		params.put("nodes", String.valueOf(nodes));
		return Core.estimatesFor(new Job(), params, getHttpMethodExecutor());
	}

	/**
	 * Shows the estimated data files cost to the user
	 * 
	 * @param job
	 *            - created job, for which the data files are added
	 * @return data file estimated costs
	 * @throws IOException
	 */
	public HashMap<Integer, Double> getEstimatedCostForDataFile(Job job)
			throws IOException {
		return getEstimatedCostForDataFile(job.getID());
	}

	/**
	 * Shows the estimated data files cost to the user
	 * 
	 * @param jobId
	 *            - created job ID, for which the data files are added
	 * @return hash, where key (String) is cost description and value (double)
	 *         is the cost itself
	 * @throws IOException
	 */
	public HashMap<Integer, Double> getEstimatedCostForDataFile(String jobId)
			throws IOException {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("job", jobId);
		return Core.estimatesFor(new DataFile(), params,
				getHttpMethodExecutor());
	}

	/**
	 * Lists costs for the base object
	 * 
	 * @param base
	 *            - specify the base object, for which its costs should be shown
	 * @return hash, where key (String) is cost description and value (double)
	 *         is the cost itself
	 * @throws IOException
	 */
	public List<Fee> getCostsFor(Base base) throws IOException {
		return Core.costsFor(base, getHttpMethodExecutor());
	}

	/**
	 * Deactivates the base object on the CloudBroker side
	 * 
	 * @param base
	 *            - specify the base object, which should be deactivated
	 * @throws IOException
	 */
	public void deactivate(Base base) throws IOException {
		Core.deactivate(base, getHttpMethodExecutor());
	}

	/**
	 * Activates the base object on the CloudBroker side
	 * 
	 * @param base
	 *            - specify the base object, which should be activated
	 * @throws IOException
	 */
	public void activate(Base base) throws IOException {
		Core.activate(base, getHttpMethodExecutor());
	}

	/**
	 * Sets the timeout value for waiting for data before throwing an exception.
	 * Default value is 1 minute.
	 * 
	 * @param seconds
	 *            - specify the timeout value in seconds
	 */
	public void setTimeouts(int seconds) {
		getHttpMethodExecutor().setTimeouts(seconds);
	}

	/**
	 * Lists fees of the selected tag
	 * 
	 * @param tag
	 *            - specify the tag, for which its fees should be shown
	 * @throws IOException
	 * @return list of fees of the tag
	 */
	public HashMap<String, Double> getTagsFees(Tag tag) throws IOException {
		return Core.getTagFee(tag, getHttpMethodExecutor());
	}

	/**
	 * Gets the server version
	 * 
	 * @return server version
	 * @throws IOException
	 */
	public String getServerVersion() throws IOException {
		return Core.getServerVersion(getHttpMethodExecutor());
	}

	/**
	 * Lists the enter time and exit time of each job's status reached during
	 * its execution
	 * 
	 * @param job
	 *            - job to get the status enter and exit times for
	 * @return List of the enter time and exit time of each status of the given
	 *         job
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<JobStatusTime> getJobStatusTimes(Job job) throws IOException,
			ClassNotFoundException {
		return Core.getJobStatusTimes(job.getID(), getHttpMethodExecutor());
	}
}
