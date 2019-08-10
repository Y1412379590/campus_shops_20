$(function() {
    var shopId = 1;
    var productName = '';

    function getList() {
        var listUrl = '/shop/listuserproductmapsbyshop?pageIndex=1&pageSize=9999&shopId=' + shopId + '&productName=' + productName;
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userProductMapList = data.userProductMapList;
                var tempHtml = '';
                userProductMapList.map(function (item, index) {
                    tempHtml += ''
                         +      '<div class="row row-productbuycheck">'
                         +          '<div class="col-20">'+ item.product.productName +'</div>'
                         +          '<div class="col-40 productbuycheck-time">'+ new Date(item.createTime).Format('yyyy-MM-dd hh:mm:ss') +'</div>'
                         +          '<div class="col-20">'+ item.user.name +'</div>'
                         +          '<div class="col-10">'+ item.point +'</div>'
                         +          '<div class="col-20">'+ item.operator.name +'</div>'
                         +      '</div>';
                });
                $('.productbuycheck-wrap').html(tempHtml);
            }
        });
    }

    $('#search').on('input', function (e) {
        productName = e.target.value;
        $('.productbuycheck-wrap').empty();
        getList();
    });

    getList();

    //获取7天的销量
    function getProductSellDailyList() {

        var listUrl = '/shop/listproductselldailyinfobyshop';
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var myChart = echarts.init(document.getElementById('chart'));
                //动态生成信息部分
                var option = generateStaticEchartPart();
                option.legend.data = data.legendData;
                option.xAxis = data.xAxis;
                option.series = data.series;
                myChart.setOption(option);
            }
        });
    }


    /*
    * echarts部分
    * */
    var myChart = echarts.init(document.getElementById('chart'));

    function generateStaticEchartPart() {
        var option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['奶茶','文具','咖啡']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'奶茶',
                    type:'bar',
                    data:[120, 132, 101, 134, 290, 230, 220]
                },
                {
                    name:'文具',
                    type:'bar',
                    data:[60, 72, 71, 74, 190, 130, 110]
                },
                {
                    name:'咖啡',
                    type:'bar',
                    data:[62, 82, 91, 84, 109, 110, 120]
                }
            ]
        };

        return option;
    }

    // myChart.setOption(option);

    getProductSellDailyList();





});