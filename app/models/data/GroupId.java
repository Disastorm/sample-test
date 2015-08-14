package models.data;

import java.util.HashMap;
import java.util.Map;

public enum GroupId {
	STUDENT(1), COMPANY(2);

	private static Map<Integer, GroupId> idMap;
	private int value;

	private GroupId(int value) {
		this.value = value;
		populateMap(value, this);
	}

	public int getValue() {
		return value;
	}

	private static void populateMap(int value, GroupId obj) {
		if (idMap == null) {
			idMap = new HashMap<>();
		}
		idMap.put(value, obj);
	}

	public static GroupId getGroupId(int value) {
		return idMap.get(value);
	}
}
