package co.com.fxmanager.auth.domain.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import com.google.common.net.MediaType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
public class Mail {

	public static final String ADDRESS_SEPARATOR = ",";

	@Setter
	@NonNull
	private Optional<String> uid;

	private String subject;

	private String content;

	private String contenType;

	private LocalDateTime creationDate;

	private List<String> recipientsTo;

	private List<String> recipientsBCC;

	private List<String> recipientsCC;

	private List<Attachment> attachments;

	public Mail(@NonNull String subject, @NonNull String content, LocalDateTime creationDate, @NonNull String... recipientsTo) {
		this.subject = subject;
		this.content = content;
		this.contenType = MediaType.HTML_UTF_8.toString();
		this.creationDate = creationDate;
		this.recipientsTo = List.of(recipientsTo);
		this.recipientsCC = new ArrayList<>();
		this.recipientsBCC = new ArrayList<>();
		this.attachments = new ArrayList<>();
		this.uid = Optional.empty();
	}

	public void setContenType(@NonNull String contenType) {
		this.contenType = contenType;
	}

	public void setRecipientsTo(@NonNull String recipientsToSeparatedByCommas) {
		setRecipients(this.recipientsTo, recipientsToSeparatedByCommas, ADDRESS_SEPARATOR);
	}

	public void setRecipientsTo(@NonNull String... recipientsTo) {
		this.recipientsTo = List.of(recipientsTo);
	}

	public void setRecipientsTo(@NonNull List<String> recipientsTo) {
		this.recipientsTo = recipientsTo;
	}

	public void setRecipientsBCC(@NonNull String recipientsBCCSeparatedByCommas) {
		setRecipients(this.recipientsBCC, recipientsBCCSeparatedByCommas, ADDRESS_SEPARATOR);
	}

	public void setRecipientsBCC(@NonNull String... recipientsBCC) {
		this.recipientsBCC = List.of(recipientsBCC);
	}

	public void setRecipientsBCC(@NonNull List<String> recipientsBCC) {
		this.recipientsBCC = recipientsBCC;
	}

	public void setRecipientsCC(@NonNull String recipientsCCSeparatedByCommas) {
		setRecipients(this.recipientsCC, recipientsCCSeparatedByCommas, ADDRESS_SEPARATOR);
	}

	public void setRecipientsCC(@NonNull String... recipientsCC) {
		this.recipientsCC = List.of(recipientsCC);
	}

	public void setRecipientsCC(@NonNull List<String> recipientsCC) {
		this.recipientsCC = recipientsCC;
	}

	private void setRecipients(@NonNull List<String> recipientsTarget, @NonNull String recipientsSeparatedByCommas,
			@NonNull String separator) {
		recipientsTarget.clear();
		StringTokenizer tokenizer = new StringTokenizer(recipientsSeparatedByCommas, separator);
		while (tokenizer.hasMoreTokens()) {
			recipientsTarget.add(tokenizer.nextToken());
		}
	}

	public void attachAll(@NonNull Attachment... attachments) {
		this.attachments.addAll(List.of(attachments));
	}

	public void attachAll(@NonNull List<Attachment> attachments) {
		this.attachments.addAll(attachments);
	}

	public void attach(@NonNull File file) throws FileNotFoundException {
		Attachment attachment = new Attachment(file.getName());
		attachment.setSource(Optional.of(new FileInputStream(file)));
		this.attachments.add(attachment);
	}

	public void attach(@NonNull File file, @NonNull String mediaType) throws FileNotFoundException {
		Attachment attachment = new Attachment(file.getName(), mediaType);
		attachment.setSource(Optional.of(new FileInputStream(file)));
		this.attachments.add(attachment);
	}

	public void attach(@NonNull String name, @NonNull byte[] bytes) {
		Attachment attachment = new Attachment(name);
		attachment.setSource(Optional.of(new ByteArrayInputStream(bytes)));
		this.attachments.add(attachment);
	}

	public void attach(@NonNull String name, @NonNull String mediaType, @NonNull byte[] bytes) {
		Attachment attachment = new Attachment(name, mediaType);
		attachment.setSource(Optional.of(new ByteArrayInputStream(bytes)));
		this.attachments.add(attachment);
	}
}
