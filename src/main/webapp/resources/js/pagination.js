
// Глобальные переменные
window.currentPage = 1;

//Получает количество строк на страницу из селекта (если он есть) или возвращает 2
function getRowsPerPage() {
    let select = document.getElementById("rowsPerPageSelect");
    return select ? parseInt(select.value) : 2;
}

//
//Основная функция отображения
//1. Скрывает всё
//2. Выбирает только то, что не отфильтровано
//3. Показывает нужную страницу
function showPage(page) {
    let tbody = document.getElementById("musicTableBody");
    if (!tbody) return;

    let rowsPerPage = getRowsPerPage();
    let allRows = tbody.querySelectorAll("tr");

    // Сначала скрываем ВСЕ строки таблицы
    // Это сбрасывает любое предыдущее состояние отображения
    allRows.forEach(r => r.style.display = "none");

    //  Находим строки, которые прошли фильтр (у которых нет класса filtered-out)
    let visibleRows = Array.from(allRows).filter(r => !r.classList.contains("filtered-out"));

    //  Считаем количество страниц
    let totalPages = Math.max(1, Math.ceil(visibleRows.length / rowsPerPage));

    //  Проверяем границы страницы
    if (page > totalPages) page = totalPages;
    if (page < 1) page = 1;

    // Обновляем глобальную переменную
    window.currentPage = page;

    // Показываем только строки для текущей страницы
    let start = (window.currentPage - 1) * rowsPerPage;
    let end = start + rowsPerPage;

    visibleRows.forEach((row, index) => {
        if (index >= start && index < end) {
            row.style.display = ""; // Возвращаем стандартное отображение (table-row)
        }
    });

    //  Обновляем кнопки пагинации
    updatePagination(totalPages);

    // Обновляем счетчик страниц (если есть такой элемент для отладки или инфо)
    // console.log(`Showing page ${window.currentPage} of ${totalPages}`);
}

// Рисует кнопки пагинации
function updatePagination(totalPages) {
    const container = document.getElementById("pagination");
    if (!container) return;

    container.innerHTML = "";

    // Кнопка "Назад"
    const prevBtn = document.createElement("button");
    prevBtn.textContent = "←";
    prevBtn.disabled = (window.currentPage === 1);
    prevBtn.onclick = () => showPage(window.currentPage - 1);
    container.appendChild(prevBtn);

    // Номера страниц
    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement("button");
        btn.textContent = i;
        if (i === window.currentPage) {
            btn.style.fontWeight = "bold";
            btn.style.textDecoration = "underline";
            btn.style.backgroundColor = "#ddd"; // Визуальное выделение
        }
        btn.onclick = () => showPage(i);
        container.appendChild(btn);
    }

    // Кнопка "Вперед"
    const nextBtn = document.createElement("button");
    nextBtn.textContent = "→";
    nextBtn.disabled = (window.currentPage === totalPages);
    nextBtn.onclick = () => showPage(window.currentPage + 1);
    container.appendChild(nextBtn);
}

// Инициализация при первой загрузке
document.addEventListener("DOMContentLoaded", () => {
    showPage(1);
});
