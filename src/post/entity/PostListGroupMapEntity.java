package post.entity;

import java.util.ArrayList;

public class PostListGroupMapEntity {

	private String name;
	private ArrayList<PostEntity> list;
	
	public PostListGroupMapEntity(String name) {
		this.name = name;
		this.list = new ArrayList<PostEntity>();
	}
	
	public PostListGroupMapEntity(String name, ArrayList<PostEntity> list) {
		this.name = name;
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PostEntity> getList() {
		return list;
	}

	public void setList(ArrayList<PostEntity> list) {
		this.list = list;
	}
	
	
}
