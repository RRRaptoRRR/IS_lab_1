function filterById() {
    let filter = document.getElementById("searchId").value.toLowerCase();
    let rows = document.querySelectorAll("table tbody tr");

    rows.forEach(row => {
        let idCell = row.querySelector("td:nth-child(1)");

        if (!filter) {
            row.style.display = ""; // Показать все
            return;
        }

        let text = idCell.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? "" : "none";
    });

    // Очищаем поиск по имени, чтобы не пересекались фильтры
    document.getElementById("searchName").value = "";
}

function filterByName() {
    let filter = document.getElementById("searchName").value.toLowerCase();
    let rows = document.querySelectorAll("table tbody tr");

    rows.forEach(row => {
        let nameCell = row.querySelector("td:nth-child(2)");

        if (!filter) {
            row.style.display = ""; // Показать все
            return;
        }

        let text = nameCell.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? "" : "none";
    });

    // Очищаем поиск по ID, чтобы они не смешивались
    document.getElementById("searchId").value = "";
}