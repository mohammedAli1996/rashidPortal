window.onload = function() {
    fetchPackages();
};

// document.getElementById('packageForm').addEventListener('submit', function(event) {
//     event.preventDefault();
//     const formtitle = document.getElementById('formtitle').value;
//     const formDescription = document.getElementById('formDescription').value;

//     const formPackage = {
//         formtitle: formtitle,
//         formDescription: formDescription
//     };

//     fetch('/api/subscription-form', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(formPackage),
//     })
//     .then(response => response.json())
//     .then(data => {
//         addPackageToTable(data);
//         document.getElementById('packageForm').reset();
//     })
//     .catch(error => console.error('Error:', error));
// });

function fetchPackages() {
    fetch('/api/subscription-form')
        .then(response => response.json())
        .then(data => {
            data.forEach(package => addPackageToTable(package));
        })
        .catch(error => console.error('Error:', error));
}

function addPackageToTable(package) {
    const tableBody = document.getElementById('packagesTableBody');
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${package.formtitle}</td>
        <td>${package.formDescription}</td>
        <td><button class="btn btn-primary" onclick="viewForm('${package.id}')">عرض</button></td>
    `;
    tableBody.appendChild(row);
}

function viewForm(id) {
    window.location.href = "/adminstration/viewForm?id="+id;
}