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
package com.liferay.faces.bridge.context.url.internal;

import java.net.MalformedURLException;

import javax.portlet.PortletURL;


/**
 * @author  Neil Griffin
 */
public class PortletURLNonFacesTargetRenderImpl extends PortletURLNonFacesTarget {

	public PortletURLNonFacesTargetRenderImpl(PortletURL portletURL, String portletMode, String windowState,
		boolean secure, String path) throws MalformedURLException {
		super(portletURL, portletMode, windowState, secure, path);
	}
}
