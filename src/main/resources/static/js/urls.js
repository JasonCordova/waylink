const allCopyButtons = document.querySelectorAll(".copy-url");
const flags = document.getElementById("flags");


allCopyButtons.forEach(element => {

    element.addEventListener("click", async () => {

        let shortUrl = element.dataset.shortUrl;

        const fullUrl = window.location.origin + shortUrl;

        try {
            await navigator.clipboard.writeText(fullUrl);

            const newFlag = document.createElement("div");
            newFlag.innerText = "Copied to clipboard";
            newFlag.classList.add("flag");
            flags.append(newFlag);

            setTimeout(() => {
                newFlag.classList.add("adding");
            }, 200);

            setTimeout(() => {
                newFlag.classList.remove("adding");
            }, 2000);

            setTimeout(() => {
                newFlag.remove();
            }, 2200);

        } catch (err) {
            console.error("Failed to copy:", err);
        }

    })

});