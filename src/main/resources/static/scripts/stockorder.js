// Wait for the DOM to load
document.addEventListener('DOMContentLoaded', function() {
    // Get the form element
    let form = document.getElementById('order-form');
  
    // Add submit event listener to the form
    form.addEventListener('submit', function(event) {
      event.preventDefault(); // Prevent form submission
  
      // Get the input values
      let orderID = document.getElementById('order-id').value;
      let tradeOption = document.querySelector('input[name="trade"]:checked').value;
      let orderDate = document.getElementById('order-date').value;
      let stockName = document.getElementById('stock-name').value;
      let amountInvest = document.getElementById('amount-invest').value;
      let creditCard = document.getElementById('credit-card').value;
      let nameCard = document.getElementById('name-on-card').value;
      let billingAddress = document.getElementById('billing-address').value;

      // Name validation regular expression
      let nameRegex = /^[A-Za-z ]+$/;

    // Check if name is valid
    if (!nameRegex.test(nameCard)) {
        alert("Invalid Name on Card. Only letters and space!");
        return;
    }

    // Check if any field is empty
    if (
      orderID === '' ||
      orderDate === '' ||
      stockName === '' ||
      amountInvest === '' ||
      creditCard === '' ||
      nameCard === '' ||
      billingAddress === ''
    ) {
      // Show alert message
      alert('Please fill in all the required fields!');
    } else {
      // All fields are filled, proceed with form submission
  
      // Create an object to store the input values
      let stockData = {
        orderID: orderID,
        tradeOption: tradeOption,
        orderDate: orderDate,
        stockName: stockName,
        amountInvest: amountInvest,
        creditCard: creditCard,
        nameCard: nameCard,
        billingAddress: billingAddress
      };
  
      // Retrieve the existing stock data from local storage
      let existingData = JSON.parse(localStorage.getItem('stockDataList')) || [];
  
      // Add the new stock data to the existing data
      existingData.push(stockData);
  
      // Store the updated data in local storage
      localStorage.setItem('stockDataList', JSON.stringify(existingData));
  
      // Clear the form inputs
      form.reset();
  
      // Optional: Redirect to another page
      window.location.replace("transactions.html");
      }
    });
  });