<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in to your PizzaOrderApp account</title>
    <style>
        body {
            margin: 0;
            overflow: hidden;
            display: flex;
            align-items: center; /* Центрирование по вертикали */
            justify-content: center; /* Центрирование по горизонтали */
            height: 100vh; /* 100% высоты окна браузера */
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
        }
        a {
            text-decoration: none;
        }
        input {
            font-family: 'Arial', sans-serif;
        }
    </style>
</head>
<body>
<img src="images/pizza.jpg" alt="Pizza Image">

<form action="login" method="post">
    <h4>Sign in</h4>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <input type="submit" value="Sign in">
    <p>No account? <a href="registration.jsp">Create one!</a></p>
</form>
</body>
</html>