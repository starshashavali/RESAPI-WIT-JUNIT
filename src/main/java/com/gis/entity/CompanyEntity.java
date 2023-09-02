package com.gis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CompanyEntity_tbl")
public class CompanyEntity {
	@Id
	@GeneratedValue
	private Integer cid;
	
	private String  cname;
	
	private String address;

}
