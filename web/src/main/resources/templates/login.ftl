<!DOCTYPE html>

<html lang="en">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Login</title>
</head>

<body>
<h1>登录页面----</h1>
<img alt="" src="/static/images/liuyifei.jpg" width="15" height="20">
<form action="/login"
           commandName="user" method="post">
    用户名：
    <input name="username"/>
    <br/>
    密码：
    <input type="password" name="password"/>
    <br/>
    <button type="submit" name="button">提交</button>
</form>
</body>

</html>
