package com.ezen.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date createDate = new Date();//시간표시 년월일시분초 형식, update에 참여안함
	@Column(updatable=false)
	private Long cnt=0L;	//update에는 참여 안함
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false)
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}



















