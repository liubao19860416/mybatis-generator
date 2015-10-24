package org.mybatis.generator.codegen.mybatis3.javamapper;

import java.util.List;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedCountByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedDeleteByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedDeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedInsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedInsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByParamsSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator;

public class AnnotatedClientGenerator extends JavaMapperGenerator {

	public AnnotatedClientGenerator() {
		super(false);
	}

	@Override
	protected void addCountByParamsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateCountByParams()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedCountByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addDeleteByParamsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByParams()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedDeleteByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedDeleteByPrimaryKeyMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addInsertMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsert()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedInsertMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addInsertSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedInsertSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addSelectByParamsWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByParamsWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addSelectByParamsWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByParamsWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectByPrimaryKeyMethodGenerator(false);
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByParamsSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByParamsSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByParamsWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByParamsWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByPrimaryKeySelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByPrimaryKeyWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	@Override
	public List<CompilationUnit> getExtraCompilationUnits() {
		SqlProviderGenerator sqlProviderGenerator = new SqlProviderGenerator();
		sqlProviderGenerator.setContext(context);
		sqlProviderGenerator.setIntrospectedTable(introspectedTable);
		sqlProviderGenerator.setProgressCallback(progressCallback);
		sqlProviderGenerator.setWarnings(warnings);
		return sqlProviderGenerator.getCompilationUnits();
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		// No XML required by the annotated client
		return null;
	}
}
