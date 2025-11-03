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

function filterByDate() {
    let inputValue = document.getElementById("searchDate").value;
    let rows = document.querySelectorAll("table tbody tr");
    let count = 0;

    // Если дата не введена — показать всё и вывести количество
    if (!inputValue) {
        rows.forEach(row => row.style.display = "");
        document.getElementById("dateCount").textContent = rows.length;
        return;
    }

    let inputDate = new Date(inputValue); // YYYY-MM-DD → Date

    rows.forEach(row => {
        let dateCell = row.querySelector("td:nth-child(13)"); //number in table
        if (!dateCell) return;

        let bandDateString = dateCell.textContent.trim(); // "2002-06-01"
        let bandDate = new Date(bandDateString);

        if (bandDate > inputDate) {
            row.style.display = "";
            count++;
        } else {
            row.style.display = "none";
        }
    });

    document.getElementById("dateCount").textContent = count;

    // Очистка других фильтров для независимости
    document.getElementById("searchId").value = "";
    document.getElementById("searchName").value = "";
}

function calculateAge(birthDate) {
    let today = new Date();
    let birth = new Date(birthDate);
    let age = today.getFullYear() - birth.getFullYear();
    let monthDiff = today.getMonth() - birth.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
        age--;
    }
    return age;
}

function filterByAge() {
    let ageInput = document.getElementById("searchAge").value;
    let rows = document.querySelectorAll("table tbody tr");
    let count = 0;

    // если поле пустое → показываем всё
    if (!ageInput) {
        rows.forEach(row => row.style.display = "");
        document.getElementById("ageCount").textContent = rows.length;
        return;
    }

    rows.forEach(row => {
        let birthdayCell = row.querySelector("td:nth-child(20)");
        if (!birthdayCell) return;

        let birthdayText = birthdayCell.textContent.trim();
        let age = calculateAge(birthdayText);
        console.log(birthdayText);
        console.log(age);


        if (age <= ageInput) {
            row.style.display = "";
            count++;
        } else {
            row.style.display = "none";
        }
    });

    document.getElementById("ageCount").textContent = count;

    // сбрасываем остальные фильтры чтобы фильтр был независимым
    document.getElementById("searchId").value = "";
    document.getElementById("searchName").value = "";
    document.getElementById("searchDate").value = "";
}