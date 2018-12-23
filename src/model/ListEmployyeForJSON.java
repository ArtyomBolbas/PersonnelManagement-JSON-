package model;

import java.util.List;

public class ListEmployyeForJSON {

	private List<Employee> listEmployees;

	public ListEmployyeForJSON(List<Employee> listEmployees) {
		super();
		this.listEmployees = listEmployees;
	}

	public List<Employee> getListEmployees() {
		return listEmployees;
	}

	public void setListEmployees(List<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listEmployees == null) ? 0 : listEmployees.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListEmployyeForJSON other = (ListEmployyeForJSON) obj;
		if (listEmployees == null) {
			if (other.listEmployees != null)
				return false;
		} else if (!listEmployees.equals(other.listEmployees))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListEmployyeForJSON [listEmployees=" + listEmployees + "]";
	}

}
