const API_URL = "http://localhost:9091/api/";

$(document).ready(function () {
    loadProducts();

    $("#computeBtn").click(function () {
        const productId = $("#product").val();
        const quantity = $("#quantity").val();
        if (!productId) {
            alert("Kindly select product");
            return false;
        }
        if (quantity <= 0) {
            alert("Quantity should not be 0 or negative value");
            return false;
        }

        computePrice({ "productId": Number.parseInt(productId), "quantity": Number.parseInt(quantity) });
    });
});

function computePrice(data) {
    $.ajax({
        url: API_URL + "price/compute",
        type: "POST",
        //contentType: "application/json",
        data: data,
        beforeSend: function () {
            showLoader();
        },
        success: function (data) {
			$("#computeResultContainer").removeClass("d-none");
			$("#computeResults").empty();
			let content='';
			for(let key in data){
				content+= `<p>${(key + " : " +data[key])}</p>`;
			}
	        $("#computeResults").html(content);
        },
        complete: function () {
            hideLoader();
        },
    });
}

function loadProducts() {
    $.ajax({
        url: API_URL + "products/findall",
        type: "GET",
        contentType: "application/json",
        beforeSend: function () {
            showLoader();
        },
        success: function (data) {
            $("#product").empty();
            $("#product").append(`<option value="" selected>--Select--</option>`);
            data.forEach((item) => {
                $("#product").append(`
				            <option value="${item.id}">${item.name}</option>
				        `);
            });
        },
        complete: function () {
            hideLoader();
        },
    });
}
