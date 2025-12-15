function refreshTable() {
    $.ajax({
        url: 'table-update',
        type: 'GET',
        success: function(response) {
            // 1. Заменяем содержимое таблицы
            $('#musicTableBody').html(response);

            // 2. ВАЖНО: Заново применяем пагинацию к новым данным!
            // Мы передаем currentPage, чтобы пользователь остался на той же странице,
            // на которой был (не сбрасывало на первую)
            if (typeof showPage === "function") {
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