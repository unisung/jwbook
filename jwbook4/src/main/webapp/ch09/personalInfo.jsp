<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>개인 정보</title>
</head>
<body>
<h2> 학생 정보 </h2>
<hr>
 <form method="post" action="/jwbook4/studentControl?action=update">
  <label>id</label>
  <input name="id"  value="${student.id}"  readonly required ><br>
  <label>이름</label>
  <input name="username"  value="${student.username}"  required="required"><br>
  <label>대학</label>
  <input name="univ"  value="${student.univ}"  required><br>
  <label>생일</label>
  <input name="birth" type="date"  value="${student.birth}"  required><br>
  <label>이메일</label>
  <input name="email"   value="${student.email}"  required><br>
  <button type="submit">수정</button> 
  <button type="button" onclick="check('${student.id}');">삭제</button> 
  <button type="button" onclick="goList()">학생리스트</button>
 </form>
<hr>
<script>
function goList(){
	location.href="studentControl";
}
function check(id){
	let ys = confirm("정말 탈퇴하시겠습니까?");
	if(ys) location.href="studentControl?action=delete&id="+id;
}
</script>
</body>
</html>