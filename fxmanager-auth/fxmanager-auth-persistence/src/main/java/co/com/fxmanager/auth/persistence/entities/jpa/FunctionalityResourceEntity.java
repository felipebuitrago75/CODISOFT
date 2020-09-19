package co.com.fxmanager.auth.persistence.entities.jpa;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FunctionalityResourceEntity {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "functionality_resource";
		public static final String FUNCTIONALITY = "functionality";
		public static final String RESOURCE = "resource";
	}
}
