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
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * This interface centralizes all the rules related to code generation -
 * including the methods and objects to create, and certain attributes related
 * to those objects.
 * 
 * @author Jeff Butler
 */
public interface Rules {

	/**
	 * Implements the rule for generating the insert SQL Map element and DAO
	 * method. If the insert statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateInsert();

	/**
	 * Implements the rule for generating the insert selective SQL Map element
	 * and DAO method. If the insert statement is allowed, then generate the
	 * element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateInsertSelective();

	/**
	 * Calculates the class that contains all fields. This class is used as the
	 * insert statement parameter, as well as the returned value from the select
	 * by primary key method. The actual class depends on how the domain model
	 * is generated.
	 * 
	 * @return the type of the class that holds all fields
	 */
	FullyQualifiedJavaType calculateAllFieldsClass();

	/**
	 * Implements the rule for generating the update by primary key without
	 * BLOBs SQL Map element and DAO method. If the table has a primary key as
	 * well as other non-BLOB fields, and the updateByPrimaryKey statement is
	 * allowed, then generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateUpdateByPrimaryKeyWithoutBLOBs();

	/**
	 * Implements the rule for generating the update by primary key with BLOBs
	 * SQL Map element and DAO method. If the table has a primary key as well as
	 * other BLOB fields, and the updateByPrimaryKey statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateUpdateByPrimaryKeyWithBLOBs();

	/**
	 * Implements the rule for generating the update by primary key selective
	 * SQL Map element and DAO method. If the table has a primary key as well as
	 * other fields, and the updateByPrimaryKey statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateUpdateByPrimaryKeySelective();

	/**
	 * Implements the rule for generating the delete by primary key SQL Map
	 * element and DAO method. If the table has a primary key, and the
	 * deleteByPrimaryKey statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateDeleteByPrimaryKey();

	/**
	 * Implements the rule for generating the delete by example SQL Map element
	 * and DAO method. If the deleteByParams statement is allowed, then
	 * generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateDeleteByParams();

	/**
	 * Implements the rule for generating the result map without BLOBs. If
	 * either select method is allowed, then generate the result map.
	 * 
	 * @return true if the result map should be generated
	 */
	boolean generateBaseResultMap();

	/**
	 * Implements the rule for generating the result map with BLOBs. If the
	 * table has BLOB columns, and either select method is allowed, then
	 * generate the result map.
	 * 
	 * @return true if the result map should be generated
	 */
	boolean generateResultMapWithBLOBs();

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
	boolean generateSQLExampleWhereClause();

	/**
	 * Implements the rule for generating the SQL example where clause element
	 * specifically for use in the update by example methods.
	 * 
	 * In iBATIS2, do not generate the element.
	 * 
	 * In MyBatis, generate the element if the updateByParams statements are
	 * allowed.
	 * 
	 * @return true if the SQL where clause element should be generated
	 */
	boolean generateMyBatis3UpdateByParamsWhereClause();

	/**
	 * Implements the rule for generating the SQL base column list element.
	 * Generate the element if any of the select methods are enabled.
	 * 
	 * @return true if the SQL base column list element should be generated
	 */
	boolean generateBaseColumnList();

	/**
	 * Implements the rule for generating the SQL blob column list element.
	 * Generate the element if any of the select methods are enabled, and the
	 * table contains BLOB columns.
	 * 
	 * @return true if the SQL blob column list element should be generated
	 */
	boolean generateBlobColumnList();

	/**
	 * Implements the rule for generating the select by primary key SQL Map
	 * element and DAO method. If the table has a primary key as well as other
	 * fields, and the selectByPrimaryKey statement is allowed, then generate
	 * the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateSelectByPrimaryKey();

	/**
	 * Implements the rule for generating the select by example without BLOBs
	 * SQL Map element and DAO method. If the selectByParams statement is
	 * allowed, then generate the element and method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateSelectByParamsWithoutBLOBs();

	/**
	 * Implements the rule for generating the select by example with BLOBs SQL
	 * Map element and DAO method. If the table has BLOB fields and the
	 * selectByParams statement is allowed, then generate the element and
	 * method.
	 * 
	 * @return true if the element and method should be generated
	 */
	boolean generateSelectByParamsWithBLOBs();

	/**
	 * Implements the rule for generating an example class. The class should be
	 * generated if the selectByParams or deleteByParams or countByParams
	 * methods are allowed.
	 * 
	 * @return true if the example class should be generated
	 */
	boolean generateExampleClass();

	boolean generateCountByParams();

	boolean generateUpdateByParamsSelective();

	boolean generateUpdateByParamsWithoutBLOBs();

	boolean generateUpdateByParamsWithBLOBs();

	/**
	 * Implements the rule for determining whether to generate a primary key
	 * class. If you return false from this method, and the table has primary
	 * key columns, then the primary key columns will be added to the base
	 * class.
	 * 
	 * @return true if a separate primary key class should be generated
	 */
	boolean generatePrimaryKeyClass();

	/**
	 * Implements the rule for generating a base record.
	 * 
	 * @return true if the class should be generated
	 */
	boolean generateBaseRecordClass();

	/**
	 * Implements the rule for generating a record with BLOBs. If you return
	 * false from this method, and the table had BLOB columns, then the BLOB
	 * columns will be added to the base class.
	 * 
	 * @return true if the record with BLOBs class should be generated
	 */
	boolean generateRecordWithBLOBsClass();

	IntrospectedTable getIntrospectedTable();
}
