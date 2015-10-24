/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mybatis.generator.codegen.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByParamsSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * @author Jeff Butler
 * 
 */
public class JavaMapperGenerator extends AbstractJavaClientGenerator {

	/**
     * 
     */
	public JavaMapperGenerator() {
		super(true);
	}

	public JavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
		super(requiresMatchedXMLGenerator);
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		String rootInterface = introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = context.getJavaClientGeneratorConfiguration().getProperty(
					PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}

		addCountByParamsMethod(interfaze);
		addDeleteByParamsMethod(interfaze);
		addDeleteByPrimaryKeyMethod(interfaze);
		addInsertMethod(interfaze);
		addInsertSelectiveMethod(interfaze);
		addSelectByParamsWithBLOBsMethod(interfaze);
		addSelectByParamsWithoutBLOBsMethod(interfaze);
		addSelectByPrimaryKeyMethod(interfaze);
		addUpdateByParamsSelectiveMethod(interfaze);
		addUpdateByParamsWithBLOBsMethod(interfaze);
		addUpdateByParamsWithoutBLOBsMethod(interfaze);
		addUpdateByPrimaryKeySelectiveMethod(interfaze);
		addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
		addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().clientGenerated(interfaze, null, introspectedTable)) {
			answer.add(interfaze);
		}

		List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
		if (extraCompilationUnits != null) {
			answer.addAll(extraCompilationUnits);
		}

		return answer;
	}

	protected void addCountByParamsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateCountByParams()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new CountByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByParamsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByParams()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addInsertMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsert()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addInsertSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByParamsWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByParamsWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByParamsWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByParamsWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByParamsSelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByParamsSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByParamsWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByParamsWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByParamsWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator, Interface interfaze) {
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.setProgressCallback(progressCallback);
		methodGenerator.setWarnings(warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}

	public List<CompilationUnit> getExtraCompilationUnits() {
		return null;
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}
}
