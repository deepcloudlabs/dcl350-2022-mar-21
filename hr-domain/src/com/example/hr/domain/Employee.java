package com.example.hr.domain;

// Domain -> Sub-domain      -> Sub-domain ....
//               |                  |
//               V                  V 
//           Bounded-context    Bounded-context
//				 |                  |
//				 V                  V
//           1 or more μS       1 or more μS
// Ubiquitous Language: Employee, TcKimlikNo, FullName, 
//                       Iban, Salary, Photo, JobStyle, Department  
// DDD: I) Entity Class -> i) Persistent ii) Identity iii) Mutable
//     II) Value Object
//    III) Aggregate -> Entity
public class Employee {
	private TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Salary salary;
	private Photo photo;
	private JobStyle jobStyle; // ex: "FULL-TIME", "PART-TIME"
	private Department department;
}
