<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div>
		<label>Сумма цифрой:</label>
		<div>
			<input type="text" name="number" value="" id="number" />
		</div>
	</div>
	<div>
		<label>Сумма прописью:</label>
		<div>
			<span id="text" style="color: green" lang="uk">Пусто...</span>
		</div>
	</div>
	<script type="text/javascript">
		function delay(callback, ms) {
			var timer = 0;
			return function() {
				var context = this, args = arguments;
				clearTimeout(timer);
				timer = setTimeout(function() {
					callback.apply(context, args);
				}, ms || 0);
			};
		}

		$('#number').keyup(delay(function(e) {
			$.ajax({
				type : 'POST',
				url : './MainPage.n',
				data : 'number=' + this.value,
				success : function(data) {
					var sumByFigure = document.getElementById('text');
					sumByFigure.innerHTML = data;
				}
			});
		}, 500));
	</script>
</body>
</html>
