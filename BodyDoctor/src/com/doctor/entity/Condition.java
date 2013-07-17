package com.doctor.entity;

public class Condition {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TEST = "test";
	public static final String ALIAS = "alias";
	public static final String ETIOLOGY = "etiology";
	public static final String CURE = "cure";
	public static final String PREVENTION = "prevention";
	public static final String FOOD = "FOOD";
	public static final String ATTENTION="attention";
	public static final String SPECIALTY="specialty";
	public static final String SYMPTOM_DESCRIPTION="symptom_description";
	public static final String RANK_SCORE="rank_score";
	public static final String NAME_ORDER="name_order";
	
	
	private int id;
	private String name;
	private String description;
	private String test;
	private String alias;
	private String etiology;
	private String cure;
	private String prevention;
	private String food;
	private String attention;
	private String specialty;
	private String symptom_description;
	private int rank_score;
	private int name_order;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getEtiology() {
		return etiology;
	}
	public void setEtiology(String etiology) {
		this.etiology = etiology;
	}
	public String getCure() {
		return cure;
	}
	public void setCure(String cure) {
		this.cure = cure;
	}
	public String getPrevention() {
		return prevention;
	}
	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getSymptom_description() {
		return symptom_description;
	}
	public void setSymptom_description(String symptom_description) {
		this.symptom_description = symptom_description;
	}
	public int getRank_score() {
		return rank_score;
	}
	public void setRank_score(int rank_score) {
		this.rank_score = rank_score;
	}
	public int getName_order() {
		return name_order;
	}
	public void setName_order(int name_order) {
		this.name_order = name_order;
	}
	
	
}
