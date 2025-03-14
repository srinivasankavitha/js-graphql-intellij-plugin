/*
 *  Copyright (c) 2015-present, Jim Kynde Meyer
 *  All rights reserved.
 *
 *  This source code is licensed under the MIT license found in the
 *  LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.endpoint.ide.configuration;

import java.util.List;

import com.google.common.collect.Lists;

public class JSGraphQLEndpointSchemaAnnotation {

	public String name;
	public String description;
	public List<JSGraphQLEndpointSchemaAnnotationArgument> arguments = Lists.newArrayList();

}
