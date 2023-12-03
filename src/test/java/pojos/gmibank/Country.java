package pojos.gmibank;

import java.io.Serializable;

public class Country implements Serializable {
	private int id;
	private String name;
	private Object states;

	public Country() {
	}

	public Country(int id, String name, Object states) {
		this.id = id;
		this.name = name;
		this.states = states;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setStates(Object states){
		this.states = states;
	}

	public Object getStates(){
		return states;
	}

	@Override
 	public String toString(){
		return 
			"Country{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",states = '" + states + '\'' + 
			"}";
		}
}