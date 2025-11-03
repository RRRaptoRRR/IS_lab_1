const rowsPerPage = 2;
let currentPage = 1;

function showPage(page) {
    let allRows = document.querySelectorAll("table tbody tr");
    let visibleRows = Array.from(allRows).filter(r => !r.classList.contains("filtered-out"));

    let totalPages = Math.max(1, Math.ceil(visibleRows.length / rowsPerPage));
    currentPage = Math.min(page, totalPages);

    allRows.forEach(r => r.classList.add("hidden-page"));

    visibleRows.forEach((row, index) => {
        if (index >= (currentPage - 1) * rowsPerPage && index < currentPage * rowsPerPage) {
            row.classList.remove("hidden-page");
        }
    });

    updatePagination(totalPages);
}

function updatePagination(totalPages) {
    const container = document.getElementById("pagination");
    container.innerHTML = "";

    // <-
    const prevBtn = document.createElement("button");
    prevBtn.textContent = "←";
    prevBtn.disabled = (currentPage === 1);
    prevBtn.onclick = () => showPage(currentPage - 1);
    container.appendChild(prevBtn);

    // Номера страниц
    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement("button");
        btn.textContent = i;
        btn.classList.toggle("active", i === currentPage);
        btn.onclick = () => showPage(i);
        container.appendChild(btn);
    }

    // ->
    const nextBtn = document.createElement("button");
    nextBtn.textContent = "→";
    nextBtn.disabled = (currentPage === totalPages);
    nextBtn.onclick = () => showPage(currentPage + 1);
    container.appendChild(nextBtn);
}


function afterFilterUpdate() {
    showPage(1);
}

document.addEventListener("DOMContentLoaded", () => showPage(1));
