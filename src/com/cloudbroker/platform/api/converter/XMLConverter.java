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

package com.cloudbroker.platform.api.converter;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import com.cloudbroker.platform.api.data.Base;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XMLConverter {

	public static String serialize(Base model) {
		return obtainXStream(model.getClass()).toXML(model);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Base> T deserialize(Class<T> T, String xml) {
		return (T) obtainXStream(T).fromXML(xml);
	}

	public static void main(String[] args) {
	}

	@SuppressWarnings("unchecked")
	public static <T extends Base> List<T> deserializeList(Class<T> T, byte[] xml)
			throws IOException, ClassNotFoundException {
		List<T> result = new LinkedList<T>();
		XStream xstream = obtainXStream(T);
		ObjectInputStream in = xstream
				.createObjectInputStream(new ByteArrayInputStream(xml));
		T tmp = null;
		try {
			while ((tmp = (T) in.readObject()) != null) {
				result.add(tmp);
			}
		} catch (EOFException ex) {
			// Means end of stream achieved. Do nothing
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	private static XStream obtainXStream(Class T) {
		XStream xstream = new XStream() {
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn,
							String fieldName) {
						if (definedIn == Object.class) {
							return false;
						}
						return super
								.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		xstream.alias(T.getSimpleName().toLowerCase(), T);
		xstream.processAnnotations(T);
		String[] acceptableFormats = {"HH:mm:ss"};
		xstream.registerConverter(new CBDateConverter("yyyy-MM-dd'T'HH:mm:ss'Z'", acceptableFormats));
		return xstream;
	}
}
