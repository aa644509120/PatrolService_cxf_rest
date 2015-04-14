/** * A级 */
package com.meiah;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;

/**
 * Author:hepl
 * hepl@xm-my.com.cn
 * Date: 2010-07-21
 */
public class SQLServer2005Dialect extends SQLServerDialect {
	
	public SQLServer2005Dialect() {
		super();

		registerColumnType(Types.VARCHAR, 1073741823, "NVARCHAR(MAX)");
		registerColumnType(Types.VARCHAR, 2147483647, "VARCHAR(MAX)");
		registerColumnType(Types.VARBINARY, 2147483647, "VARBINARY(MAX)");
	}

	/**
	 * Add a LIMIT clause to the given SQL SELECT
	 *
	 * The LIMIT SQL will look like:
	 *
	 * WITH query AS
	 * (SELECT TOP 100 percent ROW_NUMBER() OVER (ORDER BY orderby) as __hibernate_row_nr__, ... original_query)
	 * SELECT *
	 * FROM query
	 * WHERE __hibernate_row_nr__ > offset
	 * ORDER BY __hibernate_row_nr__
	 *
	 * @param querySqlString The SQL statement to base the limit query off of.
	 * @param offset         Offset of the first row to be returned by the query (zero-based)
	 * @param last           Maximum number of rows to be returned by the query
	 * @return A new SQL statement with the LIMIT clause applied.
	 */
	@Override
	public String getLimitString(String querySqlString, int offset, int last) {
		/*
				 * WITH query AS
				 *     (SELECT TOP 100 percent ROW_NUMBER() OVER (ORDER BY orderby) as __hibernate_row_nr__, ... original_query)
				 * SELECT *
				 * FROM query
				 * WHERE __hibernate_row_nr__ > offset
				 * ORDER BY __hibernate_row_nr__
				 */
		StringBuffer pagingBuilder = new StringBuffer();
		String orderby = getOrderByPart(querySqlString);
		String distinctStr = "";

		String loweredString = querySqlString.toLowerCase();
		String sqlPartString = querySqlString;
		if (loweredString.trim().startsWith("select")) {
			int index = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
		}
		pagingBuilder.append(sqlPartString);

		// if no ORDER BY is specified use fake ORDER BY field to avoid errors
		if (orderby == null || orderby.length() == 0) {
			orderby = "ORDER BY CURRENT_TIMESTAMP";
		}

		StringBuffer result = new StringBuffer();
		result.append("WITH query AS (SELECT ")
				.append(distinctStr)
				.append("TOP 100 PERCENT ")
				.append(" ROW_NUMBER() OVER (")
				.append(orderby)
				.append(") as __hibernate_row_nr__, ")
				.append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __hibernate_row_nr__ > ")
				.append(offset)
				.append(" and __hibernate_row_nr__ <= ")//原来没有,2010-5-20加入
				.append(last+offset)//原来没有,2010-5-20加入，不加入的话会直接加载在第几页和该页之后的数据到内存中
				.append(" ORDER BY __hibernate_row_nr__");

		return result.toString();
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public boolean useMaxForLimit() {
		return false;
	}

	static String getOrderByPart(String sql) {
		String loweredString = sql.toLowerCase();
		int orderByIndex = loweredString.indexOf("order by");
		if (orderByIndex != -1) {
			// if we find a new "order by" then we need to ignore
			// the previous one since it was probably used for a subquery
			return sql.substring(orderByIndex);
		} else {
			return "";
		}
	}
}
