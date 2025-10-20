function filterTableById() {
    // Получаем значение из поля ввода
    var inputId = document.getElementById('input_id').value;
    var table = document.getElementById('result_table');
    var rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    var found = false;

    // Сначала показываем все строки
    for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = '';
    }

    // Если поле пустое, показываем все строки
    if (inputId === '') {
        document.getElementById('noResultMessage').style.display = 'none';
        return;
    }

    // Ищем строку с нужным ID
    for (var i = 0; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName('td');
        if (cells.length > 0) {
            var rowId = cells[0].textContent || cells[0].innerText;

            if (rowId === inputId) {
                // Нашли нужную строку - показываем только её
                rows[i].style.display = '';
                found = true;
            } else {
                // Скрываем другие строки
                rows[i].style.display = 'none';
            }
        }
    }

    // Показываем сообщение, если ничего не найдено
    var messageElement = document.getElementById('noResultMessage');
    if (found) {
        messageElement.style.display = 'none';
    } else {
        messageElement.style.display = 'block';
        messageElement.innerHTML = 'Группа с ID ' + inputId + ' не найдена';
    }
}

// Функция для сброса фильтра и показа всех данных
function showAll() {
    var table = document.getElementById('result_table');
    var rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = '';
    }

    document.getElementById('input_id').value = '';
    document.getElementById('noResultMessage').style.display = 'none';
}