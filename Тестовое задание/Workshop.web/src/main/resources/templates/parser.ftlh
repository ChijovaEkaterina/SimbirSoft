<!DOCTYPE html>
<html>
<head>
    <title>Парсинг сайта</title>
</head>
<body>
<h1>Парсинг сайта</h1>
<form action="/URL/get" method="post">
    <big>Введите арес web-страницы: <input type="text" name="link"/><br><br> </big>
    <input type="submit" value="Ввести"/>
    <div>
        <#list parser as product>
            <div>
                <p><b>${product.word}</b> ${product.quantity} </p>
            </div>
        </#list>
    </div>
</form>
</body>
</html>
