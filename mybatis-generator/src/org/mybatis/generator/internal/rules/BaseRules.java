/*
 *  Copyright 2006 The Apache Software Foundation
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
package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.TableConfiguration;

/**
 * This class centralizes all the rules related to code generation - including
 * the methods and objects to create, and certain attributes related to those
 * objects.
 * 
 * @author Jeff Butler
 */
public abstract class BaseRules implements Rules {

	protected TableConfiguration tableConfiguration;
	protected IntrospectedTable introspectedTable;

	/**
     * 
     */
	public BaseRules(IntrospectedTable introspectedTable) {
		super();
		this.introspectedTable = introspectedTable;
		this.tableConfiguration = introspectedTable.getTableConfiguration();
	}

	/**
	 * Implements the rule for generating the insert SQL Map element and DAO
	 * method. If the insert statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateInsert() {
		return tableConfiguration.isInsertStatementEnabled();
	}

	/**
	 * Implements the rule for generating the insert selective SQL Map element
	 * and DAO method. If the insert statement is allowed, then generate the
	 * element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateInsertSelective() {
		return tableConfiguration.isInsertStatementEnabled();
	}

	/**
	 * Calculates the class that contains all fields. This class is used as the
	 * insert statement parameter, as well as the returned value from the select
	 * by primary key method. The actual class depends on how the domain model
	 * is generated.
	 * 
	 * @return the type of the class that holds all fields
	 */
	public FullyQualifiedJavaType calculateAllFieldsClass() {

		String answer;

		if (generateRecordWithBLOBsClass()) {
			answer = introspectedTable.getRecordWithBLOBsType();
		} else if (generateBaseRecordClass()) {
			answer = introspectedTable.getBaseRecordType();
		} else {
			answer = introspectedTable.getPrimaryKeyType();
		}

		return new FullyQualifiedJavaType(answer);
	}

	/**
	 * Implements the rule for generating the update by primary key without
	 * BLOBs SQL Map element and DAO method. If the table has a primary key as
	 * well as other non-BLOB fields, and the updateByPrimaryKey statement is
	 * allowed, then generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
		boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
				&& introspectedTable.hasPrimaryKeyColumns() && introspectedTable.hasBaseColumns();

		return rc;
	}

	/**
	 * Implements the rule for generating the update by primary key with BLOBs
	 * SQL Map element and DAO method. If the table has a primary key as well as
	 * other BLOB fields, and the updateByPrimaryKey statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateUpdateByPrimaryKeyWithBLOBs() {
		boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
				&& introspectedTable.hasPrimaryKeyColumns() && introspectedTable.hasBLOBColumns();

		return rc;
	}

	/**
	 * Implements the rule for generating the update by primary key selective
	 * SQL Map element and DAO method. If the table has a primary key as well as
	 * other fields, and the updateByPrimaryKey statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateUpdateByPrimaryKeySelective() {
		boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
				&& introspectedTable.hasPrimaryKeyColumns()
				&& (introspectedTable.hasBLOBColumns() || introspectedTable.hasBaseColumns());

		return rc;
	}

	/**
	 * Implements the rule for generating the delete by primary key SQL Map
	 * element and DAO method. If the table has a primary key, and the
	 * deleteByPrimaryKey statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateDeleteByPrimaryKey() {
		boolean rc = tableConfiguration.isDeleteByPrimaryKeyStatementEnabled()
				&& introspectedTable.hasPrimaryKeyColumns();

		return rc;
	}

	/**
	 * Implements the rule for generating the delete by example SQL Map element
	 * and DAO method. If the deleteByParams statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateDeleteByParams() {
		boolean rc = tableConfiguration.isDeleteByParamsStatementEnabled();

		return rc;
	}

	/**
	 * Implements the rule for generating the result map without BLOBs. If
	 * either select method is allowed, then generate the result map.
	 * 
	 * @return true if the result map should be generated
	 */
	public boolean generateBaseResultMap() {
		boolean rc = tableConfiguration.isSelectByParamsStatementEnabled()
				|| tableConfiguration.isSelectByPrimaryKeyStatementEnabled();

		return rc;
	}

	/**
	 * Implements the rule for generating the result map with BLOBs. If the
	 * table has BLOB columns, and either select method is allowed, then
	 * generate the result map.
	 * 
	 * @return true if the result map should be generated
	 */
	public boolean generateResultMapWithBLOBs() {
		boolean rc = (tableConfiguration.isSelectByParamsStatementEnabled() || tableConfiguration
				.isSelectByPrimaryKeyStatementEnabled()) && introspectedTable.hasBLOBColumns();

		return rc;
	}

