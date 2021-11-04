package com.intellij.lang.jsgraphql.schema;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class GraphQLKnownTypes {
    public static final String INTROSPECTION_QUERY_INTROSPECTION_META = "__QueryIntrospectionMeta";
    public static final String INTROSPECTION_TYPE_NAME_META = "__TypeNameMeta";
    public static final String INTROSPECTION_SCHEMA = "__Schema";
    public static final String INTROSPECTION_TYPE = "__Type";
    public static final String INTROSPECTION_FIELD = "__Field";
    public static final String INTROSPECTION_INPUT_VALUE = "__InputValue";
    public static final String INTROSPECTION_ENUM_VALUE = "__EnumValue";
    public static final String INTROSPECTION_TYPE_KIND = "__TypeKind";
    public static final String INTROSPECTION_DIRECTIVE = "__Directive";
    public static final String INTROSPECTION_DIRECTIVE_LOCATION = "__DirectiveLocation";

    public static final Set<String> INTROSPECTION_TYPES = Set.of(
        INTROSPECTION_QUERY_INTROSPECTION_META,
        INTROSPECTION_TYPE_NAME_META,
        INTROSPECTION_SCHEMA,
        INTROSPECTION_TYPE,
        INTROSPECTION_FIELD,
        INTROSPECTION_INPUT_VALUE,
        INTROSPECTION_ENUM_VALUE,
        INTROSPECTION_TYPE_KIND,
        INTROSPECTION_DIRECTIVE,
        INTROSPECTION_DIRECTIVE_LOCATION
    );

    public static final Set<String> DEFAULT_DIRECTIVES = Set.of("deprecated", "skip", "include", "specifiedBy");

    public static boolean isIntrospectionType(@NotNull String name) {
        return INTROSPECTION_TYPES.contains(name);
    }
}
