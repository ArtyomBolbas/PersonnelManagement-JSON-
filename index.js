function loadJSON() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            var jsonObj = JSON.parse(xhr.responseText);
            var body = '';
		    for (var employee of jsonObj.listEmployees) {
				body += "<tr><td>" + employee.name 
                    + "</td><td>" + employee.surname + "</td>"
                + "<td>" + employee.email + "</td></tr>";
			}
            document.getElementById('tbody').innerHTML = body;
        }
    }

    xhr.open("GET", "info.json", true);
    xhr.send();
}