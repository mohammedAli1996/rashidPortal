window.onload = function () {
    fetchPackages();
};



function fetchPackages() {
    $.ajax({
        type: 'GET',
        url: '/api/subscription-form',
        success: function (response) {
            var cnt = `<table id="datatable" class="table table-bordered dt-responsive nowrap"
                                        style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                        <thead>
                                            <tr>
                                                <th>العنوان</th>
                                                <th>التوصيف</th>
                                                <th>العمليات</th>
                                            </tr>
                                        </thead>
                                        <tbody>`;

            response.forEach(package => {
                cnt += addPackageToTable(package);
            });


            cnt += `  </tbody>
                                    </table>`;
            document.getElementById("packagesTableBody").innerHTML = cnt;

            $('#datatable').DataTable({
                "language": {
                    "paginate": {
                        "previous": "<i class='mdi mdi-chevron-left'>",
                        "next": "<i class='mdi mdi-chevron-right'>"
                    }
                },
                "drawCallback": function () {
                    $('.dataTables_paginate > .pagination').addClass('pagination-rounded');
                }
            });

        },
        error: function (error) {
            console.error("Error subscriptions")
        }
    });
}

function addPackageToTable(package) {
    return `
    <tr>
        <td>${package.formtitle}</td>
        <td>${package.formDescription}</td>
        <td><button class="btn btn-primary" onclick="viewForm('${package.id}')">عرض</button></td>
        </tr>
    `;
}

function viewForm(id) {
    window.location.href = "/adminstration/viewForm?id=" + id;
}