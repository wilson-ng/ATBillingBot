package com.bot.billing.at.atbillingbot.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Hangout implements Serializable {

	private static final long serialVersionUID = 6434998902303065689L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private Member createdBy;
	
	@Column(name = "created_at")
	private ZonedDateTime createdAt;
	
	@Column(name = "chat_id")
	private Long chatId;
	
	@Column(name = "is_done")
	private Boolean isDone;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "member_hangouts", 
		joinColumns = { @JoinColumn(name = "member_id") },
		inverseJoinColumns = { @JoinColumn(name = "hangout_id") })
	private Set<Member> members;
	
	public Hangout(Long chatId, Member createdBy) {
		this.members = new HashSet<>();
		this.isDone = false;
		this.chatId = chatId;
		this.createdAt = ZonedDateTime.now();
		this.createdBy = createdBy;
	}
	
	public String getFormatedCreatedAt() {
		return String.format("%tH:%tM:%tS", this.createdAt, this.createdAt, this.createdAt);
	}
	
	public String toString() {
		return String.format("Hangout%nchat_id: %d%ncreated_at: %s%nby: %s%ndone: %b%nmembers: %s", 
				this.chatId, this.getFormatedCreatedAt(), this.createdBy.getUsername(), this.isDone, this.getListOfMembers());
	}
	
	public String lastOpenToString() {
		return "Last Open " + this.toString();
	}
	
	public String closeToString() {
		return "Close " + this.toString();
	}
	
	public String updateToString() {
		return "Update " + this.toString();
	}
	
	public String getListOfMembers() {
		String[] collectionOfMembers = this.members
				.stream()
				.map(Member::getUsername)
				.toArray(String[]::new);
		return String.join(", ", collectionOfMembers);
	}
}
