/*
 * Copyright (c) 2018-present, Jim Kynde Meyer
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.psi;

import com.intellij.lang.jsgraphql.psi.GraphQLDirective;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * PSI Element which has one or more directives, e.g. fields, arguments etc.
 */
public interface GraphQLDirectivesAware extends GraphQLElement {

    @NotNull
    List<GraphQLDirective> getDirectives();

}
