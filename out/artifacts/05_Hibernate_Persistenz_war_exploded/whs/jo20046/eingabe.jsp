<html>
<head>
    <script type='text/javascript'>
        function addFields() {
            // Number of inputs to create (max. 10)
            const number = Math.min(10, document.getElementById("sources").value);
            // Container <div> where dynamic content will be placed
            const container = document.getElementById("container");
            // Clear previous contents of the container
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            for (let i = 0; i < number; i++) {
                // Append a node with text
                container.appendChild(document.createTextNode("Quelle " + (i + 1) + ": "));
                // Create an <input> element, set its type and name attributes
                const input = document.createElement("input");
                input.type = "text";
                input.name = "url" + i;
                container.appendChild(input);
                container.appendChild(document.createElement("br"));
            }
        }
    </script>
    <title>Eingabe</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/check">
    <input type="submit" value="Best&auml;tigen"><br><br>
    <label for="sources">Anzahl Quellen: (max. 10) </label>
    <input type="text" id="sources" name="sources" value=""><br><br>
    <a href="#" id="filldetails" onclick="addFields()">Quellen eingeben:</a>
    <div id="container"></div>
</form>
</body>
</html>