package org.jsp.lombokpractice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Employee {
private int id;
private String name;
private String desg;
private double salary;
private long phone;
private String password;

}
