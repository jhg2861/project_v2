<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부 수정</title>

<script>
function formCheck() {
	let memo = document.getElementById("memo");
	let amount  = document.getElementById("amount");
	
	if(memo.value.trim().length == 0) {
		alert('메모를 입력해 주세요');
		return false;
	}
	if(amount.value.trim().length == 0) {
		alert('금액을 입력해 주세요');
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
<div class="wrapper">
<h2>[ 가계부 수정 ]</h2>
	<div class="home">
		<form action="updatebook" method="POST">
			<input type="hidden" name="acc_id" value="${sessionScope.loginId}">
			<input type="hidden" name="moneybook_no" value="${book.moneybook_no}">
		
			<p>
				메모<input type="text" name="moneybook_memo" id="memo" value="${book.moneybook_memo}">
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
				<input type="text" name="moneybook_amount" id="amount" value="${book.moneybook_amount}">
			</p>
			<input type ="submit"  value="수정" onclick="return formCheck();">
		</form>
		
	</div>
</div>
</body>
</html>