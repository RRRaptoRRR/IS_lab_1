
function refreshTable() {
    $.ajax({
        url: 'table-update',
        type: 'GET',
        success: function(response) {
            // Вставляем новый HTML
            $('#musicTableBody').html(response);

            // Восстанавливаем фильтры и пагинацию
            // Функция reapplyAllFilters сама решит, какую страницу показать
            if (typeof window.reapplyAllFilters === "function") {
                window.reapplyAllFilters();
            } else {
                // Если filters.js не загружен, просто показываем текущую страницу
                if (typeof showPage === "function") {
                    showPage(window.currentPage);
                }
            }
        },
        error: function(error) {
            console.log("Ошибка обновления таблицы: ", error);
        }
    });
}

// Запускаем таймер (2 секунды)
setInterval(refreshTable, 2000);
