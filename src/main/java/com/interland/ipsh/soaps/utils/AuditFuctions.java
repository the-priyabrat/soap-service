package com.interland.ipsh.soaps.utils;

public enum AuditFuctions {
	CREATE("C"), MODIFY("M"), DELETE("D"), VERIFY("V");

	public final String function;

	private AuditFuctions(String fucntion) {
		this.function = fucntion;
	}

	public String getFunction() {
		return function;
	}

}