	/**
	 * Implements the rule for generating the SQL example where clause element.
	 * 
	 * In iBATIS2, generate the element if the selectByParams, deleteByParams,
	 * updateByParams, or countByParams statements are allowed.
	 * 
	 * In MyBatis3, generate the element if the selectByParams,
	 * deleteByParams, or countByParams statements are allowed.
	 * 
	 * @return true if the SQL where clause element should be generated
	 */
	public boolean generateSQLExampleWhereClause() {
		boolean rc = tableConfiguration.isSelectByParamsStatementEnabled()
				|| tableConfiguration.isDeleteByParamsStatementEnabled()
				|| tableConfiguration.isCountByParamsStatementEnabled();

		if (introspectedTable.getTargetRuntime() == TargetRuntime.IBATIS2) {
			rc |= tableConfiguration.isUpdateByParamsStatementEnabled();
		}

		return rc;
	}

	/**
	 * Implements the rule for generating the SQL example where clause element
	 * specifically for use in the update by example methods.
	 * 
	 * In iBATIS2, do not generate the element.
	 * 
	 * In MyBatis3, generate the element if the updateByParams statements are
	 * allowed.
	 * 
	 * @return true if the SQL where clause element should be generated
	 */
	public boolean generateMyBatis3UpdateByParamsWhereClause() {
		return introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3
				&& tableConfiguration.isUpdateByParamsStatementEnabled();
	}

	/**
	 * Implements the rule for generating the select by primary key SQL Map
	 * element and DAO method. If the table has a primary key as well as other
	 * fields, and the selectByPrimaryKey statement is allowed, then generate
	 * the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateSelectByPrimaryKey() {
		boolean rc = tableConfiguration.isSelectByPrimaryKeyStatementEnabled()
				&& introspectedTable.hasPrimaryKeyColumns()
				&& (introspectedTable.hasBaseColumns() || introspectedTable.hasBLOBColumns());

		return rc;
	}

	/**
	 * Implements the rule for generating the select by example without BLOBs
	 * SQL Map element and DAO method. If the selectByParams statement is
	 * allowed, then generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateSelectByParamsWithoutBLOBs() {
		return tableConfiguration.isSelectByParamsStatementEnabled();
	}

	/**
	 * Implements the rule for generating the select by example with BLOBs SQL
	 * Map element and DAO method. If the table has BLOB fields and the
	 * selectByParams statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	public boolean generateSelectByParamsWithBLOBs() {
		boolean rc = tableConfiguration.isSelectByParamsStatementEnabled() && introspectedTable.hasBLOBColumns();

		return rc;
	}

	/**
	 * Implements the rule for generating an example class. The class should be
	 * generated if the selectByParams or deleteByParams or countByParams
	 * methods are allowed.
	 * 
	 * @return true if the example class should be generated
	 */
	public boolean generateExampleClass() {
		boolean rc = tableConfiguration.isSelectByParamsStatementEnabled()
				|| tableConfiguration.isDeleteByParamsStatementEnabled()
				|| tableConfiguration.isCountByParamsStatementEnabled()
				|| tableConfiguration.isUpdateByParamsStatementEnabled();

		return rc;
	}

	public boolean generateCountByParams() {
		boolean rc = tableConfiguration.isCountByParamsStatementEnabled();

		return rc;
	}

	public boolean generateUpdateByParamsSelective() {
		boolean rc = tableConfiguration.isUpdateByParamsStatementEnabled();

		return rc;
	}

	public boolean generateUpdateByParamsWithoutBLOBs() {
		boolean rc = tableConfiguration.isUpdateByParamsStatementEnabled()
				&& (introspectedTable.hasPrimaryKeyColumns() || introspectedTable.hasBaseColumns());

		return rc;
	}

	public boolean generateUpdateByParamsWithBLOBs() {
		boolean rc = tableConfiguration.isUpdateByParamsStatementEnabled() && introspectedTable.hasBLOBColumns();

		return rc;
	}

	public IntrospectedTable getIntrospectedTable() {
		return introspectedTable;
	}

	public boolean generateBaseColumnList() {
		return generateSelectByPrimaryKey() || generateSelectByParamsWithoutBLOBs();
	}

	public boolean generateBlobColumnList() {
		return introspectedTable.hasBLOBColumns()
				&& (tableConfiguration.isSelectByParamsStatementEnabled() || tableConfiguration
						.isSelectByPrimaryKeyStatementEnabled());
	}
}
