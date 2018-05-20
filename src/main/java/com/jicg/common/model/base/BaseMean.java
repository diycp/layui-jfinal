package com.jicg.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseMean<M extends BaseMean<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setAction(java.lang.String action) {
		set("action", action);
		return (M)this;
	}
	
	public java.lang.String getAction() {
		return getStr("action");
	}

	public M setPid(java.lang.Integer pid) {
		set("pid", pid);
		return (M)this;
	}
	
	public java.lang.Integer getPid() {
		return getInt("pid");
	}

	public M setLevel(java.lang.Integer level) {
		set("level", level);
		return (M)this;
	}
	
	public java.lang.Integer getLevel() {
		return getInt("level");
	}

	public M setIssys(java.lang.Integer issys) {
		set("issys", issys);
		return (M)this;
	}
	
	public java.lang.Integer getIssys() {
		return getInt("issys");
	}

	public M setPname(java.lang.String pname) {
		set("pname", pname);
		return (M)this;
	}
	
	public java.lang.String getPname() {
		return getStr("pname");
	}

}