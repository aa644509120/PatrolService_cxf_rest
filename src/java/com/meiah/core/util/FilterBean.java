/** * A级 */
package com.meiah.core.util;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class FilterBean {

	private String property;

	private String value;

	private String opt;

	private String type;

	public FilterBean() {
		super();
	}

	public FilterBean(String property, String opt, String value, String type) {
		super();
		this.property = property;
		this.value = value;
		this.opt = opt;
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getOrmValue() {
		if ("string".equals(type))
			return value;
		else if ("short".equals(type))
			return Short.valueOf(value);
		else if ("int".equals(type))
			return Integer.valueOf(value);
		else if ("long".equals(type))
			return Long.valueOf(value);
		else if ("float".equals(type))
			return Float.valueOf(value);
		else if ("double".equals(type))
			return Double.valueOf(value);
		else if ("date".equals(type))
			return DateUtil.getDate(value);
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Criterion toCriterion() {
		Criterion criterion = null;
		if ("=".equals(getOpt()))
			criterion = Restrictions.eq(getProperty(), getOrmValue());
		else if ("like".equals(getOpt()))
			criterion = Restrictions.like(getProperty(), getOrmValue());
		else if (">".equals(getOpt()))
			criterion = Restrictions.gt(getProperty(), getOrmValue());
		else if (">=".equals(getOpt()))
			criterion = Restrictions.ge(getProperty(), getOrmValue());
		else if ("<".equals(getOpt()))
			criterion = Restrictions.lt(getProperty(), getOrmValue());
		else if ("<=".equals(getOpt()))
			criterion = Restrictions.le(getProperty(), getOrmValue());
		else if ("<>".equals(getOpt()) || "!=".equals(getOpt()))
			criterion = Restrictions.ne(getProperty(), getOrmValue());
		return criterion;
	}

	public String toSql() {
		return getProperty() + " " + getOpt() + " " + "?";
	}

	public String toSqlNameQuery() {
		return getProperty() + " " + getOpt() + " " + ":" + getProperty();
	}
}
