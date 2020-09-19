package co.com.fxmanager.auth.persistence.entities.jpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = MailEntity.TableInfo.TABLE_NAME)
@Setter
public class MailEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "mail";
		public static final String ID = "id";
		public static final String SUBJECT = "subject";
		public static final String CONTEN = "content";
		public static final String CONTEN_TYPE = "contenType";
		public static final String CREATION_DATE = "creationDate";
		public static final String RECIPIENTS_TO = "recipientsTo";
		public static final String RECIPIENTS_BCC = "recipientsBCC";
		public static final String RECIPIENTS_CC = "recipientsCC";
	}

	public MailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	private Long id;

	@Column(name = TableInfo.SUBJECT)
	private String subject;

	@Column(name = TableInfo.CONTEN)
	private String content;

	@Column(name = TableInfo.CONTEN_TYPE)
	private String contenType;

	@Column(name = TableInfo.CREATION_DATE)
	private LocalDateTime creationDate;

	@Column(name = TableInfo.RECIPIENTS_TO)
	private String recipientsTo;

	@Column(name = TableInfo.RECIPIENTS_BCC)
	private String recipientsBCC;

	@Column(name = TableInfo.RECIPIENTS_CC)
	private String recipientsCC;

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getContenType() {
		return contenType;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getRecipientsTo() {
		return recipientsTo;
	}

	public String getRecipientsBCC() {
		return recipientsBCC;
	}

	public String getRecipientsCC() {
		return recipientsCC;
	}

}
