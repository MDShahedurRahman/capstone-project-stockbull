const registrationForm = document.querySelector("#signup-form");
const email = document.querySelector("#email");
const password = document.querySelector("#password");
const confirmPassword = document.querySelector("#confirm-password");

registrationForm.addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent form submission

  // Get form input values
  let emailInput = email.value;
  let passwordInput = password.value;
  let confirmPasswordInput = confirmPassword.value;

    // Email validation regular expression
    let emailRegex = /^[\w.-]+@[a-zA-Z_-]+?(?:\.[a-zA-Z]{2,6})+$/;

    // Check if the email is valid
    if (!emailRegex.test(emailInput)) {
        alert("Please enter a valid email address.");
        return;
    }

    // Check if the password and confirmed password match
    if (passwordInput !== confirmPasswordInput) {
        alert("Password and confirmed password do not match.");
        return;
    }

  // The localStorage read-only property of the window interface allowsto access a Storage object for the Document's origin; the stored data is saved across browser sessions.
  localStorage.setItem("email", emailInput);
  localStorage.setItem("password", passwordInput);

  // Redirect to the login page
  window.location.replace("login.html");
});