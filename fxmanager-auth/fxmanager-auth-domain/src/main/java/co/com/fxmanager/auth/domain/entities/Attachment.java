package co.com.fxmanager.auth.domain.entities;

import java.io.InputStream;
import java.util.Optional;

import com.google.common.net.MediaType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class Attachment {

	private String name;

	private String contenType;

	@Setter
	@NonNull
	private Optional<InputStream> source;

	public Attachment(@NonNull String name) {
		this.name = name;
		this.contenType = MediaType.OCTET_STREAM.toString();
		this.source = Optional.empty();
	}

	public Attachment(@NonNull String name, @NonNull String contenType) {
		this(name);
		this.contenType = contenType;
	}

}
