document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll("tbody tr").forEach(function(row) {
        row.addEventListener("click", function() {
            var dataNumber = row.getAttribute("data-data-number");

            // 동적으로 폼 생성
            var form = document.createElement("form");
            form.method = "GET";
            form.action = detailPageUrl;  // Ensure 'detailPageUrl' is defined elsewhere in your script

            // 필요한 필드 추가 (only 'data_number')
            var hiddenField = document.createElement("input");
            hiddenField.type = "hidden";
            hiddenField.name = 'data_number';
            hiddenField.value = dataNumber;
            form.appendChild(hiddenField);

            // 폼을 문서에 추가하고 제출
            document.body.appendChild(form);
            form.submit();
        });
    });
});