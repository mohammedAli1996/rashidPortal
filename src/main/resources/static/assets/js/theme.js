
$(document).ready(function () {
	// fetch('/views/nav')
	// .then(response => response.text())
	// .then(data => {
	// 	document.getElementById('header-placeholder').innerHTML = data;
		
	// })
	// .catch(error => console.error('Error loading header:', error));
	initTheme();
	// changeLanguage();
});



function initTheme(){
	const themeToggleBtn = document.getElementById('theme-toggle-btn');
	const themeIcon = document.getElementById('theme-icon');

	const darkModeStyles = {
		bootstrap: '/assets/css/bootstrap-dark-rtl.min.css',
		app: '/assets/css/app-dark-rtl.min.css'
	};

	const lightModeStyles = {
		bootstrap: '/assets/css/bootstrap-rtl.min.css',
		app: '/assets/css/app-rtl.min.css'
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


//with rtl ltr
// function initTheme() {
//     const themeToggleBtn = document.getElementById('theme-toggle-btn');
//     const themeIcon = document.getElementById('theme-icon');

//     // Define styles for different combinations of RTL/LTR and dark/light mode
//     const styles = {
//         rtl: {
//             dark: {
//                 bootstrap: '/assets/css/bootstrap-dark-rtl.min.css',
//                 app: '/assets/css/app-dark-rtl.min.css'
//             },
//             light: {
//                 bootstrap: '/assets/css/bootstrap-rtl.min.css',
//                 app: '/assets/css/app-rtl.min.css'
//             }
//         },
//         ltr: {
//             dark: {
//                 bootstrap: '/assets/css/bootstrap-dark.min.css',
//                 app: '/assets/css/app-dark.min.css'
//             },
//             light: {
//                 bootstrap: '/assets/css/bootstrap.min.css',
//                 app: '/assets/css/app.min.css'
//             }
//         }
//     };

//     themeToggleBtn.addEventListener('click', function () {
//         const isDarkMode = themeIcon.classList.contains('ri-moon-line');
//         const isRtl = document.documentElement.getAttribute('dir') === 'rtl';

//         let selectedStyles;
//         if (isRtl) {
//             selectedStyles = isDarkMode ? styles.rtl.dark : styles.rtl.light;
//         } else {
//             selectedStyles = isDarkMode ? styles.ltr.dark : styles.ltr.light;
//         }

//         document.getElementById('bootstrap-style').setAttribute('href', selectedStyles.bootstrap);
//         document.getElementById('app-style').setAttribute('href', selectedStyles.app);

//         // Toggle the icon and mode
//         if (isDarkMode) {
//             themeIcon.classList.remove('ri-moon-line');
//             themeIcon.classList.add('ri-sun-line');
//         } else {
//             themeIcon.classList.remove('ri-sun-line');
//             themeIcon.classList.add('ri-moon-line');
//         }
//     });

//     initNavComponenets(); // Initialize other components if needed
// }


// function changeLanguage() {
//     const langToggleBtn = document.getElementById('lang-toggle-btn');

//     // Define styles for different combinations of RTL/LTR and dark/light mode
//     const styles = {
//         rtl: {
//             dark: {
//                 bootstrap: '/assets/css/bootstrap-dark-rtl.min.css',
//                 app: '/assets/css/app-dark-rtl.min.css'
//             },
//             light: {
//                 bootstrap: '/assets/css/bootstrap-rtl.min.css',
//                 app: '/assets/css/app-rtl.min.css'
//             }
//         },
//         ltr: {
//             dark: {
//                 bootstrap: '/assets/css/bootstrap-dark.min.css',
//                 app: '/assets/css/app-dark.min.css'
//             },
//             light: {
//                 bootstrap: '/assets/css/bootstrap.min.css',
//                 app: '/assets/css/app.min.css'
//             }
//         }
//     };

//     langToggleBtn.addEventListener('click', function () {
//         const isRtl = document.documentElement.getAttribute('dir') === 'rtl';
//         const isDarkMode = document.body.getAttribute('data-sidebar') === 'dark'; // Check if dark mode is enabled by checking data-sidebar attribute

//         if (isRtl) {
//             // Switch to LTR (Left-to-Right)
// 			document.getElementById('translateText').innerHTML = 'عربي'

//             document.documentElement.setAttribute('dir', 'ltr');
//             if (isDarkMode) {
//                 document.getElementById('bootstrap-style').setAttribute('href', styles.ltr.dark.bootstrap);
//                 document.getElementById('app-style').setAttribute('href', styles.ltr.dark.app);
//             } else {
//                 document.getElementById('bootstrap-style').setAttribute('href', styles.ltr.light.bootstrap);
//                 document.getElementById('app-style').setAttribute('href', styles.ltr.light.app);
//             }
//         } else {
//             // Switch to RTL (Right-to-Left)
// 			document.getElementById('translateText').innerHTML = 'EN'

//             document.documentElement.setAttribute('dir', 'rtl');
//             if (isDarkMode) {
//                 document.getElementById('bootstrap-style').setAttribute('href', styles.rtl.dark.bootstrap);
//                 document.getElementById('app-style').setAttribute('href', styles.rtl.dark.app);
//             } else {
//                 document.getElementById('bootstrap-style').setAttribute('href', styles.rtl.light.bootstrap);
//                 document.getElementById('app-style').setAttribute('href', styles.rtl.light.app);
//             }
//         }
//     });

//     initNavComponenets(); // Initialize other components if needed
// }



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
