package com.jamesXu.asynchttprequest;

import java.io.Serializable;

public class Journal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int journal;
	private int year;
	private int month;
	private String coverPicture;
	private String releaseTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJournal() {
		return journal;
	}

	public void setJournal(int journal) {
		this.journal = journal;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Override
	public String toString() {
		return "Journal [id=" + id + ", journal=" + journal + ", year=" + year
				+ ", month=" + month + ", coverPicture=" + coverPicture
				+ ", releaseTime=" + releaseTime + "]";
	}
	

}
