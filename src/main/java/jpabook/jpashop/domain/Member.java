package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID") // 대소문자인지는 DBA의 룰마다 다름
	private Long id;
	
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	// 예시라서 넣은 것이지, 보통 멤버에서 orders를 꺼내진 않음. 따로 조회하는 게 맞지.
	@OneToMany(mappedBy = "member")// 반대니까 컬렉션. 멤버 입장에서의 주문은 oneToMany
	private List<Order> orders = new ArrayList<Order>(); // 메모리를 조금 쓰지만 데이터 NPE 방지 외 여러 장점 있음
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	} 
}
