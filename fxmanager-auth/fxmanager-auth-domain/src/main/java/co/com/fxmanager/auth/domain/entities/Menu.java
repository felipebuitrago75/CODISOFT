package co.com.fxmanager.auth.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Menu {

	@NonNull
	protected String name;

	@NonNull
	protected Integer index;

	protected Menu parent;

	protected String description;

	protected String target;

	@NonNull
	protected List<Menu> children;
	
	@SuppressWarnings("unused")
	private Menu() {
		//Usado para la deseralización de Jackson
	}

	public Menu(@NonNull String name, @NonNull Integer index) {
		this.name = name;
		this.index = index;
	}

	public Menu(@NonNull String name, @NonNull Integer index, @NonNull Menu parent) {
		this(name, index);
		this.parent = parent;
	}

	public Menu(@NonNull String name, @NonNull Integer index, @NonNull Menu parent, @NonNull String target) {
		this(name, index, parent);
		this.target = target;
	}

	public Menu(@NonNull String name, @NonNull Integer index, @NonNull Menu parent, @NonNull String description,
			@NonNull String target) {
		this(name, index, parent, target);
		this.description = description;

	}

	public Optional<Menu> getParent() {
		return Optional.ofNullable(parent);
	}

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	public Optional<String> getTarget() {
		return Optional.ofNullable(target);
	}

	public Optional<List<Menu>> getChildren() {
		return Optional.ofNullable(children);
	}

	public void add(@NonNull Menu menu) {
		if (getChildren().isEmpty()) {
			setChildren(new ArrayList<>());
		}
		getChildren().ifPresent(children -> children.add(menu));
	}
}
