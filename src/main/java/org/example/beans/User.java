package org.example.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = { "employerUser" })
public class User implements Serializable {
	@Id
	private Integer userId;

	private String name;
	private String lastname;
	private String email;

}
