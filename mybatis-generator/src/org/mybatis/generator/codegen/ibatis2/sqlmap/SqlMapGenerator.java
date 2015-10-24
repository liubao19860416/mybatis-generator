/*
 *  Copyright 2008 The Apache Software Foundation
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
package org.mybatis.generator.codegen.ibatis2.sqlmap;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.CountByParamsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.DeleteByParamsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ExampleWhereClauseElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByParamsWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByParamsWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByParamsSelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByParamsWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByParamsWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SqlMapGenerator extends AbstractXmlGenerator {

	public SqlMapGenerator() {
		super();
	}

	protected XmlElement getSqlMapElement() {
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(getString("Progress.12", table.toString())); //$NON-NLS-1$
		XmlElement answer = new XmlElement("sqlMap"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
				introspectedTable.getIbatis2SqlMapNamespace()));

		context.getCommentGenerator().addRootComment(answer);

		addResultMapWithoutBLOBsElement(answer);
		addResultMapWithBLOBsElement(answer);
		addExampleWhereClauseElement(answer);
		addBaseColumnListElement(answer);
		addBlobColumnListElement(answer);
		addSelectByParamsWithBLOBsElement(answer);
		addSelectByParamsWithoutBLOBsElement(answer);
		addSelectByPrimaryKeyElement(answer);
		addDeleteByPrimaryKeyElement(answer);
		addDeleteByParamsElement(answer);
		addInsertElement(answer);
		addInsertSelectiveElement(answer);
		addCountByParamsElement(answer);
		addUpdateByParamsSelectiveElement(answer);
		addUpdateByParamsWithBLOBsElement(answer);
		addUpdateByParamsWithoutBLOBsElement(answer);
		addUpdateByPrimaryKeySelectiveElement(answer);
		addUpdateByPrimaryKeyWithBLOBsElement(answer);
		addUpdateByPrimaryKeyWithoutBLOBsElement(answer);

		return answer;
	}

	protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseResultMap()) {
			AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addExampleWhereClauseElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateSQLExampleWhereClause()) {
			AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addBaseColumnListElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBaseColumnList()) {
			AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addBlobColumnListElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateBlobColumnList()) {
			AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addSelectByParamsWithoutBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateSelectByParamsWithoutBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new SelectByParamsWithoutBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addSelectByParamsWithBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateSelectByParamsWithBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new SelectByParamsWithBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addDeleteByParamsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateDeleteByParams()) {
			AbstractXmlElementGenerator elementGenerator = new DeleteByParamsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addInsertElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateInsert()) {
			AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addInsertSelectiveElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateInsertSelective()) {
			AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addCountByParamsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateCountByParams()) {
			AbstractXmlElementGenerator elementGenerator = new CountByParamsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByParamsSelectiveElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByParamsSelective()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByParamsSelectiveElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByParamsWithBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByParamsWithBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByParamsWithBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByParamsWithoutBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByParamsWithoutBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByParamsWithoutBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByPrimaryKeyWithBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void addUpdateByPrimaryKeyWithoutBLOBsElement(XmlElement parentElement) {
		if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
			AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement);
		}
	}

	protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement) {
		elementGenerator.setContext(context);
		elementGenerator.setIntrospectedTable(introspectedTable);
		elementGenerator.setProgressCallback(progressCallback);
		elementGenerator.setWarnings(warnings);
		elementGenerator.addElements(parentElement);
	}

	@Override
	public Document getDocument() {
		Document document = new Document(XmlConstants.IBATIS2_SQL_MAP_PUBLIC_ID, XmlConstants.IBATIS2_SQL_MAP_SYSTEM_ID);
		document.setRootElement(getSqlMapElement());

		if (!context.getPlugins().sqlMapDocumentGenerated(document, introspectedTable)) {
			document = null;
		}

		return document;
	}
}
