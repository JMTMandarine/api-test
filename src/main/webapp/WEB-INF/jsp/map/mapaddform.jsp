<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지도 좌표 얻기</title>
<link rel='stylesheet' href='/css/common.css'>
<style>
th {
    text-align: right;
}
</style>
</head>
<body>


<h1>지도 좌표를 얻어보자</h1>

<form action='add' method='post' enctype="multipart/form-data">
<table>
<tbody>
<tr>
    <th>이름</th>
    <td><input type='text' name='name'></td>
</tr>
<tr>
    <th>이메일</th>
    <td><input type='email' name='email'></td>
</tr>
<tr>
    <th>암호</th>
    <td><input type='password' name='password'></td>
</tr>
<tr>
    <th>전화</th>
    <td><input type="tel" name='tel'></td>
</tr>
<tr>
    <th>직위</th>
    <td><input type='text' name='position'></td>
</tr>
<tr>
    <th>사진</th>
    <td><input type='file' name='file1'></td>
</tr>
<tr>
    <th></th>
    <td><button>등록</button></td>
</tr>
</tbody>
</table>
</form>


</body>
</html>











    