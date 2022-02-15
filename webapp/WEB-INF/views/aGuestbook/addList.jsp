<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>



</head>

<body>
	<div id="wrap">

		<!-- header.jsp -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<%-- <form action="${pageContext.request.contextPath}/guestbook/write" method="get"> --%>
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->


					</form>
					
					<div id= "listArea">
						<!-- 리스트 출력할 곳 -->
					
					</div>




				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>


	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">

//로딩되기 전에 요청 
	$(document).ready(function(){
		
		//리스트 그리기 
		fetchList();
		
	});
	
//등록 버튼이 클릭될 때 
	$("#btnSubmit").on("click", function(){
		console.log("클릭");
		
		//폼에 데이터를 모아야한다 
		var name = $("#input-uname").val(); //이름 
		var password = $("#input-pass").val();	//패스워드 
		var content = $("[name='content']").val(); //컨텐츠 
		
		//객체 만들기 
		var guestbookVo = {
			
			name: name,   //굳이 오른쪽 변수 name을 안만들고 $("#input-uname").var(), 이렇게 작성해도 됨. 이렇게 하면 폼에 데이터를 모아야한다 를 안해도 됨.
			password: password,
			content: content 
				
		};
		//확인 
		console.log(guestbookVo);
		
	
		//요청 
		$.ajax({   // 이 안에 있는 것들 전부 객체임. key:value 으로 이뤄진.  
		      
			
			//요청 
		      url : "${pageContext.request.contextPath}/api/guestbook/write",      
		      type : "post",
		      //contentType : "application/json",
		      data : guestbookVo,   //url 기본 주소 물음표 뒤에 파라미터로 변환하여 넣어줌  {key:value} 형식으로. 
		      						//객체 만들기에 넣어놨으니 guestbookVo 을 가져와도 된다.
		      						//{name: name, password: password, content: content} 원래는 이렇게 쓰면 됨. 
		      						// 보내는 기술은 기존 방식이랑 같음 (파라미터로 가는 거. json으로 가는 거 아님)

		    //응답 
		      dataType : "json",
		      success : function(result){
		        
		    	  /*성공시 처리해야될 코드 작성*/
		         console.log(guestbookList);
		         //console.log(guestbookList[0].name);
		         
		         for(var i=0; i<guestbookList.length; i++ ){
		        	 render(guestbookList[i]); //방명록 리스트 그리기 
		         }
		        
		         
		         
		      },
		      error : function(XHR, status, error) {
		         console.error(status + " : " + error);
		      }
		   });
			
	});
	
	
//리스트 출력 
	function fetchList(){
		$.ajax({
		      
		      url : "${pageContext.request.contextPath}/api/guestbook/list",      
		      type : "post",
		      //contentType : "application/json",
		      //data : {name: "홍길동"},

		      dataType : "json",
		      success : function(guestbookList){
		        
		    	  /*성공시 처리해야될 코드 작성*/
		         console.log(guestbookList);
		         //console.log(guestbookList[0].name);
		         
		         //방명록 리스트 그리기 
		         //데이터 개수 만큼 돌아가도록 For문 
		         for(var i=0; i<guestbookList.length; i++ ){
		        	 render(guestbookList[i]); //이게 vo 모양이 됨 
		        	 
		         }
		        
		         
		         
		      },
		      error : function(XHR, status, error) {
		         console.error(status + " : " + error);
		      }
		   });
		
	}
	
//리스트 그리기 
	function render(guestbookVo){
		
		var str = '';
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+guestbookVo.no+'</td>';
		str += '		<td>'+guestbookVo.name+'</td>';
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><a href="${pageContext.request.contextPath }/guestbook/deleteForm?no=${guestVo.no}">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';
		str += '</table>';
		
		$("#listArea").append(str);
		
		
	};

	/*
	<table class="guestRead">
	<colgroup>
		<col style="width: 10%;">
		<col style="width: 40%;">
		<col style="width: 40%;">
		<col style="width: 10%;">
	</colgroup>
	<tr>
		<td>11</td>
		<td>황인경</td>
		<td>2022-01-28</td>
		<td><a href="${pageContext.request.contextPath }/guestbook/deleteForm?no=${guestVo.no}">[삭제]</a></td>
	</tr>
	<tr>
		<td colspan=4 class="text-left">방문했습니다.</td>
	</tr>
	</table>
	*/
	

</script>






</html>


