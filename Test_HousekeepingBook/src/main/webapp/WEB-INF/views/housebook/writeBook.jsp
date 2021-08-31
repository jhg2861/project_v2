<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부 작성</title>
<script src="resources/js/jquery-3.4.1.js"></script>

<script>
function formCheck() {
	let memo = document.getElementById("memo");
	let price  = document.getElementById("price");
	
	if(memo.value.trim().length == 0) {
		alert('메모를 입력해 주세요');
		return false;
	}
	if(price.value.trim().length == 0) {
		alert('금액을 입력해 주세요');
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
<div class="wrapper">
<h2>[ 가계부 입력 ]</h2>
	<div class="home">
		<form action="writebook" method="POST">
			<input type="hidden" name="acc_id" value="${sessionScope.loginId}">
		
			<p>
				메모<input type="text" name="moneybook_memo" id="memo">
			</p>
			<p>
				분류 
				<select name="moneybook_type">
					<option value="수입"  ${searchItem=='income' ? 'selected' : ''}>수입</option>
					<option value="지출" ${searchItem=='spend'? 'selected' : ''}>지출</option>
				</select>
			</p>
			<p>
				금액
				<input type="text" name="moneybook_amount" id="price">
			</p>
			<input type ="submit"  value="입력하기" onclick="return formCheck();">
		</form>
		
	</div>
</div>
</body>
</html>