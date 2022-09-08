package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id @GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;
	
	private String name;
	
	@ManyToOne // 자식 입장에서는 부모가 하나인 케이스
	@JoinColumn(name="PARENT_ID")
	private Category parent;// 상위 카테고리.
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<Category>(); // 자식 카테고리 목록. 양방향으로 함. 카테고리 쭉 내려갈텐데 그걸 JPA에서 셀프로 매핑하는 것
	
	@ManyToMany 
	@JoinTable(name="CATEGORY_ITEM",
				joinColumns = @JoinColumn(name="CATEGORY_ID"), // 내가 조인하는 것
				inverseJoinColumns = @JoinColumn(name="ITEM_ID") // 반대쪽이 조인해야되는 것
	) // 중간테이블을 만드는 것. 중간테이블 있따치고 여러 설정 정보를 매핑하는 것이다.
	private List<Item> items = new ArrayList<Item>();
	
}
