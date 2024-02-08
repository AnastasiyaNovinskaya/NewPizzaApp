<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.PizzaType" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            text-align: center;
            margin-top: 50px;
            background-color: #f4f4f4;
        }
        input {
            font-family: 'Arial', sans-serif;
            font-size: 16px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        img {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<h2>Select Pizza</h2>
<form action="selectPizza" method="post">
    <table style="width: 80%; margin: 0 auto; border-collapse: collapse; margin-bottom: 20px;">
        <tr>
            <th style="width: 20%;">Pizza</th>
            <th style="width: 30%;">Ingredients</th>
            <th style="width: 10%;">Image</th>
            <th style="width: 10%;">Price</th>
            <th style="width: 30%;">Quantity</th>
        </tr>
        <%
            ArrayList<PizzaType> pizzas = (ArrayList<PizzaType>) session.getAttribute("pizzas");
            if (pizzas != null && !pizzas.isEmpty()) {
                for (PizzaType pizza : pizzas) {
        %>
        <tr>
            <td>
                <label>
                    <input type="checkbox" name="pizzaId" value="<%= pizza.getId() %>">
                    <b><%= pizza.getName() %></b>
                </label>
            <td><%= pizza.getDescription() %></td>
            <td><img src="<%= pizza.getImageUrl() %>" alt="<%= pizza.getName() %>"></td>
            <td><%= pizza.getPrice() + " €" %></td>
            <td>Quantity:
                <label>
                    <input type="number" name="pizzaQuantity<%= pizza.getId() %>" value="1" min="1">
                </label>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            No pizzas available.
        </tr>
        <%
            }
        %>
    </table>
    <p>To add something else to your order, please select "Continue"</p>
    <input type="submit" value="Continue">
</form>
</body>
</html>
