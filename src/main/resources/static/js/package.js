window.onload = function() {
    fetchPackages();
};

document.getElementById('packageForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const packageName = document.getElementById('packageName').value;
    const packageDescription = document.getElementById('packageDescription').value;
    const packagePrice = document.getElementById('packagePrice').value;

    const formPackage = {
        packageName: packageName,
        packageDescription: packageDescription,
        packagePrice: parseFloat(packagePrice)
    };

    fetch('/api/form-packages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formPackage),
    })
    .then(response => response.json())
    .then(data => {
        addPackageToTable(data);
        document.getElementById('packageForm').reset();
    })
    .catch(error => console.error('Error:', error));
});

function fetchPackages() {
    fetch('/api/form-packages')
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
        <td>${package.packageName}</td>
        <td>${package.packageDescription}</td>
        <td>${package.packagePrice.toFixed(2)}</td>
        <td><button class="btn btn-danger" onclick="deletePackage('${package.id}')">Delete</button></td>
    `;
    tableBody.appendChild(row);
}

function deletePackage(id) {
    fetch(`/api/form-packages/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (response.ok) {
            // Remove the row from the table
            const row = document.querySelector(`button[onclick="deletePackage('${id}')"]`).closest('tr');
            row.remove();
        }
    })
    .catch(error => console.error('Error:', error));
}