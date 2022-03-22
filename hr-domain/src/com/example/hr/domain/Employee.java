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
//     II) Value Object -> Immutable
//    III) Aggregate -> Entity
@Aggregate
public class Employee {
	@IdentityReference
	private final TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Salary salary;
	private Photo photo;
	private JobStyle jobStyle; // ex: "FULL-TIME", "PART-TIME"
	private Department department;

	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
		this.department = builder.department;
	}

	public static class Builder {
		private final TcKimlikNo identity;
		private FullName fullName;
		private Iban iban;
		private Salary salary;
		private Photo photo;
		private JobStyle jobStyle; // ex: "FULL-TIME", "PART-TIME"
		private Department department;

		public Builder(String identity) {
			this.identity = TcKimlikNo.valueOf(identity);
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Salary.valueOf(value, currency);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Salary.valueOf(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder photo(String base64EncodedValue) {
			this.photo = Photo.of(base64EncodedValue);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder jobStyle(JobStyle value) {
			this.jobStyle = value;
			return this;
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}

		public Builder department(Department value) {
			this.department = value;
			return this;
		}

		public Employee build() {
			// Business Rule/Constraint/Validation/Invariant/... ?
			if (jobStyle.equals(JobStyle.FULL_TIME) && salary.getValue() < 4500)
				throw new IllegalArgumentException("Full time job should offer at lest 4500");
			if (department.equals(Department.IT) && salary.getValue() < 7_000)
				throw new IllegalArgumentException("IT salary must be greater than 7000");
			
			return new Employee(this);
		}

	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public Employee increaseSalary(double rate) {
		salary = this.salary.increment(rate);
		return this;
	}

}
