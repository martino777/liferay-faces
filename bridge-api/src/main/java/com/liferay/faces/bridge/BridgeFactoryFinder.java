/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.bridge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.faces.FacesException;


/**
 * @author  Neil Griffin
 */
public abstract class BridgeFactoryFinder {

	// Private Static Data Members
	private static BridgeFactoryFinder instance;

	public static String getClassPathResourceAsString(String resourcePath) {
		String classPathResourceAsString = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		if (classLoader != null) {
			InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				try {
					classPathResourceAsString = bufferedReader.readLine();
				}
				catch (IOException e) {

					// Since the API can't use a logging system like SLF4J the best we can do is print to stderr.
					System.err.println("Unable to read contents of resourcePath=[" + resourcePath + "]");
				}
				finally {

					try {
						bufferedReader.close();
						inputStreamReader.close();
						inputStream.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return classPathResourceAsString;
	}

	public static Object getFactory(Class<?> clazz) {
		return getInstance().getFactoryInstance(clazz);
	}

	public static BridgeFactoryFinder getInstance() throws FacesException {

		if (instance == null) {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			try {
				String factoryFinderService = "META-INF/services/com.liferay.faces.bridge.BridgeFactoryFinder";
				String facesFactoryFinderClassName = getClassPathResourceAsString(factoryFinderService);

				if (facesFactoryFinderClassName != null) {
					Class<?> facesFactoryFinderClass = classLoader.loadClass(facesFactoryFinderClassName);
					instance = (BridgeFactoryFinder) facesFactoryFinderClass.newInstance();
				}
				else {
					throw new FacesException("Unable to load resource=[" + factoryFinderService + "]");
				}
			}
			catch (Exception e) {
				throw new FacesException(e);
			}
		}

		return instance;
	}

	public abstract Object getFactoryInstance(Class<?> clazz);
}
