const rowsPerPage = 2; // Сколько групп показывать на странице
let currentPage = 1;

function showPage(page) {
    const table = document.querySelector("table tbody");
    const rows = table.querySelectorAll("tr");
    const totalPages = Math.ceil(rows.length / rowsPerPage);

    currentPage = page;

    rows.forEach((row, index) => {
        row.style.display = (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage)
            ? "" : "none";
    });

    updatePagination(totalPages);
}

function updatePagination(totalPages) {
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    // Кнопка "Назад"
    if (currentPage > 1) {
        pagination.innerHTML += `<button onclick="showPage(${currentPage - 1})">&lt;</button>`;
    }

    // Номера страниц
    for (let i = 1; i <= totalPages; i++) {
        pagination.innerHTML += `<button onclick="showPage(${i})" 
            style="margin-left:5px; ${i === currentPage ? 'font-weight:bold;' : ''}">
            ${i}
        </button>`;
    }

    // Кнопка "Вперед"
    if (currentPage < totalPages) {
        pagination.innerHTML += `<button onclick="showPage(${currentPage + 1})" style="margin-left:5px;">&gt;</button>`;
    }
}

// Запускаем пагинацию при загрузке страницы
window.onload = function() {
    showPage(1);
};