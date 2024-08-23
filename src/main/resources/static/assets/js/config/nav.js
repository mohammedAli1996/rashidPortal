document.addEventListener('DOMContentLoaded', function() {
    // Function to fetch and insert the navigation HTML
    function loadNav() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/views/nav', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('nav-container').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    // Call the function to load the navigation
    loadNav();
});
