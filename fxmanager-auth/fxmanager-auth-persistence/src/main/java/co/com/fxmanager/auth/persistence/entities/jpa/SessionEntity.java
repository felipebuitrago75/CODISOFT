package co.com.fxmanager.auth.persistence.entities.jpa;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = SessionEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class SessionEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "session";
		public static final String ID = "id";
		public static final String TOKEN = "token";
		public static final String DATE = "date";
		public static final String TIME = "time";
		public static final String IP = "ip";
		public static final String USER = "user";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String TOKEN = "token";
		public static final String DATE = "date";
		public static final String TIME = "time";
		public static final String IP = "ip";
		public static final String USER = "user";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	@Column(name = TableInfo.TOKEN)
	@NonNull
	private String token;
	@Column(name = TableInfo.DATE)
	@NonNull
	private LocalDate date;
	@Column(name = TableInfo.TIME)
	@NonNull
	private LocalTime time;
	@Column(name = TableInfo.IP)
	@NonNull
	private String ip;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.USER, referencedColumnName = UserEntity.TableInfo.ID)
	@NonNull
	private UserEntity user;

}
