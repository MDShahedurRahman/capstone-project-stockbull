/* index.js */

/* Lower Header Buttons & Dropdown Menu*/
const investDropdownToggle = document.getElementById('investDropdown');
const investDropdownMenu = document.querySelector('.dropdown-menu');
const bankDropdownToggle = document.getElementById('bankDropdown');
const bankDropdownMenu = document.querySelector('.dropdown-menu[aria-labelledby="bankDropdown"]');
const learnDropdownToggle = document.getElementById('learnDropdown');
const learnDropdownMenu = document.querySelector('.dropdown-menu[aria-labelledby="learnDropdown"]');
const featuredDropdownToggle = document.getElementById('featuredDropdown');
const featuredDropdownMenu = document.querySelector('.dropdown-menu[aria-labelledby="featuredDropdown"]');

investDropdownToggle.addEventListener('click', function () {
  investDropdownMenu.classList.toggle('show');
});

bankDropdownToggle.addEventListener('click', function () {
  bankDropdownMenu.classList.toggle('show');
});

learnDropdownToggle.addEventListener('click', function () {
  learnDropdownMenu.classList.toggle('show');
});

featuredDropdownToggle.addEventListener('click', function () {
  featuredDropdownMenu.classList.toggle('show');
});

window.addEventListener('click', function (event) {
  if (!investDropdownToggle.contains(event.target) && !investDropdownMenu.contains(event.target)) {
    investDropdownMenu.classList.remove('show');
  }
  if (!bankDropdownToggle.contains(event.target) && !bankDropdownMenu.contains(event.target)) {
    bankDropdownMenu.classList.remove('show');
  }
  if (!learnDropdownToggle.contains(event.target) && !learnDropdownMenu.contains(event.target)) {
    learnDropdownMenu.classList.remove('show');
  }
  if (!featuredDropdownToggle.contains(event.target) && !featuredDropdownMenu.contains(event.target)) {
    featuredDropdownMenu.classList.remove('show');
  }
});
