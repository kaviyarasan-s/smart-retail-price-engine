const API_URL = "http://localhost:9091/api/products/";

$(document).ready(function () {
    loadProducts();

    $("#createBtn").click(function () {
        clearForm();
        $("#formTitle").text("Add Product");
        openForm();
    });

    $("#closeBtn").click(closeForm);

    $("#saveBtn").click(function () {
        const productInfo = {
            name: $("#name").val(),
            category: $("#category").val(),
            brand: $("#brand").val(),
            stockQuantity: $("#stockquantity").val(),
            basePrice: $("#baseprice").val(),
        };

        const id = $("#productId").val();
        if (doesValidProductInfo(productInfo)) {
            if (id) {
                updateProduct(id, productInfo);
            } else {
                createProduct(productInfo);
            }
        }
    });
});

function doesValidProductInfo(productInfo) {
    if (!productInfo.name) {
        alert("Name should not be empty");
        return false;
    }

    if (!productInfo.category) {
        alert("Category should not be empty");
        return false;
    }

    if (!productInfo.brand) {
        alert("Brand should not be empty");
        return false;
    }

    if (!productInfo.stockQuantity || productInfo.stockQuantity < 0) {
        alert("Stock Quantity should not be empty or a negative value");
        return false;
    }

    if (!productInfo.basePrice || productInfo.basePrice < 0) {
        alert("Base price should not be empty or a negative value");
        return false;
    }
    return true;
}

function loadProducts() {
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
				                <td>${item.category}</td>
								<td>${item.brand}</td>
								<td>${item.stockQuantity}</td>
								<td>${item.basePrice}</td>
								<td>${item.createdOn}</td>
								<td>${item.modifiedOn}</td>
				                <td>
				                    <button class="btn btn-sm btn-warning me-1" onclick="editProduct(${item.id})">Edit</button>
				                    <button class="btn btn-sm btn-danger" onclick="deleteProduct(${item.id})">Delete</button>
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

function createProduct(item) {
    $.ajax({
        url: API_URL + "add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        beforeSend: function () {
            showLoader();
        },
        success: function () {
            closeForm();
            loadProducts();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function updateProduct(id, item) {
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
            loadProducts();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function editProduct(id) {
    $.ajax({
        url: API_URL + "find/" + id,
        type: "GET",
        contentType: "application/json",
        beforeSend: function () {
            showLoader();
        },
        success: function (item) {
            $("#productId").val(item.id);
            $("#name").val(item.name);
            $("#category").val(item.category);
            $("#brand").val(item.brand);
            $("#stockquantity").val(item.stockQuantity);
            $("#baseprice").val(item.basePrice);
            $("#formTitle").text("Edit Product");
            openForm();
        },
        complete: function () {
            hideLoader();
        },
    });
}

function deleteProduct(id) {
    if (confirm("Are you sure want to delete?")) {
        $.ajax({
            url: API_URL + "delete/" + id,
            type: "DELETE",
            beforeSend: function () {
                showLoader();
            },
            success: loadProducts,
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
    $("#productId").val("");
    $("#name").val("");
    $("#category").val("");
    $("#brand").val("");
    $("#stockquantity").val(0);
    $("#baseprice").val(0);
}