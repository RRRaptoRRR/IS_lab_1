/* === filters.js === */

function clearOtherFilters(activeId) {
    let ids = ["searchId", "searchName", "searchDate", "searchAge"];
    ids.forEach(id => {
        if (id !== activeId) {
            let el = document.getElementById(id);
            if (el) el.value = "";
        }
    });
}

function calculateAge(birthDate) {
    let today = new Date();
    let birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    let m = today.getMonth() - birth.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) age--;
    return age;
}

/**
 * Обновляет интерфейс после изменения фильтров.
 * keepPage = true: пытаемся остаться на текущей странице (для автообновления).
 * keepPage = false: сбрасываем на 1 страницу (для нового поиска).
 */
function updateUI(keepPage) {
    if (typeof showPage === "function") {
        if (keepPage) {
            // Оставляем текущую страницу (логика проверки границ уже есть внутри showPage)
            showPage(window.currentPage);
        } else {
            // Новый фильтр - всегда первая страница
            window.currentPage = 1;
            showPage(1);
        }
    }
}

/* --- Функции фильтрации --- */
/* Они ТОЛЬКО ставят класс filtered-out. Они НЕ меняют display: none. */

function filterById(keepPage = false) {
    clearOtherFilters("searchId");
    let val = document.getElementById("searchId")?.value.toLowerCase() || "";

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(1)");
        let match = cell && cell.textContent.toLowerCase().includes(val);

        if (val && !match) row.classList.add("filtered-out");
        else row.classList.remove("filtered-out");
    });
    updateUI(keepPage);
}

function filterByName(keepPage = false) {
    clearOtherFilters("searchName");
    let val = document.getElementById("searchName")?.value.toLowerCase() || "";

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(2)");
        let match = cell && cell.textContent.toLowerCase().includes(val);

        if (val && !match) row.classList.add("filtered-out");
        else row.classList.remove("filtered-out");
    });
    updateUI(keepPage);
}

function filterByDate(keepPage = false) {
    clearOtherFilters("searchDate");
    let val = document.getElementById("searchDate")?.value;
    let inputDate = val ? new Date(val) : null;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(13)"); // Проверьте индекс столбца!
        let visible = true;

        if (cell && inputDate) {
            let rowDate = new Date(cell.textContent.trim());
            if (rowDate <= inputDate) visible = false;
        }

        if (!visible) row.classList.add("filtered-out");
        else {
            row.classList.remove("filtered-out");
            count++;
        }
    });

    if(document.getElementById("dateCount"))
        document.getElementById("dateCount").textContent = val ? count : 0;

    updateUI(keepPage);
}

function filterByAge(keepPage = false) {
    clearOtherFilters("searchAge");
    let val = document.getElementById("searchAge")?.value;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(20)"); // Проверьте индекс столбца!
        let visible = true;

        if (cell && val) {
            let age = calculateAge(cell.textContent.trim());
            if (age > val) visible = false;
        }

        if (!visible) row.classList.add("filtered-out");
        else {
            row.classList.remove("filtered-out");
            count++;
        }
    });

    if(document.getElementById("ageCount"))
        document.getElementById("ageCount").textContent = val ? count : 0;

    updateUI(keepPage);
}

/* === ГЛАВНАЯ ФУНКЦИЯ ВОССТАНОВЛЕНИЯ === */
window.reapplyAllFilters = function() {
    let foundActiveFilter = false;

    if (document.getElementById("searchId")?.value) {
        filterById(true); // true = сохранить страницу
        foundActiveFilter = true;
    } else if (document.getElementById("searchName")?.value) {
        filterByName(true);
        foundActiveFilter = true;
    } else if (document.getElementById("searchDate")?.value) {
        filterByDate(true);
        foundActiveFilter = true;
    } else if (document.getElementById("searchAge")?.value) {
        filterByAge(true);
        foundActiveFilter = true;
    }

    // Если фильтров нет, просто обновляем текущую страницу для новых данных
    if (!foundActiveFilter && typeof showPage === "function") {
        showPage(window.currentPage);
    }
};
