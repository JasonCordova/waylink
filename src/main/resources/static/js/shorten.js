const inputElement = document.getElementById("shorten-input");
const flag = document.getElementById("flag-text");
const valid = document.getElementById("valid");
const invalid = document.getElementById("invalid");
const button = document.getElementById("shorten-button");

inputElement.oninput = () => {

    const urlPattern = /^(https?:\/\/)?(www\.)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}(\/[^\s]*)?$/;

    if (inputElement.value === ""){
        valid.classList.add("display-none");
        invalid.classList.add("display-none");
        button.disabled = true;
        flag.innerHTML = `&nbsp;`
    } else if (urlPattern.test(inputElement.value)) {
        flag.innerText = "The URL you've entered is valid.";
        valid.classList.remove("display-none");
        invalid.classList.add("display-none");
        button.disabled = false;
    } else {
        flag.innerText = "The URL you've entered is invalid.";
        valid.classList.add("display-none");
        invalid.classList.remove("display-none");
        button.disabled = true;
    }
};