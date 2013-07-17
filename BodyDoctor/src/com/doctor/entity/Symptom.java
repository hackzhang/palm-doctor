package com.doctor.entity;

public class Symptom {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String AGEFLAG = "ageflag";
	public static final String COMMON = "ageflag";
	public static final String ALIAS = "alias";
	public static final String RANK_SCORE = "rank_score";
	public static final String NAME_ORDER = "name_order";
	public static final String COLLECT = "collect";
	
	private int id;
	private String name;
	private String sex;
	private String ageflag;
	private String common;
	private String alias;
	private String rankscore;
	private int name_order;
	private String collect;
	
	public String getCollect() {
		return collect;
	}
	public void setCollect(String collect) {
		this.collect = collect;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAgeflag() {
		return ageflag;
	}
	public void setAgeflag(String ageflag) {
		this.ageflag = ageflag;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getRankscore() {
		return rankscore;
	}
	public void setRankscore(String rankscore) {
		this.rankscore = rankscore;
	}
	public int getName_order() {
		return name_order;
	}
	public void setName_order(int name_order) {
		this.name_order = name_order;
	}
	
	
}
