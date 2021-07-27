<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省市县联动</title>
    <script type="text/javascript" src="/static/js/jquery-1.8.3.min.js"></script>

    <script>

        jQuery(function ($) {
            // 先加载出省份
            $.ajax({
                url: "/load.json?pid=-1",
                success: function (data) {
                    // data: [{id:11,name:"北京"}, ...]
                    $(data).each(function () {
                        //$("#sheng").append('<option value="'+this.id+'">'+this.name+'</option>');
                        $("<option>", {
                            value: this.id,
                            text: this.name
                        }).appendTo("#sheng");
                    });
                }
            });


            $("#sheng").change(function () {
                // 加载之前，先清空（保留1项）
                //$("#shi").prop("length", 1);
                //$("#xian").prop("length", 1);
                $("#shi,#xian").prop("length", 1);

                if (!this.value) return;

                $.ajax({
                    url: "/load.json?pid=" + this.value,
                    success: function (data) {
                        // data: [{id:11,name:"北京"}, ...]
                        $(data).each(function () {
                            $("#shi").append('<option value="'+this.id+'">'+this.name+'</option>');
                        });
                    }
                });
            })


            $("#shi").change(function () {
                // 加载之前，先清空（保留1项）
                $("#xian").prop("length", 1);

                $.ajax({
                    url: "/load.json?pid=" + this.value,
                    success: function (data) {
                        // data: [{id:11,name:"北京"}, ...]
                        $(data).each(function () {
                            $("#xian").append('<option value="'+this.id+'">'+this.name+'</option>');
                        });
                    }
                });
            })

        })

    </script>

</head>
<body>
    <select id="sheng">
        <%--当option没有指定value时，则其中的文本将作为value--%>
        <option value="">--请选择省--</option>
    </select>

    <select id="shi">
        <option value="">--请选择市--</option>
    </select>

    <select id="xian">
        <option value="">--请选择县(区)--</option>
    </select>
</body>
</html>
