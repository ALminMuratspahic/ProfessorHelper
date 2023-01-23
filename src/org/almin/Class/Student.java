package org.almin.Class;

public class Student {
	private int logNumber;
	private String studentName;
	private long startTime, endTime, resultTime, min, sec;

	public Student(int logNumber) {
		this.logNumber = logNumber;
	}

	public Student() {

	}

	public int getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(int logNumber) {
		this.logNumber = logNumber;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getResultTime() {
		return resultTime;
	}

	public void setResultTime(long resultTime) {
		this.resultTime = resultTime;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getSec() {
		return sec;
	}

	public void setSec(long sec) {
		this.sec = sec;
	}

	@Override
	public String toString() {
		return "Student{" + "logNumber=" + logNumber + '}';
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
