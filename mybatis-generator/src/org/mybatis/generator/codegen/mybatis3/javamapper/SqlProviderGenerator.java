/*
 *  Copyright 2010 The MyBatis Team
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

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.AbstractJavaProviderMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderApplyWhereMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderCountByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderDeleteByParamsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderInsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByParamsSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByParamsWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByParamsWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByPrimaryKeySelectiveMethodGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SqlProviderGenerator extends AbstractJavaGenerator {

	public SqlProviderGenerator() {
		super();
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		progressCallback.startTask(getString("Progress.18", //$NON-NLS-1$
				introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3SqlProviderType());
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(topLevelClass);

		boolean addApplyWhereMethod = false;
		addApplyWhereMethod |= addCountByParamsMethod(topLevelClass);
		addApplyWhereMethod |= addDeleteByParamsMethod(topLevelClass);
		addInsertSelectiveMethod(topLevelClass);
		addApplyWhereMethod |= addSelectByParamsWithBLOBsMethod(topLevelClass);
		addApplyWhereMethod |= addSelectByParamsWithoutBLOBsMethod(topLevelClass);
		addApplyWhereMethod |= addUpdateByParamsSelectiveMethod(topLevelClass);
		addApplyWhereMethod |= addUpdateByParamsWithBLOBsMethod(topLevelClass);
		addApplyWhereMethod |= addUpdateByParamsWithoutBLOBsMethod(topLevelClass);
		addUpdateByPrimaryKeySelectiveMethod(topLevelClass);

		if (addApplyWhereMethod) {
			addApplyWhereMethod(topLevelClass);
		}

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

		if (topLevelClass.getMethods().size() > 0) {
			if (context.getPlugins().providerGenerated(topLevelClass, introspectedTable)) {
				answer.add(topLevelClass);
			}
		}

		return answer;
	}

	protected boolean addCountByParamsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateCountByParams()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderCountByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected boolean addDeleteByParamsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateDeleteByParams()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderDeleteByParamsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderInsertSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
		}
	}

	protected boolean addSelectByParamsWithBLOBsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateSelectByParamsWithBLOBs()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected boolean addSelectByParamsWithoutBLOBsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateSelectByParamsWithoutBLOBs()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected boolean addUpdateByParamsSelectiveMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateUpdateByParamsSelective()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByParamsSelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected boolean addUpdateByParamsWithBLOBsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateUpdateByParamsWithBLOBs()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByParamsWithBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected boolean addUpdateByParamsWithoutBLOBsMethod(TopLevelClass topLevelClass) {
		boolean rc = false;
		if (introspectedTable.getRules().generateUpdateByParamsWithoutBLOBs()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByParamsWithoutBLOBsMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
			rc = true;
		}

		return rc;
	}

	protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByPrimaryKeySelectiveMethodGenerator();
			initializeAndExecuteGenerator(methodGenerator, topLevelClass);
		}
	}

	protected void addApplyWhereMethod(TopLevelClass topLevelClass) {
		AbstractJavaProviderMethodGenerator methodGenerator = new ProviderApplyWhereMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, topLevelClass);
	}

	protected void initializeAndExecuteGenerator(AbstractJavaProviderMethodGenerator methodGenerator,
			TopLevelClass topLevelClass) {
		methodGenerator.setContext(context);
		methodGenerator.setIntrospectedTable(introspectedTable);
		methodGenerator.setProgressCallback(progressCallback);
		methodGenerator.setWarnings(warnings);
		methodGenerator.addClassElements(topLevelClass);
	}
}
