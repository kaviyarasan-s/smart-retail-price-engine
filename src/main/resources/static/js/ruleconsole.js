const API_URL = "http://localhost:9091/api/rules/";

$(document).ready(function () {
    loadRules();

    $("#createBtn").click(function () {
        clearForm();
        $("#formTitle").text("Add Rule");
        openForm();
    });

    $("#closeBtn").click(closeForm);

    $("#saveBtn").click(function () {
        const ruleInfo = {
            name: $("#name").val(),
            ruleType: $("#ruleType").val(),
            value: $("#value").val(),
            priority: $("#priority").val(),
            conditionJson: $("#condition").val(),
            isActive: $("#active").prop("checked")? "Y":"N",
        };

        const id = $("#ruleId").val();
        if (doesValidRuleInfo(ruleInfo)) {
            if (id) {
                updateRule(id, ruleInfo);
            } else {
                createRule(ruleInfo);
            }
        }
    });
});

function doesValidRuleInfo(ruleInfo) {
    if (!ruleInfo.name) {
        alert("Name should not be empty");
        return false;
    }

    if (!ruleInfo.ruleType) {
        alert("Rule Type should not be empty");
        return false;
    }

    if (!ruleInfo.value || ruleInfo.value < 0) {
        alert("Value should not be empty or a negative value");
        return false;
    }

    if (!ruleInfo.priority || ruleInfo.priority < 0) {
        alert("Priority should not be empty or a negative value");
        return false;
    }

    if (!ruleInfo.isActive) {
        alert("Active should not be empty");
        return false;
    }
    return true;
}

function loadRules() {
    $.ajax({
        url: API_URL + "findall",
        type: "GET",
        contentType: "application/json",
        beforeSend: function () {
            showLoader();
        },
        success: function (data) {
            $("#itemTable").empty();
            data.forEach((item) => {
                $("#itemTable").append(`
				            <tr>
				                <td>${item.name}</td>
				                <td>${item.ruleType}</td>
								<td>${item.value}</td>
								<td>${item.priority}</td>
								<td>${item.conditionJson}</td>
								<td>${item.isActive == "Y" ? "Yes" : "No"}</td>
				                <td>
				                    <button class="btn btn-sm btn-warning me-1" onclick="editRule(${item.id})">Edit</button>
				                    <button class="btn btn-sm btn-danger" onclick="deleteRule(${item.id})">Delete</button>
				                </td>
				            </tr>
				        `);
            });
        },
        complete: function () {
            hideLoader();
        },
    });
}

function createRule(item) {
    $.ajax({
        url: API_URL + "save",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        beforeSend: function () {
            showLoader();
        },
        success: function () {
            closeForm();
            loadRules();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function updateRule(id, item) {
    $.ajax({
        url: API_URL + "update/" + id,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(item),
        beforeSend: function () {
            showLoader();
        },
        success: function () {
            closeForm();
            loadRules();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function editRule(id) {
    $.ajax({
        url: API_URL + "find/" + id,
        type: "GET",
        contentType: "application/json",
        beforeSend: function () {
            showLoader();
        },
        success: function (item) {
            $("#ruleId").val(item.id);
            $("#name").val(item.name);
            $("#category").val(item.category);
            $("#brand").val(item.brand);
            $("#stockquantity").val(item.stockQuantity);
            $("#baseprice").val(item.basePrice);
            $("#formTitle").text("Edit Rule");
            openForm();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function deleteRule(id) {
    if (confirm("Are you sure want to delete?")) {
        $.ajax({
            url: API_URL + "delete/" + id,
            type: "DELETE",
            beforeSend: function () {
                showLoader();
            },
            success: loadRules,
            complete: function () {
                hideLoader();
            },
        });
    }
}

function openForm() {
    $("#itemFormPanel").addClass("active");
}

function closeForm() {
    $("#itemFormPanel").removeClass("active");
}

function clearForm() {
    $("#ruleId").val("");
    $("#ruleType").val("");
    $("#value").val(0);
    $("#priority").val(0);
    $("#condition").val("");
    $("#active").prop("checked", true);
}

function showLoader() {
    $(".loader-overlay").removeClass("d-none");
}

function hideLoader() {
    $(".loader-overlay").addClass("d-none");
}