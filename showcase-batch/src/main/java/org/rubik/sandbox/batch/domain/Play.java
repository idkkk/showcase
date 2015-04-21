package org.rubik.sandbox.batch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "play")
@XmlRootElement(name = "Play")
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_size")
	private long fileSize;
	
	@Column(name = "total_time_length")
	private int totalTimeLength;
	
	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "Name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@XmlElement(name = "Type")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@XmlElement(name = "FileName")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@XmlElement(name = "Size")
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@XmlElement(name = "Long")
	public int getTotalTimeLength() {
		return totalTimeLength;
	}

	public void setTotalTimeLength(int totalTimeLength) {
		this.totalTimeLength = totalTimeLength;
	}

	@XmlElement(name = "Start")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}