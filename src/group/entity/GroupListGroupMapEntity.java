package group.entity;

import java.util.ArrayList;

public class GroupListGroupMapEntity {

	private String name;
	private ArrayList<GroupEntity> list;
	
	public GroupListGroupMapEntity(String name) {
		this.name = name;
		list = new ArrayList<GroupEntity>();
	}
	
	
	public GroupListGroupMapEntity(String name, ArrayList<GroupEntity> list) {
		this.name = name;
		this.list = list;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<GroupEntity> getList() {
		return list;
	}
	public void setList(ArrayList<GroupEntity> list) {
		this.list = list;
	}
	
	
}
