package com.intellij.lang.jsgraphql.schema;

import com.intellij.lang.jsgraphql.GraphQLBaseTestCase;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.lang.jsgraphql.types.schema.GraphQLSchema;
import com.intellij.lang.jsgraphql.types.schema.idl.SchemaPrinter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.UnaryOperator;


public class GraphQLSchemaBuilderTest extends GraphQLBaseTestCase {

    @Override
    protected @NotNull String getBasePath() {
        return "/schema";
    }

    public void testObjects() {
        doTest();
    }

    public void testInterfaces() {
        doTest();
    }

    public void testUnions() {
        doTest();
    }

    public void testInputObjects() {
        doTest();
    }

    public void testScalars() {
        doTest();
    }

    public void testEnums() {
        doTest();
    }

    public void testDirectives() {
        doTest(builder -> builder.includeDirectiveDefinitions(true));
    }

    public void testSchemas() {
        doTest();
    }

    private void doTest() {
        doTest(null);
    }

    private void doTest(@Nullable UnaryOperator<SchemaPrinter.Options> optionsBuilder) {
        myFixture.configureByFile(getTestName(true) + ".graphql");
        GraphQLSchemaProvider schemaProvider = GraphQLSchemaProvider.getInstance(myFixture.getProject());
        GraphQLSchema schema = schemaProvider.getSchemaInfo(myFixture.getFile()).getSchema();

        myFixture.configureByText("schema.graphql", new SchemaPrinter(getOptions(optionsBuilder)).print(schema));
        myFixture.checkResultByFile(getTestName(true) + "_schema.graphql");

        getOptions(optionsBuilder).useAstDefinitions(true);
        myFixture.configureByText("ast.graphql", new SchemaPrinter(getOptions(optionsBuilder).useAstDefinitions(true)).print(schema));
        myFixture.checkResultByFile(getTestName(true) + "_ast.graphql");
    }

    @NotNull
    private static SchemaPrinter.Options getOptions(@Nullable UnaryOperator<SchemaPrinter.Options> optionsBuilder) {
        SchemaPrinter.Options options = SchemaPrinter.Options.defaultOptions().includeDirectiveDefinitions(false);
        if (optionsBuilder != null) {
            return optionsBuilder.apply(options);
        }
        return options;
    }
}
