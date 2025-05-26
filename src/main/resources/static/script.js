document.addEventListener("DOMContentLoaded", () => {
    const generateBtn = document.getElementById("generateBtn");
    const passwordOutput = document.getElementById("passwordOutput");
    const copyBtn = document.querySelector(".copy-btn");

    generateBtn.addEventListener("click", () => {
        let upperCase = document.getElementById("upperCase").checked;
        let numbers = document.getElementById("numbers").checked;
        let symbols = document.getElementById("symbols").checked;
        let length = parseInt(document.getElementById("length").value, 10);

        let config = {
            upperCase,
            numbers,
            symbols,
            length
        };

        fetch("/api/generate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(config)
        })
        .then(res => {
            if (!res.ok) throw new Error("Error al generar contraseña");
            return res.text();
        })
        .then(password => {
            passwordOutput.textContent = password;
        })
        .catch(err => {
            console.error(err);
            passwordOutput.textContent = "Error al generar contraseña.";
        });
    });

    copyBtn.addEventListener("click", () => {
        const password = passwordOutput.textContent;
        navigator.clipboard.writeText(password).then(() => {
            copyBtn.setAttribute("title", "¡Copiado!");
            new bootstrap.Tooltip(copyBtn).show();
            setTimeout(() => {
                copyBtn.setAttribute("title", "Copiar al portapapeles");
            }, 1500);
        });
    });
});
