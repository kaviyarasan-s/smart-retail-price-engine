var grid;
  var dataView;
  var data = [];

  // Sample data
  for (var i = 1; i <= 50; i++) {
    data.push({
      id: i,
      name: "Item " + i,
      category: i % 2 === 0 ? "Hardware" : "Software",
      price: (i * 10)
    });
  }

  var columns = [
    { id: "id", name: "ID", field: "id", width: 60 },
    { id: "name", name: "Name", field: "name" },
    { id: "category", name: "Category", field: "category" },
    { id: "price", name: "Price", field: "price" }
  ];

  var options = {
    enableCellNavigation: true,
    enableColumnReorder: false,
    forceFitColumns: true,
    rowHeight: 38
  };

  $(function () {
    dataView = new Slick.Data.DataView();
    grid = new Slick.Grid("#myGrid", dataView, columns, options);

    dataView.beginUpdate();
    dataView.setItems(data);
    dataView.endUpdate();

    // Search filter
    $("#searchBox").on("keyup", function () {
      var searchText = this.value.toLowerCase();

      dataView.setFilter(function (item) {
        return (
          item.name.toLowerCase().includes(searchText) ||
          item.category.toLowerCase().includes(searchText)
        );
      });

      dataView.refresh();
    });
  });