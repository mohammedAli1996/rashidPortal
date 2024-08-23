
$(document).ready(function () {
	// fetch('/views/nav')
	// .then(response => response.text())
	// .then(data => {
	// 	document.getElementById('header-placeholder').innerHTML = data;
		
	// })
	// .catch(error => console.error('Error loading header:', error));
	initTheme();
});

function initTheme(){
	const themeToggleBtn = document.getElementById('theme-toggle-btn');
	const themeIcon = document.getElementById('theme-icon');

	const darkModeStyles = {
		bootstrap: '/assets/css/bootstrap-dark.min.css',
		app: '/assets/css/app-dark.min.css'
	};

	const lightModeStyles = {
		bootstrap: '/assets/css/bootstrap.min.css',
		app: '/assets/css/app.min.css'
	};


	themeToggleBtn.addEventListener('click', function () {
		const isDarkMode = themeIcon.classList.contains('ri-moon-line');
		if (isDarkMode) {
			document.getElementById('bootstrap-style').setAttribute('href', darkModeStyles.bootstrap);
			document.getElementById('app-style').setAttribute('href', darkModeStyles.app);
			themeIcon.classList.remove('ri-moon-line');
			themeIcon.classList.add('ri-sun-line');
		} else {
			document.getElementById('bootstrap-style').setAttribute('href', lightModeStyles.bootstrap);
			document.getElementById('app-style').setAttribute('href', lightModeStyles.app);
			themeIcon.classList.remove('ri-sun-line');
			themeIcon.classList.add('ri-moon-line');
		}
	});

	initNavComponenets();
}






document.addEventListener('DOMContentLoaded', function () {


	// Optionally, initialize the button based on current mode
	// if (document.getElementById('bootstrap-style').getAttribute('href') === darkModeStyles.bootstrap) {
	// 	themeIcon.classList.remove('ri-moon-line');
	// 	themeIcon.classList.add('ri-sun-line');
	// } else {
	// 	themeIcon.classList.remove('ri-sun-line');
	// 	themeIcon.classList.add('ri-moon-line');
	// }

});
