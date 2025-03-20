package jwbook.ch05.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
 private int id;
 private String title;
 private String content;
 private LocalDateTime created;
 private String writer;
 private String email;
 
//게시글 리스트 만들기
public static List<Post> list = new ArrayList<Post>();
static int seq = 0;

static {
	list.add(new Post(++seq, "위대하다", "밥을 많이 먹어서", LocalDateTime.now(), "lee", "lee@naver.com"));
	list.add(new Post(++seq, "나의 성격 유형", "infj", LocalDateTime.now(), "kim", "kim@naver.com"));
	list.add(new Post(++seq, "가을바람", "가을은 추남의 계절이다.", LocalDateTime.now(), "park", "park@naver.com"));
	list.add(new Post(++seq, "언젠가부터", "사람들과 이해관계가 힘들어지는거 같다.", LocalDateTime.now(), "lee", "lee@naver.com"));
	list.add(new Post(++seq, "node js는", "너무 재미있어요...", LocalDateTime.now(), "", ""));
}


public Post(String title, String content, LocalDateTime created, String writer, String email) {
	this.title = title;
	this.content = content;
	this.created = created;
	this.writer = writer;
	this.email = email;
	list.add(new Post(++seq, title, content, created, writer, email));
}

public Post(int id, String title, String content, LocalDateTime created, String writer, String email) {
	this.id = id;
	this.title = title;
	this.content = content;
	this.created = created;
	this.writer = writer;
	this.email = email;
}

@Override
public String toString() {
	return "Post [id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", writer="
			+ writer + ", email=" + email + "]";
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
 

}
