package com.navercorp.fixturemonkey.report;

import java.util.List;
import java.util.stream.Collectors;

public final class UserInputManipulatorInfo implements ManipulatorsInfo {
	private final String methodName;
	private final List<Object> args;

	public UserInputManipulatorInfo(String methodName, List<Object> args) {
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public String toDebugLog(Integer idx) {
		List<String> strArgs = args
			.stream().map(Object::toString).collect(Collectors.toList());
		String joinedArgs = String.join(", ", strArgs);
		if (methodName.equals("set")) {
			return String.format(".set(%s)", joinedArgs);
		} else if (methodName.equals("size")) {
			return String.format(".size(%s)", joinedArgs);
		}
		throw new IllegalArgumentException(
			"Wrong method used"
		);
	}
}
