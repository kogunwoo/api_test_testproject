<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>savings</title>
</head>
<body>
<h2>Financial Supervisory Service API Test</h2>
<p>API 결과는 아래에 표시됩니다.</p>
<div>
    <pre id="apiResult"></pre>
</div>
<script>
    // AJAX로 API 호출
    fetch("/")
        .then(response => response.text())
        .then(data => document.getElementById('apiResult').innerText = data);
</script>
</body>
</html>
