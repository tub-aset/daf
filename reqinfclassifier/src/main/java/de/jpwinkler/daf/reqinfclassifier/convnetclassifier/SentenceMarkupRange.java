package de.jpwinkler.daf.reqinfclassifier.convnetclassifier;

public class SentenceMarkupRange {

	private int start;
	private int length;
	private double strength;

	public int getStart() {
		return start;
	}

	public void setStart(final int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(final int length) {
		this.length = length;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(final double strength) {
		this.strength = strength;
	}

	public SentenceMarkupRange() {
		super();
	}

	public SentenceMarkupRange(final int start, final int length, final double strength) {
		super();
		this.start = start;
		this.length = length;
		this.strength = strength;
	}

}
