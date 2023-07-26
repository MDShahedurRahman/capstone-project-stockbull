const registrationForm = document.getElementById("login-form");
const emailLocal = localStorage.getItem("email");
const passwordLocal = localStorage.getItem("password");
const userData = new Map();
const errorMessage = document.getElementById("error-message"); // Reference to error message element
userData.set(emailLocal, passwordLocal);
// More email-password pairs can be added to the Map
userData.set("abc@gmail.com", "1234");

function logSubmit(event) {
  event.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  if (userData.has(email)) {
    const storedPassword = userData.get(email);
    if (password === storedPassword) {
      localStorage.setItem("logged", email);
      window.location.replace("index.html");
    } else {
      displayErrorMessage("Invalid password. Please try again.");
    }
  } else {
    displayErrorMessage("Invalid email. Please try again.");
  }
}

function displayErrorMessage(message) {
  errorMessage.textContent = message;
  errorMessage.style.color = "red";
}

registrationForm.addEventListener("submit", logSubmit);