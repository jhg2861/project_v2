<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.4.1.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form[name='readForm']");
	$('.btDelete').on('click', deleteBook);
	$('.btUpdate').on('click', updateBook);
	// 수정 
	$(".update_btn").on("click", function(){
		formObj.attr("action", "/housebook/updateBook");
		formObj.attr("method", "get");
		formObj.submit();				
	})
	
	// 삭제
	$(".delete_btn").on("click", function(${Moneybook_tb.moneybook_no}){
		formObj.attr("action", "deletebook");
		formObj.attr("method", "post");
		formObj.submit();
	})
	
	
})

/**
 * 삭제 기능
 */
function deleteBook() {
   if (confirm('삭제하시겠습니까?')) {
      let a = $(this).attr('data-num')
      location.href = 'deletebook?moneybook_no=' + a;
   }
}

/*
 * 업데이트 기능
 */
function updateBook() {
		let a = $(this).attr('data-num')
		location.href = 'updatebook?moneybook_no=' + a;
	}

/**
 * 검색 기능
 */
 $.searchBook = function(){
	let searchWord = $("#searchWord").val();
	let searchItem = $("#searchItem").val();
	var formObj = $("form[name='search']");
	
	if(searchWord == '' || searchWord == null ){
		alert("검색어를 입력해주세요");
		return false;
	}else{
		formObj.attr("action", "listbook");
		formObj.submit();
	}
}
/*
 * 최솟값 최대값 구하기
 */
$.fnMinOrMax = function(mode){
	let searchItem = $("#searchItem").val();
	let searchWord = $("#searchWord").val();
	
	let myData = {"mode" : mode, "searchWord" : searchWord, "searchItem" : searchItem};
	
	$.ajax({
		url : 'schMinOrmax'
		, method 	: 'POST'
		, data		: myData
		, success 	: function(resp) { 
			let result = resp['result'];
			$("#minOrmax").val(result);
		}
			
	});
	
}
$.fnTotalCaculate = function(mode){
	let searchWord = $("#searchWord").val();
	let searchItem = $("#searchItem").val();
	let myData = {"mode" : mode, "searchWord" : searchWord, "searchItem" : searchItem};

	alert(searchWord);
	alert(searchItem);
	$.ajax({
		url : 'schTotalCaculate'
		, method 	: 'POST'
		, data		: myData
		, success 	: function(resp) { 
			let result = resp['result'];
			$("#totalCaculate").val(result);
		}
			
	});
	
}

$.schBookList = function(mode){
	var formObj = $("form[name='search']");
	let searchItem = $("#searchItem").val();
	formObj.attr("action", "listbook");
	formObj.submit();
}

</script>
</head>
<body>
	<div class="wrapper">
		<h2>[ 가계부 ]</h2>
		<div class="home">
			<form id="search" name="search" action="listbook" method="GET">
				<select name="searchItem" id ="searchItem" onchange="$.schBookList(this.value); return falset();">
					<option value="entitle"  ${searchItem=='entitle' ? 'selected' : ''}>전체</option>
					<option value="income" ${searchItem=='income'? 'selected' : ''}>수입</option>
					<option value="pay"   ${searchItem=='pay'	 ? 'selected' : ''}>지출</option>
				</select>
				<input type="text" name="searchWord" id="searchWord" value="${searchWord}">
				<input class="btn" type="button" onclick="$.searchBook(); return false;" value="검색">
			</form>
		</div>
		
		
		<!-- 게시글 목록 시작 -->
		<div>
			<form name="readForm" role="form" method="post">
				<table border="1">
					<tr>
						<th>번호</th>
						<th class="title">메모</th>			
						<th>종류</th>			
						<th>금액</th>			
						<th>작성일</th>
						<th></th>
						<th></th>
					</tr>
					<!-- 게시글 출력 -->
					<c:forEach var="Moneybook_tb" items="${list}">		
						<tr>
							<td>${Moneybook_tb.moneybook_no}</td>
							<td>${Moneybook_tb.moneybook_memo}</td>
							<td>${Moneybook_tb.moneybook_type}</td>
							<td>${Moneybook_tb.moneybook_amount}</td>
							<td>${Moneybook_tb.moneybook_indate}</td>
							<td>
								<input class="btUpdate" type="button" value="수정" data-num = "${Moneybook_tb.moneybook_no}">
							</td>
							<td>
								<input class="btDelete" type="button" value="삭제" data-num = "${Moneybook_tb.moneybook_no}">
							</td>
						</tr>
					</c:forEach>
				</table>
				<!--  글쓰기 버튼 -->
				<c:if test="${sessionScope.loginId != null}">
					<div class="write"><a href="writebook"><input type="button" name="write" value="가계부 작성"></a></div>
				</c:if>
			</form>
		</div>
		<div>
			<input type="button"  onclick="$.fnTotalCaculate('totalIncome');" value="총 수입 구하기"/>
			<input type="button"  onclick="$.fnTotalCaculate('totalPay');" value="총 지출 구하기"/>
			<input type="text" id="totalCaculate" value="" placeholder="총 수입 또는 지출">
		</div>
		<div>
			<input type="button" onclick="$.fnMinOrMax('min');" value="최소값 구하기">
			<input type="button" onclick="$.fnMinOrMax('max');" value="최대값 구하기">
			<input type="text" id="minOrmax" value="" placeholder="최소값, 최대값"/>
		</div>
	</div>
</body>
</html>