package com.jzzx.po;


import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

public class User {
	
	/*
	 *在实体类中，定义视图，来区分服务端返回的数据内容
	 */
	public interface showSimpleInfos {}
	public interface showDetailInfos extends showSimpleInfos {}
	
	private String id;
	@NotBlank//定义此属性不能为空，配合controller中的@valid注解使用
	private String name;
	private String age;
	
	//如何处理日期类型
	private Date birthday;
	
	//在get方法上定义视图
	@JsonView(showSimpleInfos.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonView(showSimpleInfos.class)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonView(showDetailInfos.class)
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@JsonView(showDetailInfos.class)
	//@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
