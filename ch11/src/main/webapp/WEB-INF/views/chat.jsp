<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {height: 450px; border:2px solid green;
		table-layout:fixed; overflow:hidden;}
	#chatMessage{height:400px; overflow: scroll; }
</style>
<script type="text/javascript">
	var websocket; //웹소켓 변수
	var bot = 'AI챗봇';
	$(function() {
		//각각의 버튼을 클릭했을 때
		$('#enterBtn').click(function() {
			connect();
			
		});
		$('#exitBtn').click(function() {
			disconnect();
			
		});
		$('#sendBtn').click(function() {
			send();
			
		});
		//키보드를 누르면 
		$('#message').keypress(function () {
			//				IE이면? 		IE에서 key값	  IE가 아닌 key값
			var keycode = event.keyCode?event.keyCode:event.which;
			if (keycode == 13) { //enter를 눌렀으면 (13이 아스키 enter)
				send();
			}
			event.stopPropagation(); // 이벤트를 전달하지 말고 멈춤
		});
	});
	function send() {
		var nickname= $('#nickname').val();
		var msg = $('#message').val(); //입력한 메시지 읽기
		websocket.send(nickname+"=>" +msg);
		if(msg == '도움') {
			websocket.send(bot + "=> "+ '무엇을 도와드릴까요?');
			websocket.send('1. 이용안내 2. 박민수 3. 유건창');
			if(msg == '1') {
				websocket.send('안녕');
			}
		}else if (msg =='민수') {
			websocket.send(bot + "=>" + '민수바보');
		}
		 $('#message').val(""); //입력한 메시지 지우기
	}
	function connect() {
		websocket = new WebSocket("ws://localhost:8181/ch11/chat-ws.do");
		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onclose=onClose;
	}
	function onOpen() {
		var nickname= $('#nickname').val(); //별명 읽어서 nickname에 저장
		appendMessage(nickname+ "님이 입장하셨습니다.");
		appendMessage(bot +"님이 입장하셨습니다.")
	}
	function onMessage(event) {
		var msg = event.data; // 메세지는 event의 데이터로 전달 받는다
		appendMessage(msg);
	}
	function onClose() {
		var nickname= $('#nickname').val(); //별명 읽어서 nickname에 저장
		appendMessage(nickname+ "님이 퇴장하셨습니다.");
	}
	function appendMessage(msg) {
		$('#chatMessage').append(msg+"<br>");
		var objDiv = document.getElementById("chatMessage");
		objDiv.scrollTop = objDiv.scrollHeight;
	}
	function disconnect() {
		websocket.close();
	}
</script>
</head>
<body>
	<div class="container" align="center">
	<h2 class="text-primary">AI챗봇</h2>	
		<table class="table table-hover">
			<tr><td width="20%">별명</td><td><input type="text" id="nickname">
				<input type="button" id="enterBtn" value="입장" class="btn btn-sm btn-info">
				<input type="button" id="exitBtn" value="퇴장" class="btn btn-sm btn-warning"></td></tr>
				<tr><td>메세지</td><td><input type="text" id="message">
				<input type="button" id="sendBtn" value="전송" class="btn btn-success btn-sm"></td></tr>
				<tr><td>대화영역</td><td><div id="chatMessage"></div></td></tr>
		</table>
	</div>
</body>
</html>