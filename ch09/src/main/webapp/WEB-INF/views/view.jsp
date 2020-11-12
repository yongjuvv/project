<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('#boardDisp').load('list.do',"pageNum=${pageNum}");
	});
</script></head><body>
<div class="container" align="center">
	<h2 class="text-primary">게시글 상세 내역</h2>
<table class="table table-hover">
	<tr><td>제목</td><td>${board.subject }</td></tr>
	<tr><td>작성자</td><td>${board.writer}</td></tr>
	<tr><td>내용</td><td>${board.content}</td></tr>
	<tr><td>이메일</td><td>${board.email}</td></tr>
	<tr><td>조회수</td><td>${board.readcount}</td></tr>
	<tr><td>작성일</td><td>${board.reg_date}</td></tr>
	<tr><td colspan="2" align="center">
		<a class="btn btn-info" href="list.do?pageNum=${pageNum}">게시글 목록</a>
		<a class="btn btn-warning" 
			href="updateForm.do?num=${board.num}&pageNum=${pageNum}">수정</a>
		<a class="btn btn-danger" 
			href="deleteForm.do?num=${board.num}&pageNum=${pageNum}">삭제</a>
		<a class="btn btn-success" 
			href="insertForm.do?nm=${board.num}&pageNum=${pageNum}">답글</a>
	</td></tr>
</table>
<div id="boardDisp"></div>
</div>
</body>
</html>