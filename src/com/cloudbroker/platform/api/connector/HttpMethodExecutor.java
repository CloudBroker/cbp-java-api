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

package com.cloudbroker.platform.api.connector;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformAuthenticationException;
import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformException;
import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformMissingRightsException;
import com.cloudbroker.platform.api.exceptions.CloudbrokerPlatformWrongStatusException;

public class HttpMethodExecutor {
	private DefaultHttpClient httpClient;
	private HttpHost targetHost;
	private BasicHttpContext localcontext;

	@SuppressWarnings("deprecation")
	public HttpMethodExecutor(String username, String password, String host,
			int port) throws NoSuchAlgorithmException, KeyManagementException {
		UsernamePasswordCredentials myCredentials = null;
		if (username != null && !username.isEmpty()) {
			myCredentials = new UsernamePasswordCredentials(username, password);
		}
		targetHost = new HttpHost(host, port, "https");
		// Canceling the certificate change
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {

			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = new DefaultHttpClient()
				.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", ssf, 443));
		httpClient = new DefaultHttpClient(ccm);
		if (myCredentials != null) {
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(host, port), myCredentials);
		}
		// Increase timeouts
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established
		int timeoutConnection = 60 * 1000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the timeout in milliseconds for waiting for data
		int timeoutSocket = 60 * 1000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpClient.setParams(httpParameters);
		AuthCache authCache = new BasicAuthCache();
		authCache.put(targetHost, new BasicScheme());
		localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
	}

	public void setTimeouts(int seconds) {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				seconds * 1000);
		HttpConnectionParams.setSoTimeout(httpParameters, seconds * 1000);
		httpClient.setParams(httpParameters);
	}

	public byte[] get(String url) throws IOException {
		return executeGet(url, HttpStatus.SC_OK);
	}

	public String stringGet(String url) throws IOException {
		return executeStringGet(url, HttpStatus.SC_OK);
	}

	public String post(String content, String url, int expectedStatusCode)
			throws IOException {
		HttpPost method = new HttpPost(url);
		HttpEntity entity = new StringEntity(content, "application/xml",
				"UTF-8");
		method.setEntity(entity);

		HttpResponse response = httpClient.execute(targetHost, method,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		checkForErrorsInResponseString(responseBody);
		return responseBody;
	}

	public String multipartPost(HashMap<String, String> content,
			String pathToFile, String url, int expectedStatusCode)
			throws IOException {
		HttpPost method = new HttpPost(url);
		FileBody bin = new FileBody(new File(pathToFile));
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);

		entity.addPart("data", bin);
		for (String key : content.keySet()) {
			entity.addPart(key, new StringBody(content.get(key)));
		}
		method.setEntity(entity);

		HttpResponse response = httpClient.execute(targetHost, method,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		checkForErrorsInResponseString(responseBody);
		return responseBody;
	}

	public String filePut(HashMap<String, String> headers, String pathToFile,
			String url) throws IOException {
		HttpPut method = new HttpPut(url);
		for (String name : headers.keySet()) {
			method.addHeader(name, headers.get(name));
		}
		File file = new File(pathToFile);
		method.setEntity(new FileEntity(file, ""));

		HttpResponse response = httpClient.execute(targetHost, method,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		checkForErrorsInResponseString(responseBody);
		return responseBody;
	}

	public String put(String content, String url, int expectedStatusCode)
			throws IOException {
		HttpPut method = new HttpPut(url);
		if (!content.isEmpty()) {
			HttpEntity entity = new StringEntity(content, "application/xml",
					"UTF-8");
			method.setEntity(entity);
		}

		HttpResponse response = httpClient.execute(targetHost, method,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		checkForErrorsInResponseString(responseBody);
		return responseBody;
	}

	public String getXML(String url) throws ClientProtocolException,
			IOException {
		HttpGet method = new HttpGet(url);
		HttpResponse response = httpClient.execute(targetHost, method,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		return responseBody;
	}

	public boolean delete(String url) throws IOException {
		HttpDelete deleteMethod = new HttpDelete(url);
		HttpResponse response = httpClient.execute(targetHost, deleteMethod,
				localcontext);
		String responseBody = getResponseBodyFromHttpResponse(response);
		checkForErrorsInResponseString(responseBody);
		if (response.getStatusLine().getStatusCode() == 200) {
			return true;
		} else {
			return false;
		}
	}

	private byte[] executeGet(String url, int statusToCheck) throws IOException {
		HttpGet method = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String responseBody = httpClient.execute(targetHost, method,
				responseHandler, localcontext);
		checkForErrorsInResponseString(responseBody);
		return responseBody.getBytes();
	}

	private String executeStringGet(String url, int statusToCheck)
			throws IOException {
		HttpGet method = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String responseBody = httpClient.execute(targetHost, method,
				responseHandler, localcontext);
		checkForErrorsInResponseString(responseBody);
		return responseBody;
	}

	private String getResponseBodyFromHttpResponse(HttpResponse response)
			throws IOException {
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		InputStream instream = entity.getContent();
		if (instream == null) {
			return "";
		}
		Reader reader = new InputStreamReader(instream);
		StringBuilder buffer = new StringBuilder();
		try {
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	private static void checkForErrorsInResponseString(String errorString) {
		if (errorString == null || errorString.isEmpty()) {
			return;
		}
		NodeList errorData = null;
		if (errorString.trim().isEmpty()
				|| errorString.trim().indexOf("<") != 0) {
			return;
		}
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			org.w3c.dom.Document parse = documentBuilder
					.parse(new ByteArrayInputStream(errorString.getBytes()));
			errorData = parse.getElementsByTagName("errors");
		} catch (Exception e) {
			return;
		}
		if (errorData == null || errorData.getLength() == 0) {
			return;
		}
		StringBuilder errorText = new StringBuilder();
		Element rootErrors = (Element) errorData.item(0);
		NodeList errors = rootErrors.getElementsByTagName("error");
		for (int i = 0; i < errors.getLength(); i++) {
			if (errorText.length() > 0) {
				errorText.append("; ");
			}
			errorText.append(errors.item(0).getTextContent());
		}
		NodeList errorType = rootErrors.getElementsByTagName("type");
		if (errorType != null && errorType.getLength() > 0) {
			String errorTypeString = errorType.item(0).getTextContent();
			if (errorTypeString.equals("wrong status")) {
				throw new CloudbrokerPlatformWrongStatusException(
						errorText.toString());
			}
			if (errorTypeString.equals("missing rights")) {
				throw new CloudbrokerPlatformMissingRightsException(
						errorText.toString());
			}
			if (errorTypeString.equals("authentication")) {
				throw new CloudbrokerPlatformAuthenticationException(
						errorText.toString());
			}
		}
		throw new CloudbrokerPlatformException(errorText.toString());
	}
}
