function refreshTable() {
    $.ajax({
        url: 'table-update',
        type: 'GET',
        success: function(response) {
            $('#musicTableBody').html(response);

            // Вызываем нашу глобальную функцию
            if (typeof window.reapplyAllFilters === "function") {
                window.reapplyAllFilters();
            } else if (typeof reapplyAllFilters === "function") {
                reapplyAllFilters();
            }
            // Если функции нет, хотя бы пагинацию обновим
            else if (typeof showPage === "function") {
                showPage(currentPage);
            }
        },

        error: function(error) {
            console.log("Ошибка обновления: ", error);
        }
    });
}


// Запускаем обновление каждые 2000 мс (2 секунды)
setInterval(refreshTable, 2000);