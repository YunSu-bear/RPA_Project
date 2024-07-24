document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll("tbody tr").forEach(function(row) {
        row.addEventListener("click", function() {
            var dataNumber = row.getAttribute("data-data-number");
            var moldNumber = row.getAttribute("data-mold-number");
            var productName = row.getAttribute("data-product-name");
            var texture = row.getAttribute("data-texture");
            var grade = row.getAttribute("data-grade");
            var productWeight = row.getAttribute("data-product-weight");
            var srWeight = row.getAttribute("data-sr-weight");
            var bright = row.getAttribute("data-bright-tone");
            var cav = row.getAttribute("data-cav");
            var ct = row.getAttribute("data-c-t");

            // 동적으로 폼 생성
            var form = document.createElement("form");
            form.method = "GET";
            form.action = detailPageUrl;
            
            // 필요한 필드 추가
            var fields = {
                'data_number': dataNumber,
                'mold_number': moldNumber,
                'product_name': productName,
                'texture': texture,
                'grade': grade,
                'product_weight': productWeight,
                'sr_weight': srWeight,
                'bright_tone': bright,
                'cav': cav,
                'c_t': ct
            }
            for (var key in fields) {
                if (fields.hasOwnProperty(key)) {
                    var hiddenField = document.createElement("input");
                    hiddenField.type = "hidden";
                    hiddenField.name = key;
                    hiddenField.value = fields[key];
                    form.appendChild(hiddenField);
                }
            }
             // 폼을 문서에 추가하고 제출
            document.body.appendChild(form);
            form.submit();
        });
    });
});