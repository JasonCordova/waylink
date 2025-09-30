const redirectLabel = document.getElementById("redirect-label");
const redirectUrl = document.getElementById("redirect-url");
const urlId = redirectUrl.dataset.urlId;
const redirectUrlText = redirectUrl.dataset.redirect;

const token = document.querySelector('meta[name="_csrf"]').content;
const header = document.querySelector('meta[name="_csrf_header"]').content;

function getDeviceType() {
    const ua = navigator.userAgent;
    if (/mobile/i.test(ua)) return "Mobile";
    if (/tablet/i.test(ua)) return "Tablet";
    return "Desktop";
}

function getOS() {
    const ua = navigator.userAgent;
    if (ua.indexOf("Win") !== -1) return "Windows";
    if (ua.indexOf("Mac") !== -1) return "MacOS";
    if (ua.indexOf("Linux") !== -1) return "Linux";
    if (/Android/i.test(ua)) return "Android";
    if (/iPhone|iPad|iPod/i.test(ua)) return "iOS";
    return "Unknown";
}

async function handleTimer(){

    const record = {
        urlId: urlId,
        timeZone: Intl.DateTimeFormat().resolvedOptions().timeZone,
        clickedAt: new Date().toISOString(),
        device: getDeviceType(),
        os: getOS()
    }

    try {

        const response = await fetch("/test", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [header]: token  // include CSRF token header dynamically
            },
            body: JSON.stringify(record)
        });

        if (!response.ok) {
            console.error("Failed to record click:", response.status);
        }
    } catch (error) {
        console.error("Error sending click data:", error);
    }
    
    window.location.href = redirectUrlText;

}

handleTimer();