<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>My ToDo App</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
  rel="stylesheet" >
</head>
<body>
<div class="container bg-warning shadow mx-automt-5 p-5 w-75">
	<h2>My ToDo App</h2>
	<hr>
	<div class="input-group">
		<input id="item" placeholder = "할일을 입력하세요..">
		<button class="btn btn-primary" type="button" onclick="addItem()">할일 추가</button>
	</div>
	<hr>
	<ul id="todolist" class="list-group"></ul>
</div>
<script>
function addItem(){
	let todo = document.getElementById("item").value;
	let list = document.getElementById("todolist");
	let listitem = document.createElement("li"); // <li ></li>
	listitem.className = "d-flex list-group-item list-group-item-action list-group-item-warning";
	
	//버튼 추가
	let xbtn = document.createElement("button");// <button></button>
	xbtn.className = "btn-close ms-auto";
	//버튼에 이벤트핸들러 추가
	xbtn.onclick = function(e){
		let pnode = e.target.parentNode; //<li>과제 제출 [버튼]</li>
		console.log(pnode);
		list.removeChild(pnode);// <ul><li>과제 제출 [버튼]</li></ul>
	}
	
	listitem.innerText = todo;
	//listitem에 버튼 추가
    listitem.appendChild(xbtn);
	//listitem을 ul에 추가
	list.appendChild(listitem);
	
	todo = "";
	document.getElementById("item").focus();

}
</script>
</body>
</html>