package jp.bctech.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {
	@Id
	private Long id;
	private String name;
	private String parent;
}
