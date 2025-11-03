function clearOtherFilters(activeId) {
    let ids = ["searchId", "searchName", "searchDate", "searchAge"];
    ids.forEach(id => {
        if (id !== activeId) document.getElementById(id).value = "";
    });
}

/* Высчитываем возраст по дате */
function calculateAge(birthDate) {
    let today = new Date();
    let birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    let m = today.getMonth() - birth.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) age--;
    return age;
}

function filterById() {
    let value = document.getElementById("searchId").value.toLowerCase();
    document.querySelectorAll("table tbody tr").forEach(row => {
        let match = row.querySelector("td:nth-child(1)").textContent.toLowerCase().includes(value);
        row.classList.toggle("filtered-out", value && !match);
    });
    afterFilterUpdate();
}

function filterByName() {
    let value = document.getElementById("searchName").value.toLowerCase();
    document.querySelectorAll("table tbody tr").forEach(row => {
        let match = row.querySelector("td:nth-child(2)").textContent.toLowerCase().includes(value);
        row.classList.toggle("filtered-out", value && !match);
    });
    afterFilterUpdate();
}

function filterByDate() {
    let value = document.getElementById("searchDate").value;
    let inputDate = value ? new Date(value) : null;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let dateCell = row.querySelector("td:nth-child(13)").textContent.trim();
        let rowDate = new Date(dateCell);
        let visible = !inputDate || rowDate > inputDate;
        row.classList.toggle("filtered-out", !visible);
        if (visible) count++;
    });

    document.getElementById("dateCount").textContent = count;
    afterFilterUpdate();
}

function filterByAge() {
    let value = document.getElementById("searchAge").value;
    let count = 0;

    document.querySelectorAll("table tbody tr").forEach(row => {
        let birthday = row.querySelector("td:nth-child(20)").textContent.trim();
        let age = calculateAge(birthday);
        let visible = !value || age <= value;
        row.classList.toggle("filtered-out", !visible);
        if (visible) count++;
    });

    document.getElementById("ageCount").textContent = count;
    afterFilterUpdate();
}
