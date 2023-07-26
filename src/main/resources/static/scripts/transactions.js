// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', function() {
  // Retrieve the table element
  let table = document.getElementById('transactionTable');

  // Retrieve the Clear All button element
  let clearButton = document.getElementById('clearButton');

  // Add click event listener to the Clear All button
  clearButton.addEventListener('click', function() {
    // Clear the table body by removing all child nodes
    while (table.tBodies[0].firstChild) {
      table.tBodies[0].removeChild(table.tBodies[0].firstChild);
    }

    // Clear the data from local storage
    localStorage.removeItem('stockDataList');

    // Clear the stockDataList variable
    let stockDataList = [];
  });

  // Retrieve the saved data from local storage
  let stockDataList = JSON.parse(localStorage.getItem('stockDataList')) || [];

  // Function to render the table rows
  function renderTableRows() {
    // Clear the table body
    table.tBodies[0].innerHTML = '';

    // Loop through each stock data
    stockDataList.forEach(function(stockData) {
      // Create a table row element
      let row = document.createElement('tr');

      // Create table data cells
      let orderIDCell = document.createElement('td');
      orderIDCell.textContent = stockData.orderID;
      let tradeOptionCell = document.createElement('td');
      tradeOptionCell.textContent = stockData.tradeOption;
      let orderDateCell = document.createElement('td');
      orderDateCell.textContent = stockData.orderDate;
      let stockNameCell = document.createElement('td');
      stockNameCell.textContent = stockData.stockName;
      let amountInvestCell = document.createElement('td');
      amountInvestCell.textContent = stockData.amountInvest;
      let creditCardCell = document.createElement('td');
      creditCardCell.textContent = stockData.creditCard;
      let nameCardCell = document.createElement('td');
      nameCardCell.textContent = stockData.nameCard;
      let billingAddressCell = document.createElement('td');
      billingAddressCell.textContent = stockData.billingAddress;

      // Append the cells to the row
      row.appendChild(orderIDCell);
      row.appendChild(tradeOptionCell);
      row.appendChild(orderDateCell);
      row.appendChild(stockNameCell);
      row.appendChild(amountInvestCell);
      row.appendChild(creditCardCell);
      row.appendChild(nameCardCell);
      row.appendChild(billingAddressCell);

      // Append the row to the table
      table.tBodies[0].appendChild(row);
    });
  }

  // Render the initial table rows
  renderTableRows();
});