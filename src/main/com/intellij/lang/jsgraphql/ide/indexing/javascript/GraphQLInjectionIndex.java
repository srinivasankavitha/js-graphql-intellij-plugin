/*
 * Copyright (c) 2018-present, Jim Kynde Meyer
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.intellij.lang.jsgraphql.ide.indexing.javascript;

import com.intellij.lang.jsgraphql.GraphQLFileType;
import com.intellij.lang.jsgraphql.ide.injection.javascript.GraphQLLanguageInjectionUtil;
import com.intellij.lang.jsgraphql.ide.indexing.GraphQLIndexUtil;
import com.intellij.lang.jsgraphql.ide.findUsages.GraphQLFindUsagesUtil;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.util.indexing.*;
import com.intellij.util.io.EnumeratorStringDescriptor;
import com.intellij.util.io.KeyDescriptor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Indexes files which contain GraphQL Injection to enable discovery of type definitions written using injected Schema IDL.
 */
public class GraphQLInjectionIndex extends ScalarIndexExtension<String> {

    public static final ID<String, Void> NAME = ID.create(GraphQLInjectionIndex.class.getName());
    public static final String DATA_KEY = "true";

    private static final Map<String, Void> INJECTED_KEY = Collections.singletonMap(DATA_KEY, null);
    public static final int VERSION = 3;

    private final DataIndexer<String, Void, FileContent> myDataIndexer = inputData -> {
        final Ref<Boolean> isInjected = new Ref<>(Boolean.FALSE);
        inputData.getPsiFile().accept(new PsiRecursiveElementVisitor() {
            @Override
            public void visitElement(@NotNull PsiElement element) {
                if (GraphQLLanguageInjectionUtil.isGraphQLLanguageInjectionTarget(element)) {
                    isInjected.set(Boolean.TRUE);
                } else {
                    // visit deeper until injection found
                    super.visitElement(element);
                }
            }
        });
        return isInjected.get() == Boolean.FALSE ? Collections.emptyMap() : INJECTED_KEY;
    };

    private final Set<FileType> myIncludedFileTypes = GraphQLFindUsagesUtil.getService().getIncludedFileTypes();

    @NotNull
    @Override
    public ID<String, Void> getName() {
        return NAME;
    }

    @NotNull
    @Override
    public DataIndexer<String, Void, FileContent> getIndexer() {
        return myDataIndexer;
    }

    @NotNull
    @Override
    public KeyDescriptor<String> getKeyDescriptor() {
        return EnumeratorStringDescriptor.INSTANCE;
    }

    @NotNull
    @Override
    public FileBasedIndex.InputFilter getInputFilter() {
        return file -> file.getFileType() != GraphQLFileType.INSTANCE && myIncludedFileTypes.contains(file.getFileType());
    }

    @Override
    public boolean dependsOnFileContent() {
        return true;
    }

    @Override
    public int getVersion() {
        return GraphQLIndexUtil.INDEX_BASE_VERSION + VERSION;
    }
}
