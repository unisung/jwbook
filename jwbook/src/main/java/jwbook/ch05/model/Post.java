package jwbook.ch05.model;

import java.time.LocalDateTime;

public class Post {
 private int id;
 private String title;
 private String content;
 private LocalDateTime created;
 private String writer;
 private String email;
 
 //생성자
public Post(int id, String title, String content, LocalDateTime created, String writer, String email) {
	this.id = id;
	this.title = title;
	this.content = content;
	this.created = created;
	this.writer = writer;
	this.email = email;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public LocalDateTime getCreated() {
	return created;
}

public void setCreated(LocalDateTime created) {
	this.created = created;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "Post [id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", writer="
			+ writer + ", email=" + email + "]";
}


}
