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

/* --- Функции фильтрации --- */

function filterById() {
    clearOtherFilters("searchId");
    let value = document.getElementById("searchId").value.toLowerCase();
    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(1)");
        if(cell) {
            let match = cell.textContent.toLowerCase().includes(value);
            row.classList.toggle("filtered-out", value && !match);
        }
    });
    // Важно: проверяем, существует ли функция перед вызовом
    if (typeof afterFilterUpdate === "function") afterFilterUpdate();
}

function filterByName() {
    clearOtherFilters("searchName");
    let value = document.getElementById("searchName").value.toLowerCase();
    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(2)");
        if(cell) {
            let match = cell.textContent.toLowerCase().includes(value);
            row.classList.toggle("filtered-out", value && !match);
        }
    });
    if (typeof afterFilterUpdate === "function") afterFilterUpdate();
}

function filterByDate() {
    clearOtherFilters("searchDate");
    let value = document.getElementById("searchDate").value;
    let inputDate = value ? new Date(value) : null;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(13)"); // Убедитесь, что индекс верный!
        if(cell) {
            let rowDate = new Date(cell.textContent.trim());
            let visible = !inputDate || rowDate > inputDate;
            row.classList.toggle("filtered-out", !visible);
            if (visible) count++;
        }
    });

    let counter = document.getElementById("dateCount");
    if(counter) counter.textContent = count;

    if (typeof afterFilterUpdate === "function") afterFilterUpdate();
}

function filterByAge() {
    clearOtherFilters("searchAge");
    let value = document.getElementById("searchAge").value;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let cell = row.querySelector("td:nth-child(20)"); // Убедитесь, что индекс верный!
        if(cell) {
            let birthday = cell.textContent.trim();
            let age = calculateAge(birthday);
            let visible = !value || age <= value;
            row.classList.toggle("filtered-out", !visible);
            if (visible) count++;
        }
    });

    let counter = document.getElementById("ageCount");
    if(counter) counter.textContent = count;

    if (typeof afterFilterUpdate === "function") afterFilterUpdate();
}

/* === ГЛАВНАЯ ФУНКЦИЯ ВОССТАНОВЛЕНИЯ === */
/* Эту функцию вызывает table.js после AJAX */
window.reapplyAllFilters = function() {
    // Проверяем каждый инпут. Если есть значение - запускаем фильтр.

    let idInput = document.getElementById("searchId");
    if (idInput && idInput.value) {
        filterById();
        return;
    }

    let nameInput = document.getElementById("searchName");
    if (nameInput && nameInput.value) {
        filterByName();
        return;
    }

    let dateInput = document.getElementById("searchDate");
    if (dateInput && dateInput.value) {
        filterByDate();
        return;
    }

    let ageInput = document.getElementById("searchAge");
    if (ageInput && ageInput.value) {
        filterByAge();
        return;
    }

    // Если ни один фильтр не активен, просто обновляем пагинацию
    // Переменная currentPage должна быть глобальной в pagination.js
    if (typeof showPage === "function" && typeof currentPage !== "undefined") {
        showPage(currentPage);
    } else if (typeof showPage === "function") {
        showPage(1);
    }
};
